package com.smart_clinic.controller;

import com.smart_clinic.entity.MedicalRecord;
import com.smart_clinic.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }



    @PostMapping("/appointment/{appointmentId}")
    public ResponseEntity createMedicalRecord(
            @PathVariable long appointmentId ,
            @RequestBody String medicalRecordText){
     medicalRecordService.createMedicalRecord(appointmentId,medicalRecordText);
        return ResponseEntity.status(201).body("Medical record created!");
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<MedicalRecord>getRecordByAppointment(@PathVariable long appointmentId){
        return ResponseEntity.ok().body(medicalRecordService.getRecordByAppointment(appointmentId));
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>>getRecordByPatient(@PathVariable long patientId){
        return ResponseEntity.ok().body(medicalRecordService.getRecordByPatient(patientId));

    }
}
