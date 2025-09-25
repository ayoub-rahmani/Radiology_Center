package entity;

public class Calendrier {
    private Radiologue radiologue;
    private Salle salle;
    private String Date;

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Calendrier(Radiologue radiologue, Salle salle, String date) {
        this.radiologue = radiologue;
        this.salle = salle;
        Date = date;
    }
}
