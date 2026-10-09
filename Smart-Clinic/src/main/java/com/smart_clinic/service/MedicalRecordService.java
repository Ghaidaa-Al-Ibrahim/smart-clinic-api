package com.smart_clinic.service;

import com.smart_clinic.entity.Appointment;
import com.smart_clinic.entity.MedicalRecord;
import com.smart_clinic.entity.Patient;
import com.smart_clinic.repository.AppointmentRepository;
import com.smart_clinic.repository.MedicalRecordRepository;
import com.smart_clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private AppointmentRepository appointmentRepository;
    private MedicalRecordRepository medicalRecordRepository;



    @Autowired
    public MedicalRecordService(AppointmentRepository appointmentRepository, MedicalRecordRepository medicalRecordRepository) {
        this.appointmentRepository = appointmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;

    }




    public MedicalRecord createMedicalRecord(long appointmentId , String medicalRecordText){
      Appointment appointment =appointmentRepository.findById(appointmentId)
              .orElseThrow(() -> new RuntimeException("Appointment not found!!"));

        MedicalRecord medicalRecord= new MedicalRecord();
        medicalRecord.setAppointment(appointment);
        medicalRecord.setPatient(appointment.getPatient());
        medicalRecord.setMedicalHistory(medicalRecordText);
       return  medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord getRecordByAppointment(long appointmentId){
        Appointment appointment =appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found!!"));
     return appointment.getMedicalRecord();
    }

    public List<MedicalRecord> getRecordByPatient(long patientId){
     return medicalRecordRepository.findByPatientId(patientId);
    }
}
