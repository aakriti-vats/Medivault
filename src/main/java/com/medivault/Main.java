
package com.medivault;

import java.util.Scanner;

import com.medivault.dao.UserDAO;
import com.medivault.models.User;
import com.medivault.services.AuthService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        AuthService auth = new AuthService();

        System.out.println("===== MEDIVAULT LOGIN =====");

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User loggedInUser = userDAO.loginUser(email, password);

        if (loggedInUser == null) {
            System.out.println("Invalid Email or Password!");
        } else {

            auth.setCurrentUser(loggedInUser);

            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, " + loggedInUser.getUsername());
            System.out.println("Role: " + loggedInUser.getRole());

            if (auth.isAdmin()) {

                AdminMenu adminMenu = new AdminMenu();
                adminMenu.showMenu(auth, sc);

            } else if (auth.isDoctor()) {

                System.out.println("Doctor Dashboard - Coming Soon!");

            } else if (auth.isPatient()) {

                System.out.println("Patient Dashboard - Coming Soon!");

            }
        }

        sc.close();
    }
}
