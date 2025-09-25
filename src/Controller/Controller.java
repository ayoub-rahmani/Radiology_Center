package Controller;
import entity.Medecin;
import entity.Radiologue;
import entity.RendezVous;
import ihm.*;

import java.io.IOException;

public class Controller {
    public static void init() throws IOException {
        while (true) {
            int choice = Menu.displayMainMenu();
            switch (choice) {
                case 1:
                    CentreRadioCnt w = new CentreRadioCnt();
                    w.showCentreRadioMenu();
                    break;
                case 2:
                    PatientCnt p = new PatientCnt();
                    p.showPatientMenu();
                    break;
                case 3:
                    MedecinCnt m = new MedecinCnt();
                    m.showMedecinMenu();
                    break;
                case 4:
                    RadiologueCnt r = new RadiologueCnt();
                    r.showRadiologueMenu();
                    break;
                case 5:
                    TechnicienCnt t = new TechnicienCnt();
                    t.showTechnicienMenu();
                    break;
                case 6:
                    CategorieCnt c = new CategorieCnt();
                    c.showCategorieMenu();
                    break;
                case 7:
                    RdvCnt rdv = new RdvCnt();
                    rdv.showRdvMenu();
                    break;
                case 8:
                    SalleCnt s = new SalleCnt();
                    s.showSalleMenu();
                    break;
                case 9:
                    ExamenCnt e = new ExamenCnt();
                    e.showExamenMenu();
                    break;
                case 10:
                    FinanceCnt f = new FinanceCnt();
                    f.showFinanceMenu();
                    break;
                case 11:
                    RapportCnt rp = new RapportCnt();
                    rp.showRapportMenu();
                    break;
                case 12:
                    System.out.println("Exiting Administration System...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

}
