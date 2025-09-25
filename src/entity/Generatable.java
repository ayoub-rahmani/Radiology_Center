package entity;

public  class Generatable {
    Prescription prescription;
    Rapport rapport;
    public Generatable(Prescription prescription, Rapport rapport) {
        this.prescription = prescription;
        this.rapport = rapport;
    }
    public Prescription getPrescription() {
        return prescription;
    }
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
    public Rapport getRapport() {
        return rapport;
    }
    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }
    public String toString() {
        return "\nPerescription : "+prescription+"\n"+
                "\nRapport : "+rapport+"\n";
    }
}
