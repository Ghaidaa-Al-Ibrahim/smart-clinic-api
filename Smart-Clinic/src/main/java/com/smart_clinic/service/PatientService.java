package com.smart_clinic.service;

import com.smart_clinic.entity.Patient;
import com.smart_clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;



    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(long patientId){
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void updatePatient(long patientId, Patient patient){
        patientRepository.findById(patientId)
                        .orElseThrow(() -> new RuntimeException("Patient not found!"));
        patient.setId(patientId);
        patientRepository.save(patient);
    }

    public void deactivatePatient(long patientId){

    }
}
