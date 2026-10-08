package com.smart_clinic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorScheduleRequestDTO {
    private Long doctorId ;
    private String day ;
   private  LocalTime startTime ;
   private LocalTime endTime;
}
