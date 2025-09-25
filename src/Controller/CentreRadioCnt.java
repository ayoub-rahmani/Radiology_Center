package Controller;

import entity.CentreRadio;
import ihm.CentreRadioIhm;
import service.CentreRadioServ;

public class CentreRadioCnt {
    CentreRadioServ centreRadioServ = new CentreRadioServ();
    int num;

    public void showCentreRadioMenu(){
        while(true){
            int choice = CentreRadioIhm.CentreRadioManagementMenu();
            switch (choice) {
                case 1:
                    CentreRadioIhm.viewCentreRadio();
                    centreRadioServ.viewCentreRadio().showoutput();
                    break;
                case 2:
                    String newNom = CentreRadioIhm.modifyNom();
                    centreRadioServ.modifyNom(newNom).showoutput();
                    break;
                case 3:
                    String newAddress = CentreRadioIhm.modifyAddress();
                    centreRadioServ.modifyAddress(newAddress).showoutput();
                    break;
                case 4:
                    String newMail = CentreRadioIhm.modifyMail();
                    centreRadioServ.modifyMail(newMail).showoutput();
                    break;
                case 5:
                    int newNum = CentreRadioIhm.modifyNum();
                    centreRadioServ.modifyNum(newNum).showoutput();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showCentreRadioMenu();
            }
        }
    }
}