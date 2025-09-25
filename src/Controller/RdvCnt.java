package Controller;


import entity.Categorie;
import entity.RendezVous;

import ihm.*;
import service.CategorieServ;
import service.RendezVousServ;


public class RdvCnt {
    public void showRdvMenu() {
        RendezVousServ rendezVousServ=new RendezVousServ();
        CategorieServ categorieServ=new CategorieServ();
        int IdR;
        while (true) {
            int choice = RendezVousIhm.RdvManagementMenu();
            switch (choice) {
                case 1:
                    categorieServ.listAllCategories().showoutput();
                    RendezVous rendezVous = RendezVousIhm.addRendezVous();
                    if(rendezVousServ.check_existanceOfTreatment(rendezVous).istrue()) {
                        rendezVousServ.scheduleRendezVous(rendezVous);
                    }
                    else {
                        rendezVousServ.check_existanceOfTreatment(rendezVous).showoutput();
                    }
                    break;
                case 2:
                    IdR = RendezVousIhm.removeRendezVous();
                    rendezVousServ.cancelRendezVous(IdR);
                    break;
                case 3:
                    IdR = RendezVousIhm.viewRendezVous();
                    rendezVousServ.viewRdv(IdR).showoutput();
                    break;
                case 4:
                    rendezVousServ.listAllRendezVous().showoutput();
                    break;
                case 5:
                    int CIN = PatientIhm.viewPatient();
                    rendezVousServ.showRendezVousbyPatient(CIN).showoutput();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
