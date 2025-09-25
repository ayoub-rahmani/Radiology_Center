package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersExamen {
    private PersRendezVous persRendezVous=new PersRendezVous();
    private static final String EXAMENS_FILE = "examens.json";
    private Gson gson;

    public PersExamen() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void add(Examen examen) {
        try {
            List<Examen> examensList = readExamens();
            examensList.add(examen);
            writeExamens(examensList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(int idRDV) {
        try {
            List<Examen> examensList = readExamens();
            examensList.removeIf(examen -> examen.getIdRDV()==(idRDV));
            writeExamens(examensList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Examen getExamen(int idRDV) {
        try {
            List<Examen> examensList = readExamens();
            return examensList.stream()
                    .filter(examen -> examen.getIdRDV()==(idRDV))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Examen> getExamensByPatient(int CIN) {
        try {
            List<Examen> examensList = readExamens();
            return examensList.stream()
                    .filter(examen -> examen.getPatient().getCIN() == CIN)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Examen> getAllExamens() {
        try {
            return readExamens();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void updateExamen(Examen updatedExamen) {
        try {
            List<Examen> examensList = readExamens();
            examensList = examensList.stream()
                    .map(examen -> examen.getIdRDV()==(updatedExamen.getIdRDV()) ? updatedExamen : examen)
                    .collect(Collectors.toList());
            writeExamens(examensList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Examen> getExamensByCategorie(String categorie) {
        try {
            List<Examen> examensList = readExamens();
            return examensList.stream()
                    .filter(examen -> examen.getCategorie().getNom().equalsIgnoreCase(categorie))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Examen> getExamensByRadiologue(int idR) {
        try {
            List<Examen> examensList = readExamens();
            return examensList.stream()
                    .filter(examen -> examen.getRadiologue().getIdR()==(idR))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public boolean modify(int idRv, Examen updatedExamen) {
        try {
            List<Examen> examensList = readExamens();

            for (int i = 0; i < examensList.size(); i++) {
                Examen existingExamen = examensList.get(i);

                if (existingExamen.getIdRDV() == idRv) {
                    existingExamen.setCategorie(updatedExamen.getCategorie());
                    existingExamen.setRadiologue(updatedExamen.getRadiologue());
                    existingExamen.setSalle(updatedExamen.getSalle());
                    existingExamen.setRapport(updatedExamen.getRapport());
                    existingExamen.setPatient(updatedExamen.getPatient());

                    writeExamens(examensList);
                    return true;
                }
            }

            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Examen> getExamensByRendezVousState(String state) {
        try {
            List<RendezVous> filteredRendezVous = persRendezVous.getRendezVousByState(state);

            List<Examen> examensList = readExamens();

            return examensList.stream()
                    .filter(examen ->
                            filteredRendezVous.stream()
                                    .anyMatch(rdv -> rdv.getIdRv() == (examen.getIdRDV())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    private List<Examen> readExamens() throws IOException {
        try (FileReader reader = new FileReader(EXAMENS_FILE)) {
            Type examenListType = new TypeToken<ArrayList<Examen>>(){}.getType();
            List<Examen> examens = gson.fromJson(reader, examenListType);
            return examens != null ? examens : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeExamens(List<Examen> examensList) throws IOException {
        try (FileWriter writer = new FileWriter(EXAMENS_FILE)) {
            gson.toJson(examensList, writer);
        }
    }
}