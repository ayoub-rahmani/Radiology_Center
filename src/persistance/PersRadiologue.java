package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Radiologue;
import entity.Radiologue;
import entity.Personne;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersRadiologue {
    private static final String RADIOLOGUES_FILE = "radiologues.json";
    private Gson gson;

    public PersRadiologue() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    
    public void add(Personne p) {
        if (!(p instanceof Radiologue)) {
            throw new IllegalArgumentException("Only Radiologue objects can be added");
        }

        try {
            List<Radiologue> radiologueList = readRadiologues();
            radiologueList.add((Radiologue) p);
            writeRadiologues(radiologueList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void remove(Personne p) {
        if (!(p instanceof Radiologue)) {
            throw new IllegalArgumentException("Only Radiologue objects can be removed");
        }

        try {
            List<Radiologue> radiologueList = readRadiologues();
            Radiologue radiologue = (Radiologue) p;
            radiologueList.removeIf(existingRadiologue -> existingRadiologue.getIdR() == (radiologue.getIdR()));
            writeRadiologues(radiologueList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public Personne getRadiologue(int id) {
        try {
            List<Radiologue> radiologueList = readRadiologues();
            return radiologueList.stream()
                    .filter(radiologue -> Integer.parseInt(String.valueOf(radiologue.getIdR())) == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public Personne getAll() {
        try {
            List<Radiologue> radiologueList = readRadiologues();
            return !radiologueList.isEmpty() ? radiologueList.get(0) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public void update(int id) {
        try {
            List<Radiologue> radiologueList = readRadiologues();
            Radiologue radiologueToUpdate = radiologueList.stream()
                    .filter(radiologue -> Integer.parseInt(String.valueOf(radiologue.getIdR())) == id)
                    .findFirst()
                    .orElse(null);

            if (radiologueToUpdate != null) {
                System.out.println("Radiologue found: " + radiologueToUpdate.getNom());
                writeRadiologues(radiologueList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Radiologue> readRadiologues() throws IOException {
        try (FileReader reader = new FileReader(RADIOLOGUES_FILE)) {
            Type radiologueListType = new TypeToken<ArrayList<Radiologue>>(){}.getType();
            List<Radiologue> radiologues = gson.fromJson(reader, radiologueListType);
            return radiologues != null ? radiologues : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeRadiologues(List<Radiologue> radiologueList) throws IOException {
        try (FileWriter writer = new FileWriter(RADIOLOGUES_FILE)) {
            gson.toJson(radiologueList, writer);
        }
    }
    public List<Radiologue> getAllRadiologue() {
        try {
            return readRadiologues();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}