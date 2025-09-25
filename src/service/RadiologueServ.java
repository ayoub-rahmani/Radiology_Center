package service;

import entity.Radiologue;
import ihm.Output;
import persistance.PersRadiologue;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RadiologueServ {
    CategorieServ categorieServ=new CategorieServ();
    private Set<Integer> usedIds = new HashSet<>();
    PersRadiologue persRadiologue=new PersRadiologue();
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
        }List<Radiologue> existingRadiologues = persRadiologue.getAllRadiologue();
        return existingRadiologues.stream()
                .noneMatch(rdv -> rdv.getIdR() == id);
    }
    public Output addRadiologue(Radiologue p) {
        p.setIdR(generateUniqueId());
        if(!categorieServ.viewCategorie(p.getSpecialite()).istrue()){
            categorieServ.listAllCategories();
            return new Output(false,"Invalid Specialite for !(Choose from the list above)",null);
        }
        if(viewRadiologue(p.getIdR()).getObj()==null) {
            persRadiologue.add(p);
            return new Output(true,"Radiologue added Succesfully ! ",null);
        }
        return new Output(false,"Radiologue already exists ! ",null);

    }
    public Output removeRadiologue(int idR){
        Radiologue Radiologue = (Radiologue) persRadiologue.getRadiologue(idR);
        if (Radiologue != null) {
            persRadiologue.remove(Radiologue);
            return new Output(true,"Radiologue removed Succesfully ! ",null);
        } else {
            return new Output(false,"Radiologue does not exist ! ",null);
        }
    }
    public Output viewRadiologue(int idR) {
        Radiologue radiologue = (Radiologue) persRadiologue.getRadiologue(idR);
        if (radiologue != null) {
            return new Output(true,"",radiologue);
        } else {
            return new Output(false,"Radiologue not found!",null);
        }
    }

    public Output listAllRadiologues() {
        return new Output(true,"--- ALL RadiologueS ---\n",persRadiologue.getAllRadiologue());
    }
}
