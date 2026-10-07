package com.smart_clinic.repository;

import com.smart_clinic.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule,Long> {
    List<DoctorSchedule> findByDoctorIdAndDayOfWeek(long id,String day);
    // get the time when that doctor with this id work and compared with the current time in the Service Class
    List<DoctorSchedule> findByDoctorIdAndStartTimeAndEndTime(long id,LocalTime start,LocalTime end);
}
