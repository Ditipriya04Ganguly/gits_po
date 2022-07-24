package com.CAR.ClinicalAlgorithmRules.controller;

import com.CAR.ClinicalAlgorithmRules.model.Patient;
import com.CAR.ClinicalAlgorithmRules.model.PatientDetails;
import com.CAR.ClinicalAlgorithmRules.service.PatientDisease;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController

public class CarController {



    @Autowired
    private KieSession ksession;


    @PostMapping("/patient")
    public PatientDetails addPatient(@RequestBody Patient patient){
        System.out.println("Patient details:"+patient);

        PatientDisease patientDisease= new PatientDisease();

        ksession.insert(patient);

        for(Patient pt: patientDisease.getMaplist().keySet()){
            ksession.insert(pt);
        }
        ksession.insert(LocalDate.now());
        ksession.insert(patientDisease);
        ksession.fireAllRules();
        //ksession.dispose();


        /*for(Patient p1: patientDisease.getMaplist().keySet())
        {
            System.out.print("patient with id "+p1.getId()+" has disease ");
            System.out.println(patientDisease.getMaplist().get(p1));
            System.out.println("Prefill value: "+ p1.getPreFill());
            System.out.println("Multiple value: "+p1.getMultiple());
            System.out.println("Multiple Risk prefill: "+p1.getMultipleG());
            System.out.println("Risk Factor: "+p1.getRisk());
        }*/

        //System.out.println(patientDisease.searchPatientById(patient.getId()));
        //System.out.println(patientDisease.getMaplist().get(patient));
        PatientDetails patientdetails= new PatientDetails(patientDisease.searchPatientById(patient.getId()),patientDisease.getMaplist().get(patient));
        return patientdetails;

    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable(name = "id") int id){
        Patient p= new Patient(1,"R13.1", "Active",LocalDate.of(2022,06,30), LocalDate.of(2000,9,04));
        return p;
    }

}
