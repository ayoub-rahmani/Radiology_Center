package service;

import entity.Salle;
import entity.Technicien;
import ihm.Output;
import persistance.PersSalle;

import java.util.List;

public class SalleServ {
    PersSalle persSalle = new PersSalle();
    public Output addSalle(Salle s) {
        if(viewSalle(s.getNum()).getObj()==null){
        persSalle.add(s);
        return new Output(true,"Salle added Succesfully",null);
        }
        return new Output(false,"Salle with this  number already exists",null);
    }

    public Output removeSalle(int salleNum) {
        TechnicienServ technicienServ = new TechnicienServ();
        Salle salle = persSalle.getSalle(salleNum);
        if (salle != null) {
            for(Technicien tech : salle.getTechnicien()) {
                Technicien t=tech;
                t.setSalle_attribuer(0);
                technicienServ.modifyTechnicien(tech.getIdT(),t);
            }
            persSalle.remove(salleNum);
            return new Output(true,"Salle removed Succesfully",null);
        } else {
            return new Output(false,"Salle with this  number does not exist",null);
        }
    }

    public Output viewSalle(int salleNum) {
        Salle salle = persSalle.getSalle(salleNum);
        if (salle != null) {
            return new Output(true,"",salle);
        } else {
            return new Output(false,"Salle not found!",null);
        }
    }

    public Output listAllSalles() {
        if (persSalle.getAll() != null) {
           return new Output(true,"--- ALL SALLES ---\n",persSalle.getAll());
        } else {
            return new Output(false,"No salles found.",null);
        }
    }

    public Output addTechnicienToSalle(int salleNum, Technicien technicien) {
        SalleServ salleServ = new SalleServ();
        if(!salleServ.viewSalle(salleNum).istrue()){
            return new Output(false,"Salle Not Found",null);
        }
        if(technicien.getSalle_attribuer()==salleNum){
            return new Output(false,"Technicien already in this salle",null);
        }
        persSalle.addTechnicienToSalle(salleNum, technicien);
        TechnicienServ technicienServ= new TechnicienServ();
        if(technicien.getSalle_attribuer()!= 0 ) {
            removeTechnicienFromSalle(technicien.getSalle_attribuer(), technicien.getIdT());
        }
        technicien.setSalle_attribuer(salleNum);
        technicienServ.modifyTechnicien(technicien.getIdT(), technicien);
        return new Output(true,"Technicien added Succesfully",null);
    }

    public Output removeTechnicienFromSalle(int salleNum, int technicienId) {
        if(!persSalle.removeTechnicienFromSalle(salleNum, technicienId)){
            return new Output(false,"Salle Not Found",null);
        }
        TechnicienServ technicienServ= new TechnicienServ();
        Technicien technicien=(Technicien) technicienServ.viewTechnicien(technicienId).getObj();
        technicien.setSalle_attribuer(0);
        technicienServ.modifyTechnicien(technicienId, technicien);
        return new Output(true,"Technicien removed Succesfully",null);
    }
}