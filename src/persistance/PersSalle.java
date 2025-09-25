package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Salle;
import entity.Technicien;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersSalle {
    private static final String SALLES_FILE = "salles.json";
    private Gson gson;
    public PersSalle() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public boolean add(Salle s) {
        try {List<Salle> salleList = readSalles();
            boolean exists = salleList.stream()
                    .anyMatch(existingSalle -> existingSalle.getNum() == s.getNum());
            if (!exists) {
                salleList.add(s);
                writeSalles(salleList);
                return true;
            } else {
                //System.out.println("Salle with number " + s.getNum() + " already exists.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(int salleNum) {
        try {
            List<Salle> salleList = readSalles();
            boolean removed = salleList.removeIf(salle -> salle.getNum() == salleNum);
            if (removed) {
                writeSalles(salleList);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Salle getSalle(int salleNum) {
        try {
            List<Salle> salleList = readSalles();
            return salleList.stream()
                    .filter(salle -> salle.getNum() == salleNum)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Salle> getAll() {
        try {
            List<Salle> salleList = readSalles();
            return !salleList.isEmpty() ? salleList : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addTechnicienToSalle(int salleNum, Technicien technicien) {
        try {
            List<Salle> salleList = readSalles();

            Salle salle = salleList.stream()
                    .filter(s -> s.getNum() == salleNum)
                    .findFirst()
                    .orElse(null);

            if (salle != null) {
                if (salle.getTechnicien() == null) {
                    salle.setTechnicien(new Technicien[]{technicien});
                } else {
                    Technicien[] currentTechniciens = salle.getTechnicien();
                    Technicien[] newTechniciens = new Technicien[currentTechniciens.length + 1];
                    System.arraycopy(currentTechniciens, 0, newTechniciens, 0, currentTechniciens.length);
                    newTechniciens[currentTechniciens.length] = technicien;
                    salle.setTechnicien(newTechniciens);
                }
                writeSalles(salleList);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeTechnicienFromSalle(int salleNum, int technicienId) {
        try {
            List<Salle> salleList = readSalles();

            Salle salle = salleList.stream()
                    .filter(s -> s.getNum() == salleNum)
                    .findFirst()
                    .orElse(null);

            if (salle != null && salle.getTechnicien() != null) {
                Technicien[] updatedTechniciens = java.util.Arrays.stream(salle.getTechnicien())
                        .filter(t -> t.getIdT() != technicienId)
                        .toArray(Technicien[]::new);
                salle.setTechnicien(updatedTechniciens);
                writeSalles(salleList);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read salles from JSON file
    private List<Salle> readSalles() throws IOException {
        try (FileReader reader = new FileReader(SALLES_FILE)) {
            Type salleListType = new TypeToken<ArrayList<Salle>>(){}.getType();
            List<Salle> salles = gson.fromJson(reader, salleListType);
            return salles != null ? salles : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeSalles(List<Salle> salleList) throws IOException {
        try (FileWriter writer = new FileWriter(SALLES_FILE)) {
            gson.toJson(salleList, writer);
        }
    }
}