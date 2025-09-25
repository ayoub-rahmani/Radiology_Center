package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Categorie;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersCategorie {
    private static final String CATEGORIES_FILE = "categories.json";
    private Gson gson;

    public PersCategorie() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void add(Categorie c) {
        if (!(c instanceof Categorie)) {
            throw new IllegalArgumentException("Only Categorie objects can be added");
        }

        try {
            List<Categorie> categorieList = readCategories();
            categorieList.add(c);
            writeCategories(categorieList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(Categorie c) {
        if (!(c instanceof Categorie)) {
            throw new IllegalArgumentException("Only Categorie objects can be removed");
        }

        try {
            List<Categorie> categorieList = readCategories();
            categorieList.removeIf(existingCategorie ->
                    existingCategorie.getNom().equals(c.getNom()));
            writeCategories(categorieList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Categorie getCategorie(String nom) {
        try {
            List<Categorie> categorieList = readCategories();
            return categorieList.stream()
                    .filter(categorie -> categorie.getNom().equals(nom))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categorie> getAll() {
        try {
            List<Categorie> categorieList = readCategories();
            return !categorieList.isEmpty() ? categorieList : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Updated update method
    public void update(String nom, Categorie updatedCategorie) {
        try {
            List<Categorie> categorieList = readCategories();
            for (int i = 0; i < categorieList.size(); i++) {
                if (categorieList.get(i).getNom().equals(nom)) {
                    categorieList.set(i, updatedCategorie);
                    break;
                }
            }
            writeCategories(categorieList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Categorie> readCategories() throws IOException {
        try (FileReader reader = new FileReader(CATEGORIES_FILE)) {
            Type categorieListType = new TypeToken<ArrayList<Categorie>>(){}.getType();
            List<Categorie> categories = gson.fromJson(reader, categorieListType);
            return categories != null ? categories : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeCategories(List<Categorie> categorieList) throws IOException {
        try (FileWriter writer = new FileWriter(CATEGORIES_FILE)) {
            gson.toJson(categorieList, writer);
        }
    }

    public List<Categorie> getAllCategories() {
        try {
            return readCategories();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}