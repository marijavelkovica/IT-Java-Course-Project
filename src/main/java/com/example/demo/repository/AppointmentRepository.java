package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByFirstName(String firstName);
    List<Appointment> findByLastName(String lastName);
    List<Appointment> findByPhoneNumber(String phoneNumber);
}

