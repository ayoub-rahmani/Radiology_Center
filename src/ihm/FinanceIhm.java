package ihm;

import entity.Finance;

import java.util.Scanner;

public class FinanceIhm {
    public static int FinanceManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Finance MANAGEMENT ---");
        System.out.println("1. Add Flous");
        System.out.println("2. Pay Salaries");
        System.out.println("3. view Depenses");
        System.out.println("4. View Revenues");
        System.out.println("5. view Marbou7");
        System.out.println("6. Change Radiologue Salary");
        System.out.println("7. Change Technicien Salary");
        System.out.println("8. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static double addFLous() {
        System.out.println("Enter Flous Amount To Add:");
        double flous = Verification.getValidPrice();

        return flous;
    }
    public static void paySalaries() {
        System.out.println("Paying Salaries...");
    }

    public static void viewDepenses() {
        System.out.println("Totale Depenses: ");
    }
    public static void viewRevenues() {
        System.out.println("Totale Revenues: ");
    }
    public static void viewMarbou7() {
        System.out.println("Totale Marbou7: ");
    }
    public static double changeSalary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter New Salary : ");
        double newSalary = Verification.getValidPrice();
        return newSalary;
    }


}
