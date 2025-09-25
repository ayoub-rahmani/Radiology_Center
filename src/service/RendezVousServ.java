package service;

import entity.*;
import ihm.Output;
import persistance.PersCalendrier;
import persistance.PersExamen;
import persistance.PersRendezVous;
import service.RadiologueServ;
import service.SalleServ;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RendezVousServ {
    private RadiologueServ radiologueServ = new RadiologueServ();
    private SalleServ salleServ = new SalleServ();
    private PersCalendrier persCalendrier = new PersCalendrier();
    private PersRendezVous persRendezVous = new PersRendezVous();
    private CategorieServ categorieServ = new CategorieServ();
    private PersExamen persExamen=new PersExamen();
    private PatientServ patientServ = new PatientServ();
    private MedecinServ medecinServ = new MedecinServ();
    private Set<Integer> usedIds = new HashSet<>();
    private int generateUniqueId() {
        Random random = new Random();
        int newId;
        do {newId = random.nextInt(9000) + 1000;
        } while (!isIdUnique(newId));

        usedIds.add(newId);
        return newId;
    }
    private boolean isIdUnique(int id) {
        if (usedIds.contains(id)) {
            return false;
        }List<RendezVous> existingRadiologues = persRendezVous.getAllRendezVous();
        return existingRadiologues.stream()
                .noneMatch(radiologue -> radiologue.getIdRv() == id);
    }
    public Output check_existanceOfTreatment(RendezVous rendezVous) {
        if(categorieServ.viewCategorie(rendezVous.getPrescription().getTraitement()).istrue()){
            return new Output(true,"",null);
        }
        else{
            return new Output(false,"Treatement "+rendezVous.getPrescription().getTraitement()+" unavailable in our center :(",null);
        }
    }
    private void updatePatientDossier(Patient patient, Prescription prescription) {
        Dossier dossier = patient.getDossier();
        ArrayList<Generatable> results = dossier.getResults();

        if (results == null) {
            results = new ArrayList<>();
        }

        Generatable newEntry = new Generatable(prescription, null);
        results.add(newEntry);

        dossier.setResults(results);
        patient.setDossier(dossier);
    }
    public RendezVous scheduleRendezVous(RendezVous newRendezVous) {
        newRendezVous.setIdRv(generateUniqueId());
        List<Radiologue> radiologues = (List<Radiologue>)radiologueServ.listAllRadiologues().getObj();
        List<Salle> salles = (List<Salle>)salleServ.listAllSalles().getObj();

        LocalDateTime startDateTime = LocalDateTime.now();
        Radiologue radiologueEntry=null;
        Salle salleEntry=null;
        while (true) {
            String formattedDateTime = startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            newRendezVous.setDate(formattedDateTime);
            if(radiologueEntry==null) {
                for (Radiologue radiologue : radiologues) {
                    if (isTimeSlotAvailable(radiologue, formattedDateTime)) {
                        radiologueEntry = radiologue;
                    }
                }
            }
            if(salleEntry==null) {
                for (Salle salle : salles) {
                    if (isTimeSlotAvailable(salle, formattedDateTime)) {
                        salleEntry = salle;
                    }
                }
            }
            if(isTimeSlotAvailable(radiologueEntry, formattedDateTime) && isTimeSlotAvailable(salleEntry, formattedDateTime)) {
                persCalendrier.add(new Calendrier(radiologueEntry,salleEntry,formattedDateTime));
                persExamen.add(new Examen(newRendezVous.getIdRv(),newRendezVous.getPatient(),radiologueEntry,(Categorie)categorieServ.viewCategorie(newRendezVous.getPrescription().getTraitement()).getObj(),salleEntry));
                if (!patientServ.viewPatient(newRendezVous.getPatient().getCIN()).istrue()) {
                    Patient patient = newRendezVous.getPatient();
                    updatePatientDossier(patient, newRendezVous.getPrescription());
                    patientServ.addPatient(patient);
                    newRendezVous.setPatient(patient);
                } else {
                    Patient patient = newRendezVous.getPatient();
                    updatePatientDossier(patient, newRendezVous.getPrescription());
                    patientServ.modifyPatient(patient.getCIN(), patient);
                    newRendezVous.setPatient(patient);
                }
                if(!medecinServ.viewMedecin(newRendezVous.getPrescription().getMedecin().getIdM()).istrue()){
                    medecinServ.addMedecin(newRendezVous.getPrescription().getMedecin());
                }
                persRendezVous.add(newRendezVous);
                return newRendezVous;
            }
            startDateTime = startDateTime.plusHours(1);
        }

    }

    private boolean isTimeSlotAvailable(Radiologue radiologue, String proposedDateTime) {
        if(radiologue==null)
        {
            return false;
        }
        List<Calendrier> existingCalendriers = persCalendrier.getCalendrierByRadiologue(radiologue);

        LocalDateTime proposedStart = LocalDateTime.parse(proposedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime proposedEnd = proposedStart.plusHours(1);

        for (Calendrier existingCalendrier : existingCalendriers) {
            LocalDateTime existingStart = LocalDateTime.parse(existingCalendrier.getDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime existingEnd = existingStart.plusHours(1);

            if (isOverlapping(proposedStart, proposedEnd, existingStart, existingEnd)) {
                return false;
            }
        }

        return true;
    }
        private boolean isTimeSlotAvailable(Salle salle, String proposedDateTime) {
            if(salle==null)
            {
                return false;
            }
            List<Calendrier> existingCalendriers = persCalendrier.getCalendrierBySalle(salle);

            LocalDateTime proposedStart = LocalDateTime.parse(proposedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime proposedEnd = proposedStart.plusHours(1);

            for (Calendrier existingCalendrier : existingCalendriers) {
                LocalDateTime existingStart = LocalDateTime.parse(existingCalendrier.getDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime existingEnd = existingStart.plusHours(1);

                if (isOverlapping(proposedStart, proposedEnd, existingStart, existingEnd)) {
                    return false;
                }
            }

            return true;
        }

    private boolean isOverlapping(LocalDateTime start1, LocalDateTime end1,
                                  LocalDateTime start2, LocalDateTime end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }
    public Output cancelRendezVous(int IdR){
        if(viewRdv(IdR).istrue()){
        persRendezVous.cancelRendezVous(IdR);
        persExamen.remove(IdR);
        return new Output(true,"Rendez Vous Canceled Succesfully ! ",null);
        }
        return new Output(false,"Rendez Vous Not Found ! ",null);
    }
    public Output viewRdv(int IdR){
        RendezVous rdv=persRendezVous.getRendezVous(IdR);
        if(rdv==null){
            return new Output(false,"No Rendez-Vous Found",null);
        }
        return new Output(true,"",rdv);
    }
    public Output listAllRendezVous(){
        return new Output(true,"List Of Rendez-Vous : \n",persRendezVous.getAllRendezVous());
    }
    public Output showRendezVousbyPatient(int CIN){
        return new Output(true,"---- Rendez Vous -----\n",persRendezVous.getRendezVousByPatient(CIN));
    }
    public Output showRendezVousByState(String state) {
        List<RendezVous> rdvsByState = persRendezVous.getRendezVousByState(state);

        if (rdvsByState == null || rdvsByState.isEmpty()) {
            return new Output(false, "No Rendez-Vous found with state: " + state, null);
        }

        return new Output(true, "---- Rendez-Vous with State: " + state + " ----\n", rdvsByState);
    }

}
