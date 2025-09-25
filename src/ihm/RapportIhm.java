package ihm;

import java.util.Scanner;

public class RapportIhm {
    public static int RapportManagementMenu() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- Rapport MANAGEMENT ---");
            System.out.println("1. Generate PDF Rapport");
            System.out.println("2. Return To Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
    }
    public static int viewRapport(){
        System.out.println("Enter CIN : ");
        Scanner scanner = new Scanner(System.in);
        int cin = scanner.nextInt();
        scanner.nextLine();
        return cin;
    }
}
