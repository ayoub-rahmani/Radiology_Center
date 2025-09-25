package Controller;
import entity.Medecin;
import ihm.MedecinIhm;
import service.MedecinServ;

public class MedecinCnt {
    MedecinServ medecinServ=new MedecinServ();
    int idM;
    public void showMedecinMenu(){
        while(true){
        int choice = MedecinIhm.MedecinManagementMenu();
        switch (choice) {
            case 1:
                Medecin medecin = MedecinIhm.addMedecin();
                medecinServ.addMedecin(medecin).showoutput();
                break;
            case 2:
                idM= MedecinIhm.removeMedecin();
                medecinServ.removeMedecin(idM).showoutput();
                break;
            case 3:
                idM= MedecinIhm.viewMedecin();
                medecinServ.viewMedecin(idM).showoutput();
                break;
            case 4:
                medecinServ.listAllMedecins().showoutput();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                showMedecinMenu();
            }
        }
    }
}
