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
        Scanner scanner = new Scanner(System.in);
        String profile;
        int inputNo;

        System.out.println("Are you a client or an admin? Press 1 for client or 2 for admin: ");
        profile = scanner.nextLine();

        if (profile.equals("1")) {
            System.out.println("Hello, dear client!");
            while (profile.equals("1")) {
                System.out.println("Please, enter your first name:");
                String firstName = scanner.nextLine();
                while (firstName.length() == 0) {
                    System.out.println("First name cannot be empty, please try again:");
                    firstName = scanner.nextLine();
                }

                System.out.println("Please, enter your last name:");
                String lastName = scanner.nextLine();
                System.out.println("Please, enter your phone number: ");
                String phoneNumber = scanner.nextLine();

                Appointment newAppointment = new Appointment(firstName, lastName, phoneNumber);
                appointmentRepository.save(newAppointment);

                System.out.println("Dear, " + firstName + "! Thank you for your appointment booking. We will call you back to number " + phoneNumber);
                System.out.println("Press 1 to submit another appointment. Press 0 to close the application");
                profile = scanner.nextLine();
            }
        } else if (profile.equals("2")) {
            System.out.println("hello admin!");

            System.out.println("press 1 to see all inputs, to search press 2: ");

            int input = scanner.nextInt();
            if (input == 1) {
                List<Appointment> allAppointments = appointmentRepository.findAll();
                for (Appointment appointment : allAppointments) {
                    System.out.println(appointment);
                }
            } else if (input == 2) {
                System.out.println("Enter search value (last name):");
                String searchInput = scanner.next();
                List<Appointment> searchResults = appointmentRepository.findByLastName(searchInput);
                if (searchResults.isEmpty()) {
                    System.out.println("No appointments found with last name " + searchInput);
                } else {
                    for (Appointment appointment : searchResults) {
                        System.out.println(appointment);
                    }
                }
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}