package com.example.appointmentrecord;

/**
 * Class hold emergency contact information.
 */
public class EmergencyContact
{
    private String name;
    private String phoneNumber;
    private String relationship;

    /**
     * Constructor with empty info.
     */
    public EmergencyContact(){
        name = "";
        phoneNumber = "";
        relationship = "";
    }
    /**
     * Setter
     */
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    /**
     * Getter
     */
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getRelationship() {
        return relationship;
    }
}
