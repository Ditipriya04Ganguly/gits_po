package com.CAR.ClinicalAlgorithmRules.service;

import com.CAR.ClinicalAlgorithmRules.model.Patient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PatientDisease {

   private Map<Patient, Set<String>> maplist= new HashMap<>();

    public void addPatient(Patient patient, String disease)
    {
       Set<String> diseaseset=new HashSet<>();
       if(maplist.get(patient)==null)
       {
          diseaseset.add(disease);
          maplist.put(patient,diseaseset);
       }
       else {
          diseaseset=maplist.get(patient);
          diseaseset.add(disease);
          maplist.put(patient,diseaseset);

       }

    }

   public Map<Patient, Set<String>> getMaplist() {
      return maplist;
   }

   public void setMaplist(Map<Patient, Set<String>> maplist) {
      this.maplist = maplist;
   }

   public boolean checkDisease(Patient p) {
      if (maplist.get(p) == null) {
         return false;
      }
      return true;
   }

   public boolean checkMultiple(Patient p){
       if (maplist.get(p)!=null){
          if (maplist.get(p).size()>1){
             return true;
          }
       }
       return false;
   }


   public void addPatientwithRisk(Patient p){
       Set<String> risk= new HashSet<>();
       for (Patient p1: maplist.keySet()){
          if (p1.getId()==p.getId()){
             risk= p1.getRisk();
             break;
          }
       }
       risk.addAll(p.getRisk());
       p.setRisk(risk);
       maplist.put(p,maplist.get(p));
   }

   public boolean checkMultipleRisk(Patient prisk){
        if (maplist.containsKey(prisk)){
            for (Patient p: maplist.keySet()){
                if (p.getId()== prisk.getId()){
                    if (p.getRisk().size()>1){
                        return true;
                    }
                }
            }
        }
        return false;
   }

   public void addPatientwithMultipleG(Patient p){
        Patient prisk= null;
       for (Patient p1: maplist.keySet()){
           if (p1.getId()==p.getId()){
               prisk= p1;
               break;
           }
       }
       prisk.setMultipleG(p.getMultipleG());
       maplist.put(prisk,maplist.get(prisk));
   }

   public Patient searchPatientById(int id){
        for (Patient p: maplist.keySet()){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
   }

}
