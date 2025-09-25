package ihm;

import entity.Medecin;

import java.util.Scanner;

public class MedecinIhm {
    public static int MedecinManagementMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- Medecin MANAGEMENT ---");
            System.out.println("1. Add Medecin");
            System.out.println("2. Remove Medecin");
            System.out.println("3. View Medecin");
            System.out.println("4. List All Medecin");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return choice;
    }

    public static Medecin addMedecin() {
        System.out.println("Enter Medecin Details:");
        System.out.print("First Name: ");
        String prenom = Verification.getValidName();
        System.out.print("Last Name: ");
        String nom = Verification.getValidName();
        System.out.print("Phone Number: ");
        int numTelephone = Verification.getValidPhoneNumber();
        System.out.print("Medecin ID: ");
        int idM = Verification.getValidID();
        System.out.print("Specialite: ");
        String specialite = Verification.getValidName();
        System.out.print("Email : ");
        String contact = Verification.getValidEmail();

        return new Medecin(nom, prenom, numTelephone, idM ,specialite, contact);
    }

    public static int removeMedecin() {
        System.out.print("Enter Medecin ID to remove: ");
        return Verification.getValidID();
    }

    public static int viewMedecin() {
        System.out.print("Enter Medecin ID to view: ");
        return Verification.getValidID();
    }

}
