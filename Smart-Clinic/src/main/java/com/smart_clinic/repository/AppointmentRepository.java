package com.smart_clinic.repository;

import com.smart_clinic.entity.Appointment;
import com.smart_clinic.enums.AppointmentStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findByDoctorIdAndDateTime(long id, Date date);
    List<Appointment> findByAppointmentStatus(AppointmentStatus status);
    List<Appointment> findByDateTime(Date date);
    boolean existsByDoctorIdAndDateTime(Long id, Date date);
    List<Appointment> findByPatientId(Long id);
    List<Appointment> findByDoctorId(Long id);


}
