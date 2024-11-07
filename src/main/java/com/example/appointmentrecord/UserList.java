package com.example.appointmentrecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {
    ResultSet rs;
    Map<String, Integer> userList;
    final String GET_USER_LIST = "SELECT userDisplayName, userId from user";

    public UserList() {
        rs = DatabaseConnection.selectQuery(String.format(GET_USER_LIST));
        userList = new HashMap<>();
        try {
            while (rs.next()) {
                userList.put(rs.getString("userDisplayName"), rs.getInt("userId"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch user list");
        }
    }

    /**
     * Getter for user list
     * @return userlist
     */
    public Map<String, Integer> getUserList() {
        return userList;
    }

}
