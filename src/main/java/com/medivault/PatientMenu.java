
package com.medivault;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.medivault.dao.PatientDAO;
import com.medivault.models.Patient;

public class PatientMenu {

    private PatientDAO patientDAO = new PatientDAO();

    public void showMenu(Scanner sc) {

        int choice;

        do {
            System.out.println("\n===== PATIENT MANAGEMENT =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back to Admin Menu");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    addPatient(sc);
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    searchPatient(sc);
                    break;

                case 4:
                    updatePatient(sc);
                    break;

                case 5:
                    deletePatient(sc);
                    break;

                case 6:
                    System.out.println("Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }

    // Add Patient
    private void addPatient(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Registration Date (YYYY-MM-DD): ");
        Date date = Date.valueOf(sc.nextLine());

        Patient patient = new Patient(
            0, name, age, gender, phone, address, date
        );

        if (patientDAO.addPatient(patient)) {
            System.out.println("Patient Added Successfully!");
        } else {
            System.out.println("Failed to Add Patient!");
        }
    }

    // View All Patients
    private void viewPatients() {

        List<Patient> patients = patientDAO.getAllPatients();

        System.out.println("\n===== PATIENT RECORDS =====");

        if (patients.isEmpty()) {
            System.out.println("No Patients Found!");
            return;
        }

        for (Patient p : patients) {
            System.out.println(
                "ID: " + p.getPatientId() +
                " | Name: " + p.getName() +
                " | Age: " + p.getAge() +
                " | Gender: " + p.getGender() +
                " | Phone: " + p.getPhone()
            );
        }
    }

    // Search Patient
    private void searchPatient(Scanner sc) {

        System.out.print("Enter Patient ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Patient p = patientDAO.getPatientById(id);

        if (p != null) {
            System.out.println("Patient Found!");
            System.out.println("Name: " + p.getName());
            System.out.println("Age: " + p.getAge());
            System.out.println("Phone: " + p.getPhone());
        } else {
            System.out.println("Patient Not Found!");
        }
    }

    // Update Patient
    private void updatePatient(Scanner sc) {

        System.out.print("Enter Patient ID to Update: ");
        int id = Integer.parseInt(sc.nextLine());

        Patient p = patientDAO.getPatientById(id);

        if (p == null) {
            System.out.println("Patient Not Found!");
            return;
        }

        System.out.print("Enter New Name: ");
        p.setName(sc.nextLine());

        System.out.print("Enter New Age: ");
        p.setAge(Integer.parseInt(sc.nextLine()));

        System.out.print("Enter New Gender: ");
        p.setGender(sc.nextLine());

        System.out.print("Enter New Phone: ");
        p.setPhone(sc.nextLine());

        System.out.print("Enter New Address: ");
        p.setAddress(sc.nextLine());

        if (patientDAO.updatePatient(p)) {
            System.out.println("Patient Updated Successfully!");
        } else {
            System.out.println("Patient Update Failed!");
        }
    }

    // Delete Patient
    private void deletePatient(Scanner sc) {

        System.out.print("Enter Patient ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine());

        if (patientDAO.deletePatient(id)) {
            System.out.println("Patient Deleted Successfully!");
        } else {
            System.out.println("Patient Deletion Failed!");
        }
    }
}
