package entity;

public class Technicien extends Personne{
    private int idT;
    private static double salaire=1500;
    private int salle_attribuer=0;

    public static double getSalaire() {
        return salaire;
    }

    public static void setSalaire(double salaire) {
        Technicien.salaire = salaire;
    }

    public Technicien(String nom, String prenom, int numTelephone, int idT, int salle_attribuer) {
        super(nom, prenom, numTelephone);
        this.idT = idT;
        this.salle_attribuer = salle_attribuer;
    }
    public int getIdT() {
        return idT;
    }
    public void setIdT(int idT) {
        this.idT = idT;
    }
    public int getSalle_attribuer() {
        return salle_attribuer;
    }
    public void setSalle_attribuer(int salle_attribuer) {
        this.salle_attribuer = salle_attribuer;
    }
    @Override
    public String toString() {
        return "Technicien Details:\n"+
                super.toString()+
                "\nSalle  : " + ((this.salle_attribuer==0)? "No salle for now ":this.salle_attribuer)+
                "\nSalaire : " + salaire+"\n-------\n";
    }
}
