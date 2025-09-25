package Controller;

import entity.Technicien;
import ihm.TechnicienIhm;
import service.TechnicienServ;

public class TechnicienCnt {
    TechnicienServ technicienServ=new TechnicienServ();
    int idT;
    public void showTechnicienMenu() {
        while (true) {
            int choice = TechnicienIhm.TechnicienManagementMenu();
            switch (choice) {
                case 1:
                    Technicien technicien = TechnicienIhm.addTechnicien();
                    technicienServ.addTechnicien(technicien).showoutput();
                    break;
                case 2:
                    idT = TechnicienIhm.removeTechnicien();
                    technicienServ.removeTechnicien(idT).showoutput();
                    break;
                case 3:
                    idT = TechnicienIhm.viewTechnicien();
                    technicienServ.viewTechnicien(idT).showoutput();
                    break;
                case 4:
                    technicienServ.listAllTechniciens().showoutput();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showTechnicienMenu();
            }
        }
    }
}
