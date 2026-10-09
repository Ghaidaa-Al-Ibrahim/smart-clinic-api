package com.smart_clinic.mapper;

import com.smart_clinic.dto.response.DoctorResponseDTO;
import com.smart_clinic.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public DoctorResponseDTO toDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setFirstname(doctor.getFirstname());
        dto.setLastname(doctor.getLastname());
        dto.setSpecialty(doctor.getSpecialty());
        return dto;
    }

    public List<DoctorResponseDTO> toDTOList(List<Doctor> doctors) {
        return doctors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}