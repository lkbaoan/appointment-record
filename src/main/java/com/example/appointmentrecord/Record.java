package com.example.appointmentrecord;

import java.time.LocalDate;
import java.time.LocalTime;

public class Record {
    private int recId;
    private int count;
    private  String recLabel;
    private LocalDate recDate;
    private LocalTime recTime;
    private String recCategory;

    public Record(int recId ,int count, String recLabel, LocalDate recDate, LocalTime recTime, String recCategory) {
        this.recId = recId;
        this.count = count;
        this.recLabel = recLabel;
        this.recDate = recDate;
        this.recTime = recTime;
        this.recCategory = recCategory;
    }

    public int getId() {return recId;}
    public int getCount() {return count;}
    public String getRecLabel() {return recLabel;}
    public LocalDate getRecDate() {return recDate;}
    public LocalTime getRecTime() {return recTime;}
    public String getRecCategory() {return recCategory;}

    public void setCount(int count) {this.count = count;}
    public void setRecLabel(String recLabel) {
        this.recLabel = recLabel;
    }

    public void setRecDate(LocalDate recDate) {
        this.recDate = recDate;
    }

    public void setRecTime(LocalTime recTime) {
        this.recTime = recTime;
    }

    public void setRecCategory(String recCategory) {
        this.recCategory = recCategory;
    }
    public void deleteRecord() {
        DatabaseConnection.executeQuery(String.format("DELETE FROM record where recId = " + recId));
    }
}
