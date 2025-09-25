package entity;

import java.util.Scanner;
public class Patient extends Personne{
    private String adresse;
    private int CIN;
    private String dateDeNaissance;
    private Dossier dossier=new Dossier();


    public Patient(String nom, String prenom, int numTelephone, String adresse, int CIN, String dateDeNaissance, Dossier dossier) {
        super(nom, prenom, numTelephone);
        this.adresse = adresse;
        this.CIN = CIN;
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public int  getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }
    public Dossier getDossier() {
        return dossier;
    }
    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
    @Override
    public String toString() {
        return "CIN : " + CIN+"\n"+
                super.toString()+
                "\nDate De Naissance : " + this.dateDeNaissance+
                "\nAdresse : " + adresse+"\n"+
                "\nDossier : \n" + dossier;

    }
}

