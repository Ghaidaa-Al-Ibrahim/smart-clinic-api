package com.smart_clinic.service;

import com.smart_clinic.entity.Doctor;
import com.smart_clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
   public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(long doctorId){
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
    }
    public void addDoctor(
            String firstname,
            String lastname,
            String username,
            String email,
            String password,
            Date birthdate,
            String specialty
    ){

        Doctor doctor= new Doctor();
        doctor.setFirstname(firstname);
        doctor.setLastname(lastname);
        doctor.setUsername(username);
        doctor.setEmail(email);
        doctor.setPassword(password);
        doctor.setBirthdate(birthdate);
        doctor.setSpecialty(specialty);
        doctorRepository.save(doctor);
    }
    public Doctor updateDoctor(long doctorId,Doctor doctor){
     Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
      existingDoctor.setFirstname(doctor.getFirstname());
      existingDoctor.setLastname(doctor.getLastname());
      existingDoctor.setUsername(doctor.getUsername());
      existingDoctor.setEmail(doctor.getEmail());
      existingDoctor.setBirthdate(doctor.getBirthdate());
      existingDoctor.setSpecialty(doctor.getSpecialty());
      existingDoctor.setPassword(doctor.getPassword());
      existingDoctor.setDoctorScheduleList(doctor.getDoctorScheduleList());
      existingDoctor.setAppointmentList(doctor.getAppointmentList());
     return    doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(long doctorId){
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
        doctorRepository.deleteById(doctorId);
    }
}
