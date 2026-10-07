package com.smart_clinic.service;

import com.smart_clinic.entity.Doctor;
import com.smart_clinic.entity.Patient;
import com.smart_clinic.entity.User;
import com.smart_clinic.repository.DoctorRepository;
import com.smart_clinic.repository.PatientRepository;
import com.smart_clinic.repository.UserRepository;
import com.smart_clinic.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(String firstname, String lastname, String username, String email,
                         String password, Date birthdate, String specialty, String role){

        if (userRepository.findByEmail(email).isPresent()){
            throw new  RuntimeException("Email already exists!");
        }
        if (role.equalsIgnoreCase("doctor")){
            Doctor doctor =new Doctor();
            doctor.setFirstname(firstname);
            doctor.setLastname(lastname);
            doctor.setUsername(username);
            doctor.setEmail(email);
            doctor.setPassword(passwordEncoder.encode(password));
            doctor.setBirthdate(birthdate);
            doctor.setSpecialty(specialty);
            doctor.setRole("DOCTOR");
            doctorRepository.save(doctor);
        } else {
            Patient patient = new Patient();
            patient.setFirstname(firstname);
            patient.setLastname(lastname);
            patient.setUsername(username);
            patient.setEmail(email);
            patient.setPassword(passwordEncoder.encode(password));
            patient.setBirthdate(birthdate);
            patient.setRole("PATIENT");
            patientRepository.save(patient);
        }

    }

    public String login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        String encodedPassword=user.getPassword();
        String token = null;
        if (passwordEncoder.matches(password,encodedPassword)){
          token=  jwtUtil.generateToken(email,user.getRole());
        }else {
            throw new RuntimeException("Wrong password!");
        }
        return token;
    }
}
