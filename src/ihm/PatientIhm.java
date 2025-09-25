package ihm;

import entity.Categorie;
import entity.Dossier;
import entity.Patient;

import java.util.Scanner;

public class PatientIhm {
    public static int  PatientManagementMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- Patient MANAGEMENT ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Remove Patient");
            System.out.println("3. Modify Patient");
            System.out.println("4. View Patient");
            System.out.println("5. List All Patient");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return choice;
    }

    public static Patient addPatient() {
        System.out.println("Enter Patient Details:");
        System.out.print("First Name: ");
        String prenom = Verification.getValidName();
        System.out.print("Last Name: ");
        String nom = Verification.getValidName();
        System.out.print("Phone Number: ");
        int numTelephone = Verification.getValidPhoneNumber();
        System.out.print("Adresse: ");
        String adresse = Verification.getValidAddress();
        System.out.print("Patient CIN: ");
        int cin = Verification.getValidCIN();
        System.out.print("Date De Naissance (DD/MM/YYYY) : ");
        String dateDeNaissance = Verification.getValidDateOfBirth();

        return new Patient(nom, prenom, numTelephone, adresse ,cin, dateDeNaissance,new Dossier());
    }

    public static int removePatient() {
        System.out.print("Enter Patient CIN to remove: ");
        int cin = Verification.getValidCIN();

        return cin;
    }

    public static int viewPatient() {
        System.out.print("Enter Patient CIN : ");
        int cin = Verification.getValidCIN();

        return cin;
    }
    public static Patient modifyPatient(Patient existingPatient) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("First Name: ");
        String prenom = Verification.getValidName();
        System.out.print("Last Name: ");
        String nom = Verification.getValidName();
        System.out.print("Phone Number: ");
        int numTelephone = Verification.getValidPhoneNumber();
        System.out.print("Adresse: ");
        String adresse = Verification.getValidAddress();
        System.out.print("Date De Naissance (DD/MM/YYYY) : ");
        String dateDeNaissance = Verification.getValidDateOfBirth();

        return new Patient(nom, prenom,numTelephone,adresse,existingPatient.getCIN(),dateDeNaissance,existingPatient.getDossier());
    }

}
