package com.example.appointmentrecord;

import java.util.Date;

/**
 * Class hold a user profile.
 */
public class Profile {
    private String displayedName;
    private String fullName;
    private Date dateOfBirth;
    private int heightFeet;
    private int heightInch;
    private float weight;
    private EmergencyContact emergencyContact;

    /**
     * Create empty profile
     */
    public Profile() {
        displayedName = "user";
        fullName =  "";
        dateOfBirth = new Date();
        heightFeet = 0;
        heightInch = 0;
        weight = 0;
        emergencyContact = new EmergencyContact();
    }


    /**
     * Setter
     */
    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }
    public void setHeightInch(int heightInch) {
        this.heightInch = heightInch;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    /**
     * Getter
     */
    public String getDisplayedName() {
        return displayedName;
    }
    public String getFullName() {
        return fullName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public int getHeightFeet() {
        return heightFeet;
    }
    public int getHeightInch() {
        return heightInch;
    }
    public float getWeight() {
        return weight;
    }
    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }
}
