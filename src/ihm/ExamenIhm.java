package ihm;

import entity.Examen;
import java.util.Scanner;

public class ExamenIhm {
    public static int ExamenManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- EXAMEN MANAGEMENT ---");
        System.out.println("1. List Pending Exams");
        System.out.println("2. List Done Exams");
        System.out.println("3. Perform Exam");
        System.out.println("4. View Exams by Radiologist");
        System.out.println("5. View Exams by Patient");
        System.out.println("6. View Exam by ID");
        System.out.println("7. View Exams by Category");
        System.out.println("8. Return to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static int performExam() {
        System.out.print("Enter Rendez-Vous ID for the exam to perform: ");
        int idRDV = Verification.getValidID();
        return idRDV;
    }


    public static int viewExamsByRadiologue() {
        System.out.print("Enter Radiologist ID: ");
        int idRadiologue = Verification.getValidID();
        return idRadiologue;
    }

    public static int viewExamsByPatient() {
        System.out.print("Enter Patient CIN: ");
        int cin = Verification.getValidCIN();
        return cin;
    }
}