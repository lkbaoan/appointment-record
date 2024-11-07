package com.example.appointmentrecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
    ResultSet rs;
    final String GET_PROFILE = "SELECT * FROM user WHERE userId = ";
    final String INSERT_PROFILE = "INSERT INTO user(userDisplayName, birthday, heightIn, weightLbs, emergencyName, emergencyPhone, emergencyRel, note) VALUES ('%s', '%s', %d, %f, '%s', '%s', '%s', '%s')";
    final String UPDATE_PROFILE = "UPDATE user SET userDisplayName = '%s', birthday = '%s', heightIn = %d, weightLbs = %f, emergencyName = '%s', emergencyPhone= '%s', emergencyRel = '%s', note = '%s' WHERE userId = %d";
    final String DELETE_PROFILE = "DELETE FROM user WHERE userId = ";
    final String DELETE_RECORDS = "DELETE FROM record WHERE userId = ";
    /**
     * Create empty profile
     */
    public Profile(int index) {
        try {
            rs = DatabaseConnection.selectQuery(String.format(GET_PROFILE + index));
            if (rs.getInt("userId") == 0) {
                System.out.println("Not found user");
//                addProfile("New User", LocalDate.now().toString(), 0,0,0,"","","","");
            } else {
                userID = rs.getInt("userId");
                name = rs.getString("userDisplayName");
                birthday = rs.getString("birthday").isEmpty() ? LocalDate.now() : LocalDate.parse(rs.getString("birthday"));
                heightin = rs.getFloat("heightIn");
                weightlbs = rs.getFloat("weightLbs");
                emerName = rs.getString("emergencyName");
                emerPhone = rs.getString("emergencyPhone");
                emerRel = rs.getString("emergencyRel");
                note = rs.getString("note");
            }
        } catch (Exception e) {
            System.err.println("Failed to load profile: " + e);

        }
    }

    /**
     * Update profile to the database or create a new profile if not existed.
     * @param name String user name
     * @param birthday LocalDate birthday
     * @param heightft float height feet
     * @param heightin float height inch
     * @param weightlbs float weight lbs
     * @param eName String emergency name
     * @param ePhone String emergency phone number
     * @param eRel String emergency relationship
     * @param note String note text
     * @return rowAffected number of affected row in the database
     */
    public int updateProfile(String name, String birthday, int heightft, int heightin, float weightlbs, String eName, String ePhone, String eRel, String note) {
        int rowAffected;
        rowAffected = DatabaseConnection.executeUpdate(String.format(UPDATE_PROFILE, name, birthday, heightft*12+heightin, weightlbs, eName, ePhone, eRel, note, userID));
        this.name = name;
        this.birthday = LocalDate.parse(birthday);
        this.heightin = heightft*12+heightin;
        this.weightlbs = weightlbs;
        this.emerName = eName;
        this.emerPhone = ePhone;
        this.emerRel = eRel;
        this.note = note;
        return rowAffected;
    }
    /**
     * Add new profile to database.
     * @param name String user name
     * @param birthday LocalDate birthday
     * @param heightft float height feet
     * @param heightin float height inch
     * @param weightlbs float weight lbs
     * @param eName String emergency name
     * @param ePhone String emergency phone number
     * @param eRel String emergency relationship
     * @param note String note text
     * @return rowAffected number of affected row in the database
     */
    public void addProfile(String name, String birthday, int heightft, int heightin, float weightlbs, String eName, String ePhone,String eRel, String note) {
        DatabaseConnection.executeQuery(String.format(INSERT_PROFILE, name, birthday, heightft*12+heightin, weightlbs, eName, ePhone, eRel, note));
        userID = DatabaseConnection.getLastRowId();
        this.name = name;
        this.birthday = LocalDate.parse(birthday);
        this.heightin = heightft*12+heightin;
        this.weightlbs = weightlbs;
        this.emerName = eName;
        this.emerPhone = ePhone;
        this.emerRel = eRel;
        this.note = note;
    }

    /**
     * Delete profile and record related to the profile.
     */
    public void deleteProfile() {
        DatabaseConnection.executeQuery(String.format(DELETE_PROFILE + userID));
        DatabaseConnection.executeQuery(String.format(DELETE_RECORDS + userID));
    }
    /**
     * Getter
     */
    public int getUserID() {return userID;}
    public String getDisplayedName() {return name;}
    public LocalDate getDateOfBirth() {return birthday;}
    public int getHeightFeet() {return Math.floorDiv((int)heightin, 12);}
    public int getHeightInch() {return (int)(heightin % 12);}
    public float getWeight() {return weightlbs;}
    public String getEmerName() {return emerName;}
    public String getEmerPhone() {return emerPhone;}
    public String getEmerRel() {return emerRel;}
    public String getNote() {return note;}
}
