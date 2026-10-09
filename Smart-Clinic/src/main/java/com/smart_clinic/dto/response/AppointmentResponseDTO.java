package com.smart_clinic.dto.response;

import com.smart_clinic.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    private Long id;
    private String doctorName;
    private String doctorSpecialty;
    private Date dateTime;
    private AppointmentStatus status;
}
