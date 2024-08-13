package com.example.appointmentrecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    DatabaseConnection db;
    ResultSet rs;
    List<String> userList;
    final String GET_USER_LIST = "SELECT userDisplayName from user";

    public UserList() {
        db = new DatabaseConnection();
        rs = db.executeQuery(String.format(GET_USER_LIST));
        userList = new ArrayList<>();
        try {
            while (rs.next()) {
                userList.add(rs.getString("userDisplayName"));
            }
        } catch (SQLException e) {
            System.err.println(" (3)Failed to fetch user list");
        }
    }
    public List<String> getUserList() {
        return userList;
    }
}
