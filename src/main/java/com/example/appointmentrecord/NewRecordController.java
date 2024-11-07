package com.example.appointmentrecord;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NewRecordController implements Initializable {
    @FXML TextField newLabel;
    @FXML TextField newCategory;
    @FXML DateTimePicker dateTimePicker;
    @FXML Button cancelButton;
    @FXML Button saveButton;
    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateTimePicker.setFormat("yyyy-MM-dd HH:mm:ss");
        dateTimePicker.setDateTimeValue(LocalDateTime.now());
    }
    // close window
    @FXML public void onCancelButton() {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    // save record to database
    @FXML public  void onSaveRecord() {
        System.out.println("Hello world");
        int userId = MainController.selectedProfile.getUserID();
        String label = newLabel.getText() != null ? newLabel.getText() : "";
        LocalDate date = dateTimePicker.getDateTimeValue().toLocalDate();
        LocalTime time = dateTimePicker.getDateTimeValue().toLocalTime();
        String category = newCategory.getText() != null ? newCategory.getText() : "";
        System.out.println(date.toString() + " " + time.toString());
        new AddRecord(userId, label, date, time, category);
        stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
