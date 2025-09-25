package entity;

public class Categorie {
    private String nom;
    private double prix;

    public Categorie(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String toString() {
        return "\nNom : " + nom + "\nPrix : " + prix+"\n------------\n";
    }
}
