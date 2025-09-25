package entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Contient les compte rendue et prescription de chaque patient
public class Dossier {
    ArrayList<Generatable> results = new ArrayList<Generatable>();

    public ArrayList<Generatable> getResults() {
        return results;
    }

    public void setResults(ArrayList<Generatable> results) {
        this.results = results;
    }

    //chouf 7al
    @Override
    public String toString() {
        String res="";
        for(Generatable row : results) {
            res += row;
            res+="--------------------";
        }
        return res;
    }
}
