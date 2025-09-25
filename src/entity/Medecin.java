package entity;

public class Medecin extends Personne{
    private int idM;
    private String specialite;
    private String contact;

    public Medecin(String nom, String prenom, int numTelephone, int idM, String specialite, String contact) {
        super(nom, prenom, numTelephone);
        this.idM = idM;
        this.specialite = specialite;
        this.contact = contact;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    @Override
    public String toString() {
        return
        super.toString()+
        "\nContact : " + this.contact+
        "\nSpecialite : " + specialite+"\n--------------------------\n";
    }
}
