package com.smart_clinic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
   private String firstname;
   private String lastname;
   private  String username;
   private  String email;
   private String password;
   private  Date birthdate;
   private String specialty;
   private String role;
}
