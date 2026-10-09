package com.smart_clinic.controller;

import com.smart_clinic.dto.request.LoginRequestDTO;
import com.smart_clinic.dto.request.RegisterRequestDTO;
import com.smart_clinic.dto.response.LoginResponseDTO;
import com.smart_clinic.dto.response.RegisterResponseDTO;
import com.smart_clinic.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        authService.register(
                registerRequestDTO.getFirstname(),
                registerRequestDTO.getLastname(),
                registerRequestDTO.getUsername(),
                registerRequestDTO.getEmail(),
                registerRequestDTO.getPassword(),
                registerRequestDTO.getBirthdate(),
                registerRequestDTO.getSpecialty(),
                registerRequestDTO.getRole());
        return ResponseEntity.status(201).body(new RegisterResponseDTO("Registration successful!"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO =  authService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );

        return ResponseEntity.ok(loginResponseDTO);
    }
}
