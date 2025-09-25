package service;

import entity.Radiologue;
import entity.Technicien;
import entity.Technicien;
import ihm.Output;
import persistance.PersTechnicien;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TechnicienServ {
    private PersTechnicien persTechnicien=new PersTechnicien();
    private SalleServ salleServ=new SalleServ();
    private Set<Integer> usedIds = new HashSet<>();
    private int generateUniqueId() {
        Random random = new Random();
        int newId;
        do {newId = random.nextInt(9000) + 1000;
        } while (!isIdUnique(newId));

        usedIds.add(newId);
        return newId;
    }
    private boolean isIdUnique(int id) {
        if (usedIds.contains(id)) {
            return false;
        }
        List<Technicien> existingRadiologues = persTechnicien.getAllTechnicien();
        return existingRadiologues.stream()
                .noneMatch(technicien -> technicien.getIdT() == id);
    }
    public Output addTechnicien(Technicien p) {
        p.setIdT(generateUniqueId());
        if(!salleServ.viewSalle(p.getSalle_attribuer()).istrue()){
            return new Output(false,"Salle doesn't exist",null);
        }
        if(viewTechnicien(p.getIdT()).getObj()==null){
        persTechnicien.add(p);
        if(p.getSalle_attribuer()!=0){
            SalleServ salleServ=new SalleServ();
            salleServ.addTechnicienToSalle(p.getSalle_attribuer(),p);
        }
        return new Output(true,"Technicien added Succesfully",null);
        }
        return new Output(false,"Technicien Already Exists",null);
    }
    public Output removeTechnicien(int idR){
        Technicien Technicien = (Technicien) persTechnicien.getTechnicien(idR);
        if (Technicien != null) {
            persTechnicien.remove(Technicien);
            return new Output(true,"Technicien removed",null);
        } else {
            return new Output(false,"Technicien not found",null);
        }
    }
    public Output viewTechnicien(int idR) {
        Technicien technicien = (Technicien) persTechnicien.getTechnicien(idR);
        if (technicien != null) {
            return new Output(true,"",technicien);
        } else {
            return new Output(false,"Technicien not found!",null);
        }
    }

    public Output listAllTechniciens() {
        return new Output(true,"--- ALL TechnicienS ---\n",persTechnicien.getAll());
    }
    public Output modifyTechnicien(int idT,Technicien t){
        if(persTechnicien.modify(idT,t)){
            return new Output(true,"Technicien modified Succesfully",null);
        }
        return new Output(false,"Technicien not found",null);
    }
}
