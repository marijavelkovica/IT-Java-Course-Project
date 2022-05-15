package com.example.demo;

import com.example.demo.entity.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Operator {

    private AppointmentRepository appointmentRepository;


    public Operator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void connection() {
        Scanner sc = new Scanner(System.in);
        String profile;
        int inputNo;

        System.out.println("Are you a client or an admin? Press 1 for client or 2 for admin: ");
        profile = sc.nextLine();

        if (profile.equals("1")) {
            System.out.println("Hello, dear client!");
            System.out.println("Please, enter your first name:");
            String firstName = sc.nextLine();
            System.out.println("Please, enter your last name:");
            String lastName = sc.nextLine();
            System.out.println("Please, enter your phone number: ");
            String phoneNumber = sc.nextLine();
            Appointment newAppointment = new Appointment(firstName, lastName, phoneNumber);
            appointmentRepository.save(newAppointment);
            System.out.println("Dear, " + firstName + "! Thank you for your appointment booking. We will call you back to number " + phoneNumber);
        } else if (profile.equals("2")) {
            System.out.println("hello admin!");

            System.out.println("press 1 to see all inputs, to search press 2: ");

            int input = sc.nextInt();
            if (input == 1) {
//                appointmentRepository.findAll().forEach(System.out::println);
                List<Appointment> allAppointments = appointmentRepository.findAll();
                for (Appointment appointment : allAppointments) {
                    System.out.println(appointment);
                }
            } else if (input == 2) {
                System.out.println("Enter search value (last name):");
                String searchInput = sc.next();
                List<Appointment> searchResults = appointmentRepository.findByLastName(searchInput);
                if (searchResults.isEmpty()) {
                    System.out.println("No appointments found with last name " + searchInput);
                } else {
                    searchResults.forEach(System.out::println);
                }
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}