package com.smart_clinic.service;

import com.smart_clinic.entity.Appointment;
import com.smart_clinic.entity.Doctor;
import com.smart_clinic.entity.DoctorSchedule;
import com.smart_clinic.entity.Patient;
import com.smart_clinic.enums.AppointmentStatus;
import com.smart_clinic.repository.AppointmentRepository;
import com.smart_clinic.repository.DoctorRepository;
import com.smart_clinic.repository.DoctorScheduleRepository;
import com.smart_clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, DoctorScheduleRepository doctorScheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorScheduleRepository = doctorScheduleRepository;
    }





    public void bookAppointment(long doctorId,
                                long patientId,
                                Date date) {

        Calendar cal =Calendar.getInstance();
        cal.setTime(date);
        String dayOfWeek = cal.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.LONG,
                Locale.ENGLISH).toUpperCase();
        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found!"));
        List<DoctorSchedule> scheduleList =
                doctorScheduleRepository.findByDoctorIdAndDayOfWeek(doctorId, dayOfWeek);
        // check if the Doctor is available in this time
        if (scheduleList.isEmpty()) {
            throw new RuntimeException("Doctor in this Time is not available !");
        }
            // check if there is another Appointment by the same Doctor
            if (appointmentRepository.existsByDoctorIdAndDateTime(doctor.getId(), date)) {
                throw new RuntimeException("Doctor has another Appointment !");
            }

            Appointment appointment= new Appointment();
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setDateTime(date);
            appointment.setAppointmentStatus(AppointmentStatus.PENDING);
            appointmentRepository.save(appointment);
        }


    public void cancelAppointment(long appointmentId){
        Appointment appointment= appointmentRepository
                .findById(appointmentId)
                .orElseThrow( () -> new RuntimeException("Appointment not found!"));
        long currentTime =System.currentTimeMillis();
        long appointmentTime= appointment.getDateTime().getTime();
        long time=24*60*60*1000;
        long dif=appointmentTime-currentTime;
        if (dif < time){
            throw new RuntimeException("Cannot cancel appointment less than 24 hours before!");
        }
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);

    }

    public List<Appointment> getDoctorAppointment(long doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getPatientAppointment(long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }


}
