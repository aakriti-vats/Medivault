
package com.medivault;

import java.util.List;
import java.util.Scanner;

import com.medivault.dao.UserDAO;
import com.medivault.models.User;
import com.medivault.services.AuthService;

public class UserMenu {

    private UserDAO userDAO = new UserDAO();

    public void showMenu(Scanner sc, AuthService auth) {

        if (!auth.isAdmin()) {
            System.out.println("Access Denied!");
            return;
        }

        int choice;

        do {
            System.out.println("\n===== USER MANAGEMENT =====");
            System.out.println("1. View All Users");
            System.out.println("2. Search User By ID");
            System.out.println("3. Delete User");
            System.out.println("4. Back to Admin Menu");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    searchUser(sc);
                    break;

                case 3:
                    deleteUser(sc, auth);
                    break;

                case 4:
                    System.out.println("Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
    }

    // View All Users
    private void viewAllUsers() {

        List<User> users = userDAO.getAllUsers();

        System.out.println("\n===== REGISTERED USERS =====");

        if (users.isEmpty()) {
            System.out.println("No Users Found!");
            return;
        }

        for (User user : users) {

            System.out.println(
                "ID: " + user.getUserId() +
                " | Name: " + user.getUsername() +
                " | Email: " + user.getEmail() +
                " | Role: " + user.getRole()
            );
        }
    }

    // Search User
    private void searchUser(Scanner sc) {

        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());

        User user = userDAO.getUserById(id);

        if (user != null) {

            System.out.println("\nUser Found!");
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());

        } else {
            System.out.println("User Not Found!");
        }
    }

    // Delete User
    private void deleteUser(Scanner sc, AuthService auth) {

        System.out.print("Enter User ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine());

        User user = userDAO.getUserById(id);

        if (user == null) {
            System.out.println("User Not Found!");
            return;
        }

        // Prevent Admin from deleting their own account
        if (user.getUserId() == auth.getCurrentUser().getUserId()) {
            System.out.println("You cannot delete your own account!");
            return;
        }

        System.out.print("Are you sure? (yes/no): ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            if (userDAO.deleteUser(id)) {
                System.out.println("User Deleted Successfully!");
            } else {
                System.out.println("User Deletion Failed!");
            }

        } else {
            System.out.println("Deletion Cancelled!");
        }
    }
}
