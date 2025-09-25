package entity;

public class Radiologue extends Personne{
    private int idR;
    private String specialite;//can be paired with categorie class
    private  static double salaire=2000;
    public Radiologue(String nom, String prenom, int numTelephone, String specialite, int idR) {
        super(nom, prenom, numTelephone);
        this.idR = idR;
        this.specialite=specialite;
    }
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public static double getSalaire() {
        return salaire;
    }

    public static void setSalaire(double salaire) {
        Radiologue.salaire = salaire;
    }
    @Override
    public String toString() {
        return super.toString()+
                "\nSpecialite  : " + this.specialite+
                "\nSalaire : " + salaire+"\n---------------------------\n";
    }
}
