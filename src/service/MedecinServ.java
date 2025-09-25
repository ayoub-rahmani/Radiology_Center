package service;

import entity.Medecin;
import entity.Medecin;
import ihm.Output;
import persistance.PersMedecin;

public class MedecinServ {
    PersMedecin persMedecin=new PersMedecin();
    public Output addMedecin(Medecin m) {
        if(viewMedecin(m.getIdM()).getObj()==null){
            persMedecin.add(m);
            return new Output(true,"Medecin added Sucessfully !",null);
        }
        return new Output(false,"Medecin Already exists",null);

    }
    public Output removeMedecin(int idM){
        Medecin medecin = (Medecin) persMedecin.getMedecin(idM);
        if (medecin != null) {
            persMedecin.remove(medecin);
            return new Output(true,"Medecin removed successfully!",null);
        } else {
            return new Output(false,"Medecin Not Found",null);
        }
    }
    public  Output viewMedecin(int idM) {
        Medecin medecin = (Medecin) persMedecin.getMedecin(idM);
        if (medecin != null) {
            return new Output(true,"",medecin);
        } else {
            return new Output(false,"Medecin Not Found",null);
        }
    }

    public Output listAllMedecins() {
        if(persMedecin.getAllMedecin()==null){
            return new Output(false,"All Medecin List is Empty",null);
        }
        return new Output(true,"--- ALL MedecinS ---\n",persMedecin.getAllMedecin());
    }
}
