package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Patient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersPatient{
    private static final String PATIENTS_FILE = "patients.json";
    private Gson gson;

    public PersPatient() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
    
    public void add(Patient p) {
        if (!(p instanceof Patient)) {
            throw new IllegalArgumentException("Only Patient objects can be added");
        }

        try {
            List<Patient> patientList = readPatients();

            patientList.add((Patient) p);

            writePatients(patientList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean modify(int CIN, Patient updatedPatient) {
        try {
            List<Patient> patientList = readPatients();

            for (int i = 0; i < patientList.size(); i++) {
                Patient existingPatient = patientList.get(i);

                if (existingPatient.getCIN() == CIN) {
                    existingPatient.setNom(updatedPatient.getNom());
                    existingPatient.setPrenom(updatedPatient.getPrenom());
                    existingPatient.setNumTelephone(updatedPatient.getNumTelephone());
                    existingPatient.setAdresse(updatedPatient.getAdresse());
                    existingPatient.setDateDeNaissance(updatedPatient.getDateDeNaissance());
                    existingPatient.setDossier(updatedPatient.getDossier());
                    writePatients(patientList);
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePlaceholder(int id) {
        try {
            List<Patient> patientList = readPatients();

            Patient patientToUpdate = patientList.stream()
                    .filter(patient -> patient.getCIN() == id)
                    .findFirst()
                    .orElse(null);

            if (patientToUpdate != null) {
                System.out.println("Patient found: " + patientToUpdate.getNom());

                writePatients(patientList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove(Patient p) {
        if (!(p instanceof Patient)) {
            throw new IllegalArgumentException("Only Patient objects can be removed");
        }

        try {
            List<Patient> patientList = readPatients();
            Patient patient = (Patient) p;

            patientList.removeIf(existingPatient -> existingPatient.getCIN() == patient.getCIN());

            writePatients(patientList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Patient getPatient(int id) {
        try {
            List<Patient> patientList = readPatients();

            return patientList.stream()
                    .filter(patient -> patient.getCIN() == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Patient> getAll() {
        try {
            List<Patient> patientList = readPatients();
            return !patientList.isEmpty() ? patientList : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(int id) {
        try {
            List<Patient> patientList = readPatients();
            Patient patientToUpdate = patientList.stream()
                    .filter(patient -> patient.getCIN() == id)
                    .findFirst()
                    .orElse(null);

            if (patientToUpdate != null) {
                System.out.println("Patient found: " + patientToUpdate.getNom());

                writePatients(patientList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Patient> readPatients() throws IOException {
        try (FileReader reader = new FileReader(PATIENTS_FILE)) {
            Type patientListType = new TypeToken<ArrayList<Patient>>(){}.getType();

            List<Patient> patients = gson.fromJson(reader, patientListType);
            return patients != null ? patients : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writePatients(List<Patient> patientList) throws IOException {
        try (FileWriter writer = new FileWriter(PATIENTS_FILE)) {
            gson.toJson(patientList, writer);
        }
    }

    public List<Patient> getAllPatients() {
        try {
            return readPatients();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}