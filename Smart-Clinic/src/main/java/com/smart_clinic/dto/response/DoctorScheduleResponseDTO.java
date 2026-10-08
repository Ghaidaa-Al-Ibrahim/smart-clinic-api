package com.smart_clinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorScheduleResponseDTO {
    private String day ;
    private LocalTime startTime ;
    private LocalTime endTime;
}
