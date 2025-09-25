package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Medecin;
import entity.Medecin;
import entity.Patient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersMedecin{
    private static final String MEDECINS_FILE = "medecins.json";
    private Gson gson;

    public PersMedecin() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public void add(Medecin p) {
        if (!(p instanceof Medecin)) {
            throw new IllegalArgumentException("Only Medecin objects can be added");
        }

        try {
            List<Medecin> medecinList = readMedecins();
            medecinList.add((Medecin) p);
            writeMedecins(medecinList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void remove(Medecin p) {
        if (!(p instanceof Medecin)) {
            throw new IllegalArgumentException("Only Medecin objects can be removed");
        }

        try {
            List<Medecin> medecinList = readMedecins();
            Medecin medecin = (Medecin) p;
            medecinList.removeIf(existingMedecin -> existingMedecin.getIdM()==(medecin.getIdM()));
            writeMedecins(medecinList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Medecin getMedecin(int id) {
        try {
            List<Medecin> medecinList = readMedecins();
            return medecinList.stream()
                    .filter(medecin -> Integer.parseInt(String.valueOf(medecin.getIdM())) == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Medecin getAll() {
        try {
            List<Medecin> medecinList = readMedecins();
            return !medecinList.isEmpty() ? medecinList.get(0) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public void update(int id) {
        try {
            List<Medecin> medecinList = readMedecins();
            Medecin medecinToUpdate = medecinList.stream()
                    .filter(medecin -> Integer.parseInt(String.valueOf(medecin.getIdM())) == id)
                    .findFirst()
                    .orElse(null);

            if (medecinToUpdate != null) {
                System.out.println("Medecin found: " + medecinToUpdate.getNom());
                writeMedecins(medecinList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Medecin> readMedecins() throws IOException {
        try (FileReader reader = new FileReader(MEDECINS_FILE)) {
            Type medecinListType = new TypeToken<ArrayList<Medecin>>(){}.getType();
            List<Medecin> medecins = gson.fromJson(reader, medecinListType);
            return medecins != null ? medecins : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeMedecins(List<Medecin> medecinList) throws IOException {
        try (FileWriter writer = new FileWriter(MEDECINS_FILE)) {
            gson.toJson(medecinList, writer);
        }
    }
    public List<Medecin> getAllMedecin() {
        try {
            return readMedecins();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
