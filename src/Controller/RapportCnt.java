package Controller;

import entity.Radiologue;
import ihm.RadiologueIhm;
import ihm.RapportIhm;
import service.RapportServ;

import java.io.IOException;

public class RapportCnt {
    public void showRapportMenu() throws IOException {
        RapportServ rapportServ =new RapportServ();
        while (true) {
            int choice = RapportIhm.RapportManagementMenu();
            switch (choice) {
                case 1:
                    int cin = RapportIhm.viewRapport();
                    rapportServ.viewRapport(cin).showoutput();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
