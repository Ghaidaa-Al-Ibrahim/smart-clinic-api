package com.smart_clinic.controller;

import com.smart_clinic.dto.response.DoctorResponseDTO;
import com.smart_clinic.entity.Doctor;
import com.smart_clinic.mapper.DoctorMapper;
import com.smart_clinic.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors(){
        return ResponseEntity.ok(
                doctorMapper.toDTOList(doctorService.getAllDoctors())
        );
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable long doctorId){
     return ResponseEntity.ok(
             doctorMapper.toDTO(doctorService.getDoctorById(doctorId))
             );
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable long doctorId,@RequestBody Doctor doctor){
        return ResponseEntity.ok(
                doctorMapper.toDTO(
                        doctorService.updateDoctor(doctorId,doctor)));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity deleteDoctor(@PathVariable long doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.status(204).body("Deleted successful!!");
    }
}
