
package com.medivault.services;

import com.medivault.models.User;

public class AuthService {

    private User currentUser;

    // Store logged-in user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Get logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Check Admin
    public boolean isAdmin() {
        return currentUser != null &&
               "Admin".equals(currentUser.getRole());
    }

    // Check Doctor
    public boolean isDoctor() {
        return currentUser != null &&
               "Doctor".equals(currentUser.getRole());
    }

    // Check Patient
    public boolean isPatient() {
        return currentUser != null &&
               "Patient".equals(currentUser.getRole());
    }

    // Check patient management permission
    public boolean canManagePatients() {
        return isAdmin();
    }

    // Check doctor management permission
    public boolean canManageDoctors() {
        return isAdmin();
    }

    // Check user management permission
    public boolean canManageUsers() {
        return isAdmin();
    }

    // Logout
    public void logout() {
        currentUser = null;
    }
}
