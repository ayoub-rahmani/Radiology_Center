package ihm;

import entity.Radiologue;

import java.util.Scanner;

public class RadiologueIhm {
    public static int RadiologueManagementMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- RADIOLOGUE MANAGEMENT ---");
            System.out.println("1. Add Radiologue");
            System.out.println("2. Remove Radiologue");
            System.out.println("3. View Radiologue");
            System.out.println("4. List All Radiologue");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
        return choice;
    }

    public static Radiologue addRadiologue() {
        System.out.println("Enter Radiologue Details:");
        System.out.print("First Name: ");
        String prenom = Verification.getValidName();
        System.out.print("Last Name: ");
        String nom = Verification.getValidName();
        System.out.print("Phone Number: ");
        int numTelephone = Verification.getValidPhoneNumber();
        System.out.print("Specialty: ");
        String specialite = Verification.getValidName();

        return new Radiologue(nom, prenom, numTelephone, specialite,0);
    }

    public static int removeRadiologue() {
        System.out.print("Enter Radiologue ID to remove: ");
        return Verification.getValidID();
    }

    public static int viewRadiologue() {
        System.out.print("Enter Radiologue ID to view: ");
        return Verification.getValidID();
    }

}
