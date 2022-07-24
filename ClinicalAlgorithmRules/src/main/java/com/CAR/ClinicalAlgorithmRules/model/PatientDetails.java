package com.CAR.ClinicalAlgorithmRules.model;

import java.util.Set;

public class PatientDetails {
    Patient patient;
    Set<String> disease;

    public PatientDetails(Patient patient, Set<String> disease) {
        this.patient = patient;
        this.disease = disease;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<String> getDisease() {
        return disease;
    }

    public void setDisease(Set<String> disease) {
        this.disease = disease;
    }
}
