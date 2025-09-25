package service;

import entity.*;
import ihm.Output;
import persistance.*;

import java.util.ArrayList;
import java.util.List;

public class ExamenServ {
    private PersExamen persExamen=new PersExamen();
    private PersRendezVous persRendezVous=new PersRendezVous();
    private RadiologueServ radiologueServ= new RadiologueServ();
    private PatientServ patientServ= new PatientServ();
    private PersPatient persPatient=new PersPatient();
    private PersCategorie persCategorie=new PersCategorie();
    private PersFinance persFinance=new PersFinance();


    public Output getAllExamensByState(String state) {
        if(persExamen.getExamensByRendezVousState(state)==null)
        {
            return new Output(false,"No examen are "+state,null);
        }
        return new Output(true,"---- List of "+state+" Exams ----\n",persExamen.getExamensByRendezVousState(state));
    }
    private void updatePatientDossier(Patient patient, Rapport rapport) {
        Dossier dossier = patient.getDossier();
        ArrayList<Generatable> results = dossier.getResults();
        if (results == null || results.isEmpty()) {
            return;
        }
        Generatable current = results.get(results.size() - 1);
        if (current.getRapport() == null) {
            current.setRapport(rapport);
            results.set(results.size() - 1, current);
            dossier.setResults(results);
            patient.setDossier(dossier);
        }
    }
    public Output performExamen(int idRDV) {
        try {
            Examen examen = persExamen.getExamen(idRDV);

            if (examen == null) {
                return new Output(false,"Examen not found for ID: " + idRDV,null);
            }
            examen.genererRapport();
            persExamen.modify(idRDV, examen);

            RendezVous rendezVous = persRendezVous.getRendezVous(idRDV);

            if (rendezVous == null) {
                return new Output(false,"Rendez-Vous not found for ID: " + idRDV,null);
            }
            rendezVous.setState("Done");
            persRendezVous.updateRendezVous(rendezVous);
            
            double prix =persCategorie.getCategorie(rendezVous.getPrescription().getTraitement()).getPrix();
            Finance finance =persFinance.getFinance();
            finance.setRevenue(finance.getRevenue()+prix);
            persFinance.saveFinance(finance);

            // Hnee zid l compte rendu ll patient w zid l flous $ fl finance(PDF maybe zeda)
            Patient patient = rendezVous.getPatient();
            updatePatientDossier(patient, examen.getRapport());
            persPatient.modify(patient.getCIN(), patient);
            return new Output(true, "Examen completed for ID: " + idRDV, null);
        } catch (NumberFormatException e) {
            return new Output(false,"Invalid Rendez-Vous ID format: " + idRDV,null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false,e.getMessage(),null);
        }
    }

    public Output getExamenById(int idRDV) {
        if(persExamen.getExamen(idRDV)==null){
            return new Output(false,"Examen not found for ID: " + idRDV,null);
        }
        return new Output(true,"",persExamen.getExamen(idRDV));
    }
    public Output listExamens(String state) {
        List<Examen> examens =(List<Examen>) getAllExamensByState(state);

        if (examens==null || examens.isEmpty()) {
            return new Output(false,"No examens found with state: " + state,null);
        }
        return new Output(true,"---- List of examens with state "+state+" ----\n",examens);
    }
    public Output getExamensByRadiologue(int idR) {
        Radiologue radiologue= (Radiologue) radiologueServ.viewRadiologue(idR).getObj();
        if(radiologue==null){
            return new Output(false,"Radiologue not found for ID: " + idR,null);
        }
        else {
            if(persExamen.getExamensByRadiologue(idR)==null){
                return new Output(false,"Radiologue : " + radiologue.getNom()+" "+radiologue.getPrenom()+" has no exams for now",null);
            }
            else {
                return new Output(true,"Exams for Radiologue  "  + radiologue.getNom()+" "+radiologue.getPrenom() +"\n :",persExamen.getExamensByRadiologue(idR));
            }
        }
    }

    public Output getExamensByPatient(int CIN) {
        Patient patient= (Patient) patientServ.viewPatient(CIN).getObj();
        if(patient==null){
            return new Output(false,"Patient  not found for CIN : " + CIN,null);
        }
        else {
            if(persExamen.getExamensByPatient(CIN)==null){
                return new Output(false,"Patient : " + patient.getNom()+" "+patient.getPrenom()+" has no exams for now",null);
            }
            else {
                return new Output(true,"Exams for Patient : "  + patient.getNom()+" "+patient.getPrenom() +"\n :",persExamen.getExamensByPatient(CIN));
            }
        }
    }
    public Output getExamensByCategorie(String categorie) {
        CategorieServ categorieServ=new CategorieServ();
        if(!categorieServ.viewCategorie(categorie).istrue()){
            categorieServ.listAllCategories();
            return new Output(false,"Categorie not found",null);
        }
        List<Examen> examens = persExamen.getExamensByCategorie(categorie);
        if (examens == null || examens.isEmpty()) {
            return new Output(false, "No exams found for category: " + categorie, null);
        }

        return new Output(true, "Exams for Category: " + categorie + "\n", examens);
    }
    public Output remove(int idRv){
        if(getExamenById(idRv).getObj()==null){
            return  new Output(false,"No exam is Found with this ID",null);
        }
        persExamen.remove(idRv);
        return new Output(true,"",null);
    }
}

