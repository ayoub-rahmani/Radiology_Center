package entity;

import java.util.Scanner;
public class Examen {
    private int idRDV;
    private Patient patient;
    private Radiologue radiologue;
    private Salle salle;
    private Categorie categorie;
    private Rapport rapport;

    public Examen(int id, Patient patient, Radiologue radiologue, Categorie categorie,Salle salle) {
        this.idRDV = id;
        this.patient = patient;
        this.radiologue = radiologue;
        this.categorie = categorie;
        this.salle = salle;
    }

    public int getIdRDV() {
        return idRDV;
    }

    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Radiologue getRadiologue() {
        return radiologue;
    }

    public void setRadiologue(Radiologue radiologue) {
        this.radiologue = radiologue;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }
    public  void genererRapport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisit le conteneu du rapport : ");
        String contenu= sc.nextLine();
        this.rapport = new Rapport(contenu,this.radiologue);
    }
    @Override
    public String toString() {
        String r="";
        if(rapport==null)
        {
            r="No Rapport Yet";
        }
        else{
            r=rapport.toString();
        }
        return "Examens Details : \n"+
                "Id : "+idRDV +"\n"+
                "Patient : "+patient.getNom() +" "+patient.getPrenom()+
                "\nRadiologue : "+radiologue.getNom()+" "+radiologue.getPrenom()+
                "\nSalle : "+salle.getNum()+
                "\nTreatemnt : "+categorie.getNom()+
                "\nRapport : "+r+"\n-----------------\n";

    }
    //then when creating tho do_exam function look for the latest rdv of this client
    //and retrieve the prescription from there to assign it to his dossier and add the price of the treatement
}
