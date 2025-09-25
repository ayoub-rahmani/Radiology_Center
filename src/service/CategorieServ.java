package service;

import entity.Categorie;
import ihm.Output;
import persistance.PersCategorie;

import java.util.List;

public class CategorieServ {
    PersCategorie persCategorie = new PersCategorie();

    public Output addCategorie(Categorie c) {
        if (c != null) {
            if (persCategorie.getCategorie(c.getNom()) != null) {
                return new Output(false,"Category with this name already exists!",null);
            }
            persCategorie.add(c);
            return new Output(true,"Category added successfully!",null);
        }
        return new Output(false,"",null);
    }

    public Output removeCategorie(String nom) {
        Categorie categorie = persCategorie.getCategorie(nom);
        if (categorie != null) {
            persCategorie.remove(categorie);
            return new Output(true,"Category removed successfully!",null);
        } else {
            return new Output(false,"Category not found!",null);
        }
    }

    public Output viewCategorie(String nom) {
        Categorie categorie = persCategorie.getCategorie(nom);
        if (categorie != null) {
            return new Output(true,"",categorie);
        } else {
            return new Output(false,"Category not found!",null);
        }
    }

    public Output listAllCategories() {
        System.out.println("--- ALL CATEGORIES ---");
        List<Categorie> categories = persCategorie.getAllCategories();
        if (categories.isEmpty()) {
            return new Output(false,"No categories found!",null);
        }
        return new Output(true,"",categories);
    }

    public Output modifyCategorie(String nom, Categorie updatedCategorie) {
        Categorie existingCategorie = persCategorie.getCategorie(nom);
        if (existingCategorie != null) {
            if (!nom.equals(updatedCategorie.getNom()) &&
                    persCategorie.getCategorie(updatedCategorie.getNom()) != null) {
                return new Output(false,"Category with new name already exists!",null);

            }
            persCategorie.update(nom, updatedCategorie);
            return new Output(true,"Category modified successfully!",null);
        }
        return new Output(false,"Category not found!",null);
    }
}