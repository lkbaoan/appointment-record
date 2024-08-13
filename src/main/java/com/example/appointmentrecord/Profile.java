package com.example.appointmentrecord;

import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Class hold a user profile.
 */
public class Profile {
    private int userID;
    private String name;
    private LocalDate birthday;
    private float heightin;
    private float weightlbs;
    private String emerName;
    private String emerPhone;
    private String emerRel;
    private String note;
    DatabaseConnection db;
    ResultSet rs;
    final String GET_PROFILE = "SELECT * FROM user WHERE userId = ";
    /**
     * Create empty profile
     */
    public Profile(int index) {
        db = new DatabaseConnection();
        rs = db.executeQuery(String.format(GET_PROFILE + index));
        try {
            userID = index;
            while (rs.next()) {
                name = rs.getString("userDisplayName");
                birthday = LocalDate.parse(rs.getString("birthday"));
                heightin = rs.getFloat("heightIn");
                weightlbs = rs.getFloat("weightLbs");
                emerName = rs.getString("emergencyName");
                emerPhone = rs.getString("emergencyPhone");
                emerRel = rs.getString("emergencyRel");
                note = rs.getString("note");
            }
        } catch (SQLException e) {
            System.err.println("(4)Failed to load profile: " + e);

        }
    }


    /**
     * Getter
     */
    public int getUserID() {return userID;}
    public String getDisplayedName() {
        return name;
    }
    public LocalDate getDateOfBirth() {
        return birthday;
    }
    public int getHeightFeet() {
        return Math.floorDiv((int)heightin, 12);
    }
    public int getHeightInch() {
        return (int)(heightin % 12);
    }
    public float getWeight() {
        return weightlbs;
    }
    public String getEmerName() {return emerName;}
    public String getEmerPhone() {return emerPhone;}
    public String getEmerRel() {return emerRel;}
    public String getNote() {return note;}
}
