
package com.medivault;

import java.util.List;
import java.util.Scanner;

import com.medivault.dao.DoctorDAO;
import com.medivault.models.Doctor;

public class DoctorMenu {

    private DoctorDAO doctorDAO = new DoctorDAO();

    public void showMenu(Scanner sc) {

        int choice;

        do {
            System.out.println("\n===== DOCTOR MANAGEMENT =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back to Admin Menu");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    addDoctor(sc);
                    break;

                case 2:
                    viewDoctors();
                    break;

                case 3:
                    searchDoctor(sc);
                    break;

                case 4:
                    updateDoctor(sc);
                    break;

                case 5:
                    deleteDoctor(sc);
                    break;

                case 6:
                    System.out.println("Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }

    // Add Doctor
    private void addDoctor(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Experience (years): ");
        int experience = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Qualification: ");
        String qualification = sc.nextLine();

        Doctor doctor = new Doctor(
            0, name, specialization, phone,
            email, experience, qualification
        );

        if (doctorDAO.addDoctor(doctor)) {
            System.out.println("Doctor Added Successfully!");
        } else {
            System.out.println("Failed to Add Doctor!");
        }
    }

    // View All Doctors
    private void viewDoctors() {

        List<Doctor> doctors = doctorDAO.getAllDoctors();

        System.out.println("\n===== DOCTOR RECORDS =====");

        if (doctors.isEmpty()) {
            System.out.println("No Doctors Found!");
            return;
        }

        for (Doctor d : doctors) {
            System.out.println(
                "ID: " + d.getDoctorId() +
                " | Name: " + d.getName() +
                " | Specialization: " + d.getSpecialization() +
                " | Phone: " + d.getPhone()
            );
        }
    }

    // Search Doctor
    private void searchDoctor(Scanner sc) {

        System.out.print("Enter Doctor ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Doctor d = doctorDAO.getDoctorById(id);

        if (d != null) {
            System.out.println("Doctor Found!");
            System.out.println("Name: " + d.getName());
            System.out.println("Specialization: " + d.getSpecialization());
            System.out.println("Phone: " + d.getPhone());
            System.out.println("Email: " + d.getEmail());
        } else {
            System.out.println("Doctor Not Found!");
        }
    }

    // Update Doctor
    private void updateDoctor(Scanner sc) {

        System.out.print("Enter Doctor ID to Update: ");
        int id = Integer.parseInt(sc.nextLine());

        Doctor d = doctorDAO.getDoctorById(id);

        if (d == null) {
            System.out.println("Doctor Not Found!");
            return;
        }

        System.out.print("Enter New Name: ");
        d.setName(sc.nextLine());

        System.out.print("Enter New Specialization: ");
        d.setSpecialization(sc.nextLine());

        System.out.print("Enter New Phone: ");
        d.setPhone(sc.nextLine());

        System.out.print("Enter New Email: ");
        d.setEmail(sc.nextLine());

        System.out.print("Enter New Experience: ");
        d.setExperience(Integer.parseInt(sc.nextLine()));

        System.out.print("Enter New Qualification: ");
        d.setQualification(sc.nextLine());

        if (doctorDAO.updateDoctor(d)) {
            System.out.println("Doctor Updated Successfully!");
        } else {
            System.out.println("Doctor Update Failed!");
        }
    }

    // Delete Doctor
    private void deleteDoctor(Scanner sc) {

        System.out.print("Enter Doctor ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine());

        if (doctorDAO.deleteDoctor(id)) {
            System.out.println("Doctor Deleted Successfully!");
        } else {
            System.out.println("Doctor Deletion Failed!");
        }
    }
}
