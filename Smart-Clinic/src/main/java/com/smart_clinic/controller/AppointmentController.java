package com.smart_clinic.controller;

import com.smart_clinic.dto.request.AppointmentRequestDTO;
import com.smart_clinic.dto.response.AppointmentResponseDTO;
import com.smart_clinic.entity.Appointment;
import com.smart_clinic.mapper.AppointmentMapper;
import com.smart_clinic.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @PostMapping("/bookAppointment")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO){
        appointmentService.bookAppointment(
                appointmentRequestDTO.getDoctorId(),
                appointmentRequestDTO.getPatientId(),
                appointmentRequestDTO.getDateTime());
        return ResponseEntity.ok().body("Appointment booked successful!!");

    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelAppointment(
            @PathVariable("id") long appointmentId){
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok().body("Appointment canceled successful!!");
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointment(@PathVariable long doctorId){
       List<Appointment> appointmentList= appointmentService.getDoctorAppointment(doctorId);
        return ResponseEntity.ok().body(
               appointmentMapper.toDTOList(appointmentList)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>>getPatientAppointment(@PathVariable long patientId){
        List<Appointment> appointmentList =appointmentService.getPatientAppointment(patientId);
        return ResponseEntity.ok().body(
                appointmentMapper.toDTOList(appointmentList)
        );
    }
}
