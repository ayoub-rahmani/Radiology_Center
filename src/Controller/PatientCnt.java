package Controller;
import entity.Categorie;
import entity.Patient;
import ihm.CategorieIhm;
import ihm.Menu;
import ihm.PatientIhm;
import service.PatientServ;

public class PatientCnt {
    PatientServ patientServ=new PatientServ();
    int CIN;
    public void showPatientMenu() {
        while(true){
            int choice = PatientIhm.PatientManagementMenu();
            switch (choice) {
                case 1:
                    Patient patient = PatientIhm.addPatient();
                    patientServ.addPatient(patient).showoutput();
                    break;
                case 2:
                    CIN = PatientIhm.removePatient();
                    patientServ.removePatient(CIN).showoutput();
                    break;
                case 3:
                    int CIN = PatientIhm.viewPatient();
                    Patient existingPatient = (Patient) patientServ.viewPatient(CIN).getObj();
                    if (existingPatient != null) {
                        Patient updatedPatient = PatientIhm.modifyPatient(existingPatient);
                        patientServ.modifyPatient(CIN,updatedPatient).showoutput();
                    }
                    break;
                case 4:
                    CIN = PatientIhm.viewPatient();
                    patientServ.viewPatient(CIN).showoutput();
                    break;
                case 5:
                    patientServ.listAllPatients().showoutput();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

