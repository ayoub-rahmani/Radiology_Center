package entity;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//note every instance shall be saves into a json file
//so we can every patient have his own treatement(based on the categorie)

public class RendezVous {
    //when creating a rdv make sure that this  patient has no rdv not done
    private String date;
    private int idRv;
    private Patient patient;
    private Prescription prescription;
    private String state="Waiting";
    public RendezVous(int idRv, Patient patient,Prescription prescription) {
        this.idRv = idRv;
        this.patient = patient;
        this.prescription = prescription;
        //static because every patient will give his prescrption
    }
    private void scheduleRDV(){
        //schedule the rdv
        //serv
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getIdRv() {
        return idRv;
    }

    public void setIdRv(int idRv) {
        this.idRv = idRv;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "\nRendez Vous Details : \n"+
                "Patient CIN : "+patient.getCIN()+"\nPatient Name : "+patient.getNom()+
                " "+patient.getPrenom()+
                "\n"+prescription+"\nState : "+state+"\n Date : "+date+"\n--------\n";
    }

}
