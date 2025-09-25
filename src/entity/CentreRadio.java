package entity;

public class CentreRadio {
    private String nom;
    private String adresse;
    private int num;
    private String mail;

    public CentreRadio(String nom, String adresse, int num, String mail) {
        this.nom = nom;
        this.adresse = adresse;
        this.num = num;
        this.mail = mail;
    }

    public CentreRadio() {

    }

    public void afficherCentreRadio() {
        System.out.println("Nom du Centre: " + nom);
        System.out.println("Adresse: " + adresse);
        System.out.println("Num: " + num);
        System.out.println("Mail: " + mail);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String toString() {
        return ("\nNom : " + nom + "\nAddress : " + adresse +"\nNum : " + num + "\nMail : " + mail);

    }
}