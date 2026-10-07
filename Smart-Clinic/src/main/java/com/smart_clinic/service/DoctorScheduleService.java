package com.smart_clinic.service;

import com.smart_clinic.entity.Doctor;
import com.smart_clinic.entity.DoctorSchedule;
import com.smart_clinic.repository.AppointmentRepository;
import com.smart_clinic.repository.DoctorRepository;
import com.smart_clinic.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
@Service
public class DoctorScheduleService {

    private DoctorScheduleRepository doctorScheduleRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorScheduleService(DoctorScheduleRepository doctorScheduleRepository, DoctorRepository doctorRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.doctorRepository = doctorRepository;
    }




    public void addSchedule(long doctorId, String day, LocalTime startTime,LocalTime endTime){
        Doctor doctor= doctorRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found!!"));
        DoctorSchedule doctorSchedule= new DoctorSchedule();
        doctorSchedule.setDoctor(doctor);
        doctorSchedule.setDayOfWeek(day);
        doctorSchedule.setStartTime(startTime);
        doctorSchedule.setEndTime(endTime);
        doctorScheduleRepository.save(doctorSchedule);
    }

    public void deleteSchedule(long scheduleId){
        DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("DoctorSchedule not found!!"));
        doctorScheduleRepository.delete(doctorSchedule);
    }

    public List<DoctorSchedule> getDoctorSchedule(long doctorId){
        Doctor doctor= doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!!"));
        return doctor.getDoctorScheduleList();
    }
    public boolean isAvailable(long doctorId,String day){
    List<DoctorSchedule> doctorScheduleList= doctorScheduleRepository
            .findByDoctorIdAndDayOfWeek(doctorId,day);
    return !doctorScheduleList.isEmpty();
    }
}
