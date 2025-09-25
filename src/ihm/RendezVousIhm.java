package ihm;

import entity.*;

import java.util.Scanner;

public class RendezVousIhm {
    public static int RdvManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Rendez-Vous MANAGEMENT ---");
        System.out.println("1. Add Rendez-Vous");
        System.out.println("2. Cancel Rendez-Vous ");
        System.out.println("3. View Rendez-Vous");
        System.out.println("4. List All Rendez-Vous");
        System.out.println("5. Show Rendez-Vous by Patient");
        System.out.println("6. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static RendezVous addRendezVous() {
        Scanner scanner = new Scanner(System.in);
        Patient patient=PatientIhm.addPatient();
        System.out.println("Treatment type in The Prescription  ( (From the list above :) ): ");
        Prescription prescription=generatePrescription();
        return new RendezVous(0,patient,prescription);
    }
    private static Prescription generatePrescription() {
        System.out.println("Enter the traitement name:");
        String type = Verification.getValidName();
        Medecin medecin = MedecinIhm.addMedecin();
        return new Prescription(type, medecin);
    }
    public static int removeRendezVous() {
        System.out.print("Enter Rdv ID to cancel: ");
        return Verification.getValidID();
    }

    public static int viewRendezVous() {
        System.out.print("Enter Rdv ID to view: ");
        return Verification.getValidID();
    }
}