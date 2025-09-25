package ihm;
import entity.*;

import java.util.List;
import java.util.Scanner;
public class Menu {

    public static int displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n--- ADMINISTRATION MANAGEMENT SYSTEM ---");
            System.out.println("1. Centre De Radiologie Management");
            System.out.println("2. Patient Management");
            System.out.println("3. Medecin Management");
            System.out.println("4. Radiologue Management");
            System.out.println("5. Technicien Management");
            System.out.println("6. Treatement type Management");
            System.out.println("7. Rendez-Vous Management");
            System.out.println("8. Salle Management");
            System.out.println("9. Examen Management");
            System.out.println("10. Finance Management");
            System.out.println("11. Rapport Management");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}