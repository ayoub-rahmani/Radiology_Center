package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.RendezVous;
import entity.Patient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersRendezVous {
    private static final String RENDEZVOUS_FILE = "rendezvous.json";
    private Gson gson;

    public PersRendezVous() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void add(RendezVous rdv) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            rendezvousList.add(rdv);
            writeRendezVous(rendezvousList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelRendezVous(int idRv) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            rendezvousList = rendezvousList.stream()
                    .map(rdv -> {
                        if (rdv.getIdRv() == idRv) {
                            rdv.setState("Canceled");
                        }
                        return rdv;
                    })
                    .collect(Collectors.toList());
            writeRendezVous(rendezvousList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RendezVous getRendezVous(int idRv) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            return rendezvousList.stream()
                    .filter(rdv -> rdv.getIdRv() == idRv)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RendezVous> getRendezVousByPatient(int CIN) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            return rendezvousList.stream()
                    .filter(rdv -> rdv.getPatient().getCIN() == CIN)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<RendezVous> getAllRendezVous() {
        try {
            return readRendezVous();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void updateRendezVous(RendezVous updatedRdv) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            rendezvousList = rendezvousList.stream()
                    .map(rdv -> rdv.getIdRv() == updatedRdv.getIdRv() ? updatedRdv : rdv)
                    .collect(Collectors.toList());
            writeRendezVous(rendezvousList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RendezVous> getRendezVousByState(String state) {
        try {
            List<RendezVous> rendezvousList = readRendezVous();
            return rendezvousList.stream()
                    .filter(rdv -> rdv.getState().equalsIgnoreCase(state))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<RendezVous> readRendezVous() throws IOException {
        try (FileReader reader = new FileReader(RENDEZVOUS_FILE)) {
            Type rendezVousListType = new TypeToken<ArrayList<RendezVous>>(){}.getType();
            List<RendezVous> rendezVous = gson.fromJson(reader, rendezVousListType);
            return rendezVous != null ? rendezVous : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeRendezVous(List<RendezVous> rendezvousList) throws IOException {
        try (FileWriter writer = new FileWriter(RENDEZVOUS_FILE)) {
            gson.toJson(rendezvousList, writer);
        }
    }

    public boolean hasPendingRendezVous(int CIN) {
        List<RendezVous> patientRdvs = getRendezVousByPatient(CIN);
        return patientRdvs.stream()
                .anyMatch(rdv -> "Waiting".equalsIgnoreCase(rdv.getState()));
    }

}