package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Calendrier;
import entity.Radiologue;
import entity.Salle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersCalendrier {
    private static final String CALENDRIER_FILE = "calendrier.json";
    private Gson gson;

    public PersCalendrier() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void add(Calendrier calendrier) {
        try {
            List<Calendrier> calendrierList = readCalendriers();
            calendrierList.add(calendrier);
            writeCalendriers(calendrierList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Calendrier> getCalendrierByRadiologue(Radiologue radiologue) {
        try {
            List<Calendrier> calendrierList = readCalendriers();
            return calendrierList.stream()
                    .filter(c -> c.getRadiologue().getIdR() == radiologue.getIdR())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<Calendrier> getCalendrierBySalle(Salle salle) {
        try {
            List<Calendrier> calendrierList = readCalendriers();
            return calendrierList.stream()
                    .filter(c -> c.getSalle().getNum() == salle.getNum())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Calendrier> getAllCalendriers() {
        try {
            return readCalendriers();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Calendrier> readCalendriers() throws IOException {
        try (FileReader reader = new FileReader(CALENDRIER_FILE)) {
            Type calendrierListType = new TypeToken<ArrayList<Calendrier>>(){}.getType();
            List<Calendrier> calendriers = gson.fromJson(reader, calendrierListType);
            return calendriers != null ? calendriers : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeCalendriers(List<Calendrier> calendrierList) throws IOException {
        try (FileWriter writer = new FileWriter(CALENDRIER_FILE)) {
            gson.toJson(calendrierList, writer);
        }
    }
}