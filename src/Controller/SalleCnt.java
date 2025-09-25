package Controller;

import entity.Salle;
import entity.Technicien;
import ihm.SalleIhm;
import service.SalleServ;
import service.TechnicienServ;

import java.util.HashMap;

public class SalleCnt {
    SalleServ salleServ = new SalleServ();
    int salleNum;

    public void showSalleMenu() {
        while (true) {
            int choice = SalleIhm.SalleManagementMenu();
            switch (choice) {
                case 1:
                    Salle salle = SalleIhm.addSalle();
                    salleServ.addSalle(salle).showoutput();
                    break;
                case 2:
                    salleNum = SalleIhm.removeSalle();
                    salleServ.removeSalle(salleNum).showoutput();
                    break;
                case 3:
                    salleNum = SalleIhm.viewSalle();
                    salleServ.viewSalle(salleNum).showoutput();
                    break;
                case 4:
                    salleServ.listAllSalles().showoutput();
                    break;
                case 5:
                    TechnicienServ technicienServ = new TechnicienServ();
                    HashMap<String, Integer> details = SalleIhm.addTechnicien();
                    if (!details.isEmpty()) {
                        int salleNum = details.get("NumS");
                        Technicien technicien = (Technicien)technicienServ.viewTechnicien(details.get("IdT")).getObj();
                        salleServ.addTechnicienToSalle(salleNum, technicien).showoutput();
                    }
                    break;
                case 6:
                    HashMap<String, Integer> rdetails = SalleIhm.removeTechnicien();
                    if (!rdetails.isEmpty()) {
                        int salleNum = rdetails.get("NumS");
                        int idT = rdetails.get("IdT");
                        salleServ.removeTechnicienFromSalle(salleNum, idT).showoutput();
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}