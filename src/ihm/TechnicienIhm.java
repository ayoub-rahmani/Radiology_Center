package ihm;

import entity.Technicien;

import java.util.Scanner;

public class TechnicienIhm {
    public static int TechnicienManagementMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- Technicien MANAGEMENT ---");
            System.out.println("1. Add Technicien");
            System.out.println("2. Remove Technicien");
            System.out.println("3. View Technicien");
            System.out.println("4. List All Technicien");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
    }

    public static Technicien addTechnicien() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Technicien Details:");
        System.out.print("First Name: ");
        String prenom = Verification.getValidName();
        System.out.print("Last Name: ");
        String nom = Verification.getValidName();
        System.out.print("Phone Number: ");
        int numTelephone = Verification.getValidPhoneNumber();
        System.out.print("Num Salle: ");
        int numSalle = Verification.getValidID();
        return new Technicien(nom, prenom, numTelephone, 0 ,numSalle);
    }

    public static int removeTechnicien() {
        System.out.print("Enter Technicien ID to remove: ");
        return Verification.getValidID();
    }
    public static int viewTechnicien() {
        System.out.print("Enter Technicien ID to view: ");
        return Verification.getValidID();
    }
}
