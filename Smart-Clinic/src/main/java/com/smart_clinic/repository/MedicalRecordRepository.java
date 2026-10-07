package com.smart_clinic.repository;

import com.smart_clinic.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

    List<MedicalRecord> findByPatientId(long id);
    List<MedicalRecord> findByAppointmentId(long id);
}
