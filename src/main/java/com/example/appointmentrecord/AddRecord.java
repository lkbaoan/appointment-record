package com.example.appointmentrecord;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddRecord {
    final String ADD_RECORD = "INSERT INTO record(recLabel, recDate, recCategory, userId) VALUES ('%s','%s','%s', %d)";
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");


    public  AddRecord(int userId, String label, LocalDate date, LocalTime time, String category) {
        String dateTime = date + " " + time.format(tf);
        String query = String.format(ADD_RECORD, label, dateTime, category, userId);
        DatabaseConnection.executeQuery(query);
    }
}
