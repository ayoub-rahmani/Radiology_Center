package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Finance;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersFinance {
    private static final String FINANCE_FILE = "finance.json";
    private final Gson gson;

    public PersFinance() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Finance getFinance() {
        try (FileReader reader = new FileReader(FINANCE_FILE)) {
            Finance finance = gson.fromJson(reader, Finance.class);
            return finance != null ? finance : new Finance();
        } catch (IOException e) {
            return new Finance();
        }
    }

    public void saveFinance(Finance finance) {
        try (FileWriter writer = new FileWriter(FINANCE_FILE)) {
            gson.toJson(finance, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFLous(double flous) {
        Finance finance = getFinance();
        finance.setRevenue(finance.getRevenue() + flous);
        saveFinance(finance);
    }

    public double getDepenses() {
        return getFinance().getDepense();
    }

    public double getRevenues() {
        return getFinance().getRevenue();
    }

}