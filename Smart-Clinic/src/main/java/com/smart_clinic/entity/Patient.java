package com.smart_clinic.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient extends User{

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecordList= new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList = new ArrayList<>();

}
