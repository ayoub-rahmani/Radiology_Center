package entity;

public class Salle {
    private int num;
    private Technicien[] technicien;
    public Salle(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Technicien[] getTechnicien() {
        return technicien;
    }
    public void setTechnicien(Technicien[] technicien) {
        this.technicien = technicien;
    }
    @Override
    public String toString() {
        String s = "";
        if(technicien != null && technicien.length > 0) {
            for (int i = 0; i < technicien.length; i++) {
                s += "------\n" + technicien[i].getIdT()+" : "+technicien[i].getNom()+"\n";
            }
        }
        else {
            s="No techniciens assigned to this Salle.";
        }
        return "Salle  Details:\n"+
                "\nNumero  : " + this.num+
                "\nTechniciens : \n " + s+"\n-----------\n";

    }
}
