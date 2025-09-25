package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Patient;
import entity.Radiologue;
import entity.Technicien;
import entity.Personne;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersTechnicien {
    private static final String TECHNICIENS_FILE = "techniciens.json";
    private Gson gson;

    public PersTechnicien() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    
    public void add(Personne p) {
        if (!(p instanceof Technicien)) {
            throw new IllegalArgumentException("Only Technicien objects can be added");
        }

        try {
            List<Technicien> technicienList = readTechniciens();
            technicienList.add((Technicien) p);
            writeTechniciens(technicienList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean modify(int idT, Technicien updatedTechnicien) {
        try {
            List<Technicien> technicienList = readTechniciens();

            for (int i = 0; i < technicienList.size(); i++) {
                Technicien existingTechnicien = technicienList.get(i);

                if (existingTechnicien.getIdT() == idT) {
                    existingTechnicien.setNom(updatedTechnicien.getNom());
                    existingTechnicien.setPrenom(updatedTechnicien.getPrenom());
                    existingTechnicien.setNumTelephone(updatedTechnicien.getNumTelephone());
                    existingTechnicien.setSalle_attribuer(updatedTechnicien.getSalle_attribuer());

                    writeTechniciens(technicienList);
                    System.out.println("Technicien updated successfully.");
                    return true;
                }
            }

            System.out.println("Technicien not found.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePlaceholder(int id) {
        try {
            List<Technicien> technicienList = readTechniciens();

            Technicien technicienToUpdate = technicienList.stream()
                    .filter(technicien -> technicien.getIdT() == id)
                    .findFirst()
                    .orElse(null);

            if (technicienToUpdate != null) {
                System.out.println("Technicien found: " + technicienToUpdate.getNom());

                writeTechniciens(technicienList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove(Personne p) {
        if (!(p instanceof Technicien)) {
            throw new IllegalArgumentException("Only Technicien objects can be removed");
        }

        try {
            List<Technicien> technicienList = readTechniciens();
            Technicien technicien = (Technicien) p;
            technicienList.removeIf(existingTechnicien -> existingTechnicien.getIdT() == technicien.getIdT());
            writeTechniciens(technicienList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public Personne getTechnicien(int id) {
        try {
            List<Technicien> technicienList = readTechniciens();
            return technicienList.stream()
                    .filter(technicien -> technicien.getIdT() == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public List<Technicien> getAll() {
        try {
            List<Technicien> technicienList = readTechniciens();
            return !technicienList.isEmpty() ? technicienList : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public void update(int id) {
        try {
            List<Technicien> technicienList = readTechniciens();
            Technicien technicienToUpdate = technicienList.stream()
                    .filter(technicien -> technicien.getIdT() == id)
                    .findFirst()
                    .orElse(null);

            if (technicienToUpdate != null) {
                System.out.println("Technicien found: " + technicienToUpdate.getNom());
                writeTechniciens(technicienList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Technicien> readTechniciens() throws IOException {
        try (FileReader reader = new FileReader(TECHNICIENS_FILE)) {
            Type technicienListType = new TypeToken<ArrayList<Technicien>>(){}.getType();
            List<Technicien> techniciens = gson.fromJson(reader, technicienListType);
            return techniciens != null ? techniciens : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeTechniciens(List<Technicien> technicienList) throws IOException {
        try (FileWriter writer = new FileWriter(TECHNICIENS_FILE)) {
            gson.toJson(technicienList, writer);
        }
    }
    public List<Technicien> getAllTechnicien() {
        try {
            return readTechniciens();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}