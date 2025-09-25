package ihm;

import entity.Categorie;
import java.util.Scanner;

public class CategorieIhm {
    public static int CategorieManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- CATEGORY MANAGEMENT ---");
        System.out.println("1. Add Category");
        System.out.println("2. Remove Category");
        System.out.println("3. View Category");
        System.out.println("4. List All Categories");
        System.out.println("5. Modify Category");
        System.out.println("6. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static Categorie addCategorie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Category Details:");
        System.out.print("Name: ");
        String nom = Verification.getValidName();
        System.out.print("Price: ");
        double prix = Verification.getValidPrice();

        return new Categorie(nom, prix);
    }

    public static String removeCategorie() {
        System.out.print("Enter Category name to remove: ");
        return Verification.getValidName();
    }

    public static String viewCategorie() {
        System.out.print("Enter Category name to view: ");
        return Verification.getValidName();
    }

    public static Categorie modifyCategorie(Categorie existingCategorie) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("New Name (current: " + existingCategorie.getNom() + "): ");
        String newNom = scanner.nextLine();
        if (newNom.isEmpty()) {
            newNom = existingCategorie.getNom();
        }

        System.out.print("New Price (current: " + existingCategorie.getPrix() + "): ");
        String priceInput = scanner.nextLine();
        double newPrix = priceInput.isEmpty() ? existingCategorie.getPrix() : Double.parseDouble(priceInput);

        return new Categorie(newNom, newPrix);
    }
}