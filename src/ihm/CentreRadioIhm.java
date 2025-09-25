package ihm;

import java.util.Scanner;

public class CentreRadioIhm {
    public static int CentreRadioManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- CENTRE DE RADIOLOGIE MANAGEMENT ---");
        System.out.println("1. View Centre de Radiologie");
        System.out.println("2. Modify Nom");
        System.out.println("3. Modify Address");
        System.out.println("4. Modify Mail");
        System.out.println("5. Modify Number");
        System.out.println("6. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static void viewCentreRadio() {
        System.out.println("Viewing the Centre de Radiologie...");
    }

    public static String modifyNom() {
        System.out.print("Enter new name: ");
        return Verification.getValidName();
    }

    public static String modifyAddress() {
        System.out.print("Enter new address: ");
        return Verification.getValidName();
    }

    public static String modifyMail() {
        System.out.print("Enter new mail: ");
        return Verification.getValidEmail();
    }

    public static int modifyNum() {
        System.out.print("Enter new number: ");
        return Verification.getValidID();
    }
}
