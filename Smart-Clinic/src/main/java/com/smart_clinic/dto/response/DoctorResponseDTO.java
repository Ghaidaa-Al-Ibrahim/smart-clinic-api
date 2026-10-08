package com.smart_clinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String specialty;
}
