package com.smart_clinic.entity;

import com.smart_clinic.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateTime")
    private Date dateTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;


    @OneToOne
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
