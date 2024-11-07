package com.example.appointmentrecord;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordsCollection {
    private List<Record> records;
    private ResultSet rs;

    private final String GET_RECORD = "SELECT * FROM record WHERE userId = ";
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
    public RecordsCollection(int userId) {
        records = new ArrayList<>();
        try {
            rs = DatabaseConnection.selectQuery(String.format(GET_RECORD + userId));
            int count = 0;
            while (rs.next()) {
                count++;
                int recId = rs.getInt("recId");
                String recLabel = rs.getString("recLabel");
                LocalDate recDate = rs.getString("recDate").isEmpty() ? LocalDate.now() : LocalDateTime.parse(rs.getString("recDate"), dtf).toLocalDate();
                LocalTime recTime = rs.getString("recDate").isEmpty() ? LocalTime.now() : LocalDateTime.parse(rs.getString("recDate"), dtf).toLocalTime();
                String recCategory = rs.getString("recCategory");
                records.add(new Record(recId, count ,recLabel, recDate, recTime, recCategory));
            }
        } catch (Exception e) {
            System.err.println("(8)Failed to load records: " + e);
        }
    }

    /**
     * Getter for records collection
     * @return list of record
     */
    public List<Record> getRecords() {
        return records;
    }
}
