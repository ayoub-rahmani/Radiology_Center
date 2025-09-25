package service;

import entity.CentreRadio;
import ihm.Output;
import persistance.CentreRadioPers;

public class CentreRadioServ {
    private final CentreRadioPers centreRadioPers = new CentreRadioPers();

    public Output viewCentreRadio() {
        CentreRadio centreRadio = centreRadioPers.getDefaultCentre();
        if (centreRadio != null) {
            return new Output(true, "", centreRadio);
        } else {
            return new Output(false, "No Centre de Radiologie found.", null);
        }
    }

    public Output modifyNom(String nouveauNom) {
        try {
            centreRadioPers.modifyNom(nouveauNom);
            return new Output(true, "Nom modified successfully.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false, "Failed to modify Nom.", null);
        }
    }

    public Output modifyAddress(String nouvelleAdresse) {
        try {
            centreRadioPers.modifyAddress(nouvelleAdresse);
            return new Output(true, "Adresse modified successfully.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false, "Failed to modify Adresse.", null);
        }
    }

    public Output modifyMail(String nouveauMail) {
        try {
            centreRadioPers.modifyMail(nouveauMail);
            return new Output(true, "Mail modified successfully.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false, "Failed to modify Mail.", null);
        }
    }

    public Output modifyNum(int nouveauNum) {
        try {
            centreRadioPers.modifyNum(nouveauNum);
            return new Output(true, "Num modified successfully.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false, "Failed to modify Num.", null);
        }
    }
}
