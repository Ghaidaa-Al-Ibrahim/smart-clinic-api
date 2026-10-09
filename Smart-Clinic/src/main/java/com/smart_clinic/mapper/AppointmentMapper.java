package com.smart_clinic.mapper;

import com.smart_clinic.dto.response.AppointmentResponseDTO;
import com.smart_clinic.entity.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
    public AppointmentResponseDTO toDTO(Appointment appointment){
        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();
        appointmentResponseDTO.setId(appointment.getId());
        appointmentResponseDTO.setDoctorName(
                appointment.getDoctor().getFirstname()+" "+appointment.getDoctor().getLastname());
        appointmentResponseDTO.setDoctorSpecialty(appointment.getDoctor().getSpecialty());
        appointmentResponseDTO.setDateTime(appointment.getDateTime());
        appointmentResponseDTO.setStatus(appointment.getAppointmentStatus());
        return appointmentResponseDTO;
    }

    public List<AppointmentResponseDTO> toDTOList (List<Appointment> appointments){
        return appointments.stream()
                .map(this :: toDTO)
                .collect(Collectors.toList());
    }
}
