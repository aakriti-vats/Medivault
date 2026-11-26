
package com.medivault;

import java.util.Scanner;

import com.medivault.services.AuthService;

public class AdminMenu {

    public void showMenu(AuthService auth, Scanner sc) {

        if (!auth.isAdmin()) {
            System.out.println("Access Denied!");
            return;
        }

        int choice;

        do {

            System.out.println("\n===== MEDIVAULT ADMIN PANEL =====");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. User Management");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
            case 1:
                PatientMenu patientMenu = new PatientMenu();
                patientMenu.showMenu(sc);
                break;

            case 2:
                DoctorMenu doctorMenu = new DoctorMenu();
                doctorMenu.showMenu(sc);
                break;
             case 3:
                    System.out.println("User Management Selected");
                    break;

                case 4:
                    auth.logout();
                    System.out.println("Admin Logged Out!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
    }
}
