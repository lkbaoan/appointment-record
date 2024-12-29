package com.example.appointmentrecord;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:./database/database.db";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    /**
     * Initial connection to database.
     */
    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("(0)Failed to establish database connection " + e);
        }
    }

    /**
     * Execute select query.
     * @param query string
     * @return rs ResultSet
     */
    public static ResultSet selectQuery(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Failed to select query " + e);
        }
        return rs;
    }

    /**
     * Execute insert and delete query.
     * @param query string
     */
    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Failed to execute query " + e);
        }
    }

    /**
     * Execute update.
     * @param query string
     * @return affectedRows int
     */
    public static int executeUpdate(String query) {
        int affectedRows = 0;
        try {
            statement = connection.createStatement();
            affectedRows = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Failed to execute update " + e);
        }
        return affectedRows;
    }

    /**
     * Get userid of the inserted profile
     * @return userid
     */
    public static int getLastRowId() {
        int i = -1;
        try {
            statement = connection.createStatement();
            i = statement.executeQuery("SELECT last_insert_rowid()").getInt(1);
        } catch (SQLException e) {
            System.err.println("Failed to get last rowid " + e);
        }
        return i;
    }
}
