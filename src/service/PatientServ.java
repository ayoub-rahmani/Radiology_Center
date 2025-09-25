package service;
import entity.Patient;
import ihm.Output;
import persistance.*;

public class PatientServ {
    PersPatient persPatient=new PersPatient();
    public Output addPatient(Patient p) {
        if(viewPatient(p.getCIN()).getObj()==null) {
            persPatient.add(p);
            return new Output(true,"Patient added Succesfully",null);
        }
        return new Output(false,"Patient already exists",null);
    }
    public Output removePatient(int CIN){
        Patient patient = (Patient) persPatient.getPatient(CIN);
        if (patient != null) {
            persPatient.remove(patient);
            return new Output(true,"Patient removed successfully!",null);
        } else {
                return new Output(false,"Patient not found!",null);
        }
        }
        public  Output viewPatient(int CIN) {
        Patient patient = (Patient) persPatient.getPatient(CIN);
        if (patient != null) {
            return new Output(true,"",patient);
        } else {
            return new Output(false,"Patient not found!",null);
        }
    }

    public Output listAllPatients() {
        if(persPatient.getAllPatients()==null) {
            return new Output(true,"No Patient found !",null);
        }
        return new Output(true,"--- ALL PATIENTS ---\n",persPatient.getAllPatients());
    }
    public Output modifyPatient(int idT, Patient p){
        if(viewPatient(idT).getObj()==null) {
            return new Output(false,"Patient not found!",null);
        }
        persPatient.modify(idT,p);
        return new Output(true,"Modified Patient Succesfully",null);
    }
}
