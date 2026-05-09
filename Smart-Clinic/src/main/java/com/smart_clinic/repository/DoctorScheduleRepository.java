package com.smart_clinic.repository;

import com.smart_clinic.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule,Long> {
}
