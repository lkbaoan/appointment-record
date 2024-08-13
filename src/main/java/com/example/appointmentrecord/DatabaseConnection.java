package com.example.appointmentrecord;

import java.sql.*;

public class DatabaseConnection {
    private final String URL = "jdbc:sqlite:./src/database/database.db";
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            if (connection != null) {
                System.out.println("Driver name: " + connection.getMetaData().getDriverName());
            }
        } catch (SQLException e) {
            System.err.println("(0)Failed to establish database connection");
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("(1)Failed to execute query");
        }
        return rs;
    }
    public int executeUpdate(String query) {
        int affectedRows = 0;
        try {
            statement = connection.createStatement();
            affectedRows = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("(2)Failed to execute query");
        }
        return affectedRows;
    }
}
