package service;

import entity.Finance;
import entity.Radiologue;
import entity.Technicien;
import ihm.Output;
import persistance.PersFinance;
import persistance.PersRadiologue;
import persistance.PersTechnicien;

import java.util.ArrayList;
import java.util.List;

public class FinanceServ {
    private final PersFinance persFinance = new PersFinance();
    private PersRadiologue persRadiologue = new PersRadiologue();
    private PersTechnicien persTechnicien = new PersTechnicien();



    public Output addFLous(double flous) {
        try {
            persFinance.addFLous(flous);
            return new Output(true, "Flous added successfully.", null);
        } catch (Exception e) {
            return new Output(false, "Failed to add Flous.", null);
        }
    }
    public Output paySalaries() {
        try {
            double salaries = 0;
            List<Radiologue> radiologues = persRadiologue.getAllRadiologue();
            for (Radiologue radiologue : radiologues) {
                salaries += radiologue.getSalaire();
            }
            List<Technicien> techniciens = persTechnicien.getAllTechnicien();
            for (Technicien technicien : techniciens) {
                salaries += technicien.getSalaire();
            }
            Finance finance = persFinance.getFinance();
            finance.setDepense(finance.getDepense()+salaries);
            persFinance.saveFinance(finance);
            return new Output(true, "Salaries paid successfully.", null);

        } catch (Exception e) {
            return new Output(false, "Failed to pay Salaries.", null);
        }
    }

    public Output viewDepenses() {
        try {
            double depenses = persFinance.getDepenses();
            return new Output(true, "", depenses);
        } catch (Exception e) {
            return new Output(false, "Failed to retrieve Depenses.", null);
        }
    }

    public Output viewRevenues() {
        try {
            double revenues = persFinance.getRevenues();
            return new Output(true, "", revenues);
        } catch (Exception e) {
            return new Output(false, "Failed to retrieve Revenues.", null);
        }
    }

    public Output viewMarbou7() {
        try {
            double marbou7 = (persFinance.getRevenues() - persFinance.getDepenses());
            return new Output(true, "", marbou7);
        } catch (Exception e) {
            return new Output(false, "Failed to retrieve Marbou7.", null);
        }
    }
    public void updateSalairieR(double val) {
        Radiologue.setSalaire(val);
    }
    public void updateSalairieT(double val) {
        Technicien.setSalaire(val);
    }
}