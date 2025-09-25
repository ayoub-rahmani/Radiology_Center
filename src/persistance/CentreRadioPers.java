package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.CentreRadio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CentreRadioPers {
    private static final String CENTRES_FILE = "centres_radiologie.json";
    private final Gson gson;

    public CentreRadioPers() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public CentreRadio getDefaultCentre() {
        try {
            List<CentreRadio> centresList = readCentres();
            return centresList.isEmpty() ? null : centresList.get(0); // Return the first centre or null
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void modifyNom(String nouveauNom) {
        try {
            List<CentreRadio> centresList = readCentres();

            if (centresList.isEmpty()) {
                // Create a new Centre de Radiologie if none exist
                CentreRadio newCentre = new CentreRadio();
                newCentre.setNom(nouveauNom);
                centresList.add(newCentre);
                System.out.println("New Centre de Radiologie created with the provided name.");
            } else {
                // Modify the first centre's name
                centresList.get(0).setNom(nouveauNom);
            }

            writeCentres(centresList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyAddress(String nouvelleAdresse) {
        try {
            List<CentreRadio> centresList = readCentres();

            if (centresList.isEmpty()) {
                // Create a new Centre de Radiologie if none exist
                CentreRadio newCentre = new CentreRadio();
                newCentre.setAdresse(nouvelleAdresse);
                centresList.add(newCentre);
                System.out.println("New Centre de Radiologie created with the provided address.");
            } else {
                // Modify the first centre's address
                centresList.get(0).setAdresse(nouvelleAdresse);
            }

            writeCentres(centresList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyMail(String nouveauMail) {
        try {
            List<CentreRadio> centresList = readCentres();

            if (centresList.isEmpty()) {
                // Create a new Centre de Radiologie if none exist
                CentreRadio newCentre = new CentreRadio();
                newCentre.setMail(nouveauMail);
                centresList.add(newCentre);
                System.out.println("New Centre de Radiologie created with the provided mail.");
            } else {
                // Modify the first centre's mail
                centresList.get(0).setMail(nouveauMail);
            }

            writeCentres(centresList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyNum(int nouveauNum) {
        try {
            List<CentreRadio> centresList = readCentres();

            if (centresList.isEmpty()) {
                // Create a new Centre de Radiologie if none exist
                CentreRadio newCentre = new CentreRadio();
                newCentre.setNum(nouveauNum);
                centresList.add(newCentre);
                System.out.println("New Centre de Radiologie created with the provided number.");
            } else {
                // Modify the first centre's number
                centresList.get(0).setNum(nouveauNum);
            }

            writeCentres(centresList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<CentreRadio> readCentres() throws IOException {
        try (FileReader reader = new FileReader(CENTRES_FILE)) {
            Type centreListType = new TypeToken<ArrayList<CentreRadio>>() {}.getType();
            List<CentreRadio> centres = gson.fromJson(reader, centreListType);
            return centres != null ? centres : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeCentres(List<CentreRadio> centresList) throws IOException {
        try (FileWriter writer = new FileWriter(CENTRES_FILE)) {
            gson.toJson(centresList, writer);
        }
    }
}
