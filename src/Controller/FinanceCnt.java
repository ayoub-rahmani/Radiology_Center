package Controller;

import ihm.FinanceIhm;
import service.FinanceServ;

public class FinanceCnt {
    FinanceServ financeServ = new FinanceServ();

    public void showFinanceMenu() {
        double newSalary;
        while (true) {
            int choice = FinanceIhm.FinanceManagementMenu();
            switch (choice) {
                case 1:
                    double flous = FinanceIhm.addFLous();
                    financeServ.addFLous(flous).showoutput();
                    break;
                case 2:
                    FinanceIhm.paySalaries();
                    financeServ.paySalaries().showoutput();
                    break;
                case 3:
                    FinanceIhm.viewDepenses();
                    financeServ.viewDepenses().showoutput();
                    break;
                case 4:
                    FinanceIhm.viewRevenues();
                    financeServ.viewRevenues().showoutput();
                    break;
                case 5:
                    FinanceIhm.viewMarbou7();
                    financeServ.viewMarbou7().showoutput();
                    break;
                case 6:
                    newSalary = FinanceIhm.changeSalary();
                    financeServ.updateSalairieR(newSalary);
                    break;
                case 7:
                    newSalary = FinanceIhm.changeSalary();
                    financeServ.updateSalairieT(newSalary);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showFinanceMenu();
            }
        }
    }
}