package service;

import entity.Dossier;
import entity.Generatable;
import entity.Patient;
import entity.Radiologue;
import ihm.Output;
import persistance.PersPatient;

import java.io.IOException;
import java.util.ArrayList;

public class RapportServ {
    PersPatient persPatient = new PersPatient();
    private RadiologyReportPDF radiologyReportPDF = new RadiologyReportPDF();

    public Output viewRapport(int cin) throws IOException {
        Patient patient = persPatient.getPatient(cin);
        String message="";
        if (patient != null) {
            ArrayList<Generatable> res = patient.getDossier().getResults();
            for (Generatable gen : res) {
                if (gen.getRapport() == null) {
                    continue;
                }
                String pdf=radiologyReportPDF.genererRapport(patient, gen.getRapport());
                message+=pdf+" Cree avec success \n";
            }
            return new Output(true,message,null);
        } else {
            return new Output(false,"Patient not found!",null);
        }
    }

}
