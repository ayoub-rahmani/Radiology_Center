package entity;

public class Personne {
    private String nom;
    private String prenom;
    private int numTelephone;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumTelephone(int numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumTelephone() {
        return numTelephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Personne(String nom, String prenom, int numTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTelephone = numTelephone;
    }
    @Override
    public String toString() {
        return "Name : " + this.getNom() + " " + this.getPrenom()+
                "\nPhone : " + this.getNumTelephone();
    }
}
