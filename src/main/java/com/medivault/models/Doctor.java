
package com.medivault.models;

public class Doctor {

    private int doctorId;
    private String name;
    private String specialization;
    private String phone;
    private String email;
    private int experience;
    private String qualification;

    // Constructor
    public Doctor(int doctorId, String name,
                  String specialization, String phone,
                  String email, int experience,
                  String qualification) {

        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.qualification = qualification;
    }

    // Getters
    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public String getQualification() {
        return qualification;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
