package Controller;

import entity.Categorie;
import ihm.*;
import service.CategorieServ;

public class CategorieCnt {
    CategorieServ categorieServ = new CategorieServ();
    public void showCategorieMenu() {
        while (true) {
            int choice = CategorieIhm.CategorieManagementMenu();
            switch (choice) {
                case 1:
                    Categorie newCategorie = CategorieIhm.addCategorie();
                    categorieServ.addCategorie(newCategorie).showoutput();
                    break;
                case 2:
                    String nomToRemove = CategorieIhm.removeCategorie();
                    categorieServ.removeCategorie(nomToRemove).showoutput();
                    break;
                case 3:
                    String nomToView = CategorieIhm.viewCategorie();
                    categorieServ.viewCategorie(nomToView).showoutput();
                    break;
                case 4:
                    categorieServ.listAllCategories().showoutput();
                    break;
                case 5:
                    String nomToModify = CategorieIhm.viewCategorie();
                    Categorie existingCategorie = (Categorie) categorieServ.viewCategorie(nomToModify).getObj();
                    if (existingCategorie != null) {
                        Categorie updatedCategorie = CategorieIhm.modifyCategorie(existingCategorie);
                        categorieServ.modifyCategorie(nomToModify, updatedCategorie).showoutput();
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}