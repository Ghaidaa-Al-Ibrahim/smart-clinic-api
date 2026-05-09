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
@Table(name = "doctors")
public class Doctor extends User{

    @Column(name = "specialty")
    private String specialty;


    @OneToMany(mappedBy = "doctor")
    private List<DoctorSchedule> doctorScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList= new ArrayList<>();

}
