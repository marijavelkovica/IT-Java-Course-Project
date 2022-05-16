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
                while (lastName.length() == 0) {
                    System.out.println("Last name cannot be empty, please try again:");
                    lastName = scanner.nextLine();
                } // pievienoju
                System.out.println("Please, enter your phone number: ");
                String phoneNumber = scanner.nextLine();
                while (!phoneNumber.matches("\\d{8}")) {
                    System.out.println("Wrong phone number! It should be 8 digits.");
                    phoneNumber = scanner.nextLine();
                } // pievienoju
                Appointment newAppointment = new Appointment(firstName, lastName, phoneNumber);
                appointmentRepository.save(newAppointment);

                System.out.println("Dear, " + firstName + "! Thank you! Your request is registered. We will call you back to number " + phoneNumber);
                System.out.println("Press 1 to submit another appointment. Press 0 to finish the registration.");
                profile = scanner.nextLine();
            }
            if (profile.equals("0")){ // pievineoju
                System.out.println("Thank you. Good bye! You can close the application now!");
            }
        } else if (profile.equals("2")) {
            System.out.println("Hello admin!");

            while (true) { //pievienoju
                System.out.println("Press 1 to see all inputs, press 2 to search, press 3 to exit: ");

                int input = scanner.nextInt();
                if (input == 1) {
                    List<Appointment> allAppointments = appointmentRepository.findAll();
                    for (Appointment appointment : allAppointments) {
                        System.out.println(appointment);
                    }
                } else if (input == 2) {
                    System.out.println("If you want to search by name, press 1; If you want to search by surname, press 2; If you want to search by phone, press 3:");
                    int search = scanner.nextInt();

                    if (search == 1) {
                        System.out.println("Enter search value (first name):");
                        String searchInput = scanner.next();
                        List<Appointment> searchResults = appointmentRepository.findByFirstName(searchInput);
                        if (searchResults.isEmpty()) {
                            System.out.println("No appointments found with first name " + searchInput);
                        } else {
                            for (Appointment appointment : searchResults) {
                                System.out.println(appointment);
                            }
                        }
                    }
                    else if (search == 2) {
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
                    }
                    else if (search == 3) {
                        System.out.println("Enter search value (Phone Number):");
                        String searchInput = scanner.next();
                        List<Appointment> searchResults = appointmentRepository.findByPhoneNumber(searchInput);
                        if (searchResults.isEmpty()) {
                            System.out.println("No appointments found with Phone Number" + searchInput);
                        } else {
                            for (Appointment appointment : searchResults) {
                                System.out.println(appointment);
                            }
                        }
                    }
                } else if (input == 3) { // pievienoju
                    System.out.println("You have finished your work and can close the application! Have a nice day!"); // pievienoju
                    break; // pievienoju
                } else {
                    System.out.println("Wrong input");
                }
            }
        }
    }
}