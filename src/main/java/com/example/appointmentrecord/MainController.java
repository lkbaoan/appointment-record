package com.example.appointmentrecord;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class MainController {
    @FXML private ListView<String> usersListView;
    @FXML private TabPane tabPane;
    public static Profile selectedProfile;
    private UserList userList;
    private RecordsCollection recordsCollection;
    /** Profile variables */
    @FXML TextField textName;
    @FXML DatePicker textBirthday;
    @FXML TextField textHeightft;
    @FXML TextField textHeightin;
    @FXML TextField textWeightLbs;
    @FXML TextField textEmergencyName;
    @FXML TextField textEmergencyPhone;
    @FXML TextField textEmergencyRel;
    @FXML TextField textNote;
    @FXML Label ageCount;
    /** Table Record */
    @FXML TableView<Record> tableRecord;
    @FXML private TableColumn<Record, Integer> count;
    @FXML private TableColumn<Record, String> recLabel;
    @FXML private TableColumn<Record, LocalDate> recDate;
    @FXML private TableColumn<Record, LocalTime> recTime;
    @FXML private TableColumn<Record, String> recCategory;

    @FXML public void initialize() {
        DatabaseConnection db = new DatabaseConnection();
        // add to listview
        userList = new UserList();
        for (String user : userList.getUserList().keySet()) {
            usersListView.getItems().add(user);
        }
        // disable tabpane by default
        tabPane.setDisable(true);
        count.setCellValueFactory((new PropertyValueFactory<Record, Integer>("count")));;
        recLabel.setCellValueFactory(new PropertyValueFactory<Record, String>("recLabel"));
        recDate.setCellValueFactory(new PropertyValueFactory<Record, LocalDate>("recDate"));
        recTime.setCellValueFactory(new PropertyValueFactory<Record, LocalTime>("recTime"));
        recCategory.setCellValueFactory(new PropertyValueFactory<Record, String>("recCategory"));
    }

    @FXML public void onAddUserButtonClick() {
        usersListView.getItems().add("New User " + usersListView.getItems().size());
    }
    @FXML public void onSelectProfile() {
        if (tabPane.isDisable()) {tabPane.setDisable(false);}
        int selectedId = 0;
        if (!usersListView.getSelectionModel().getSelectedItem().contains("New User")) {
            selectedId = userList.getUserList().get(usersListView.getSelectionModel().getSelectedItem());
        }

        selectedProfile = new Profile(selectedId);
        // set text
        textName.setText(selectedProfile.getDisplayedName());
        textBirthday.setValue(selectedProfile.getDateOfBirth());
        textHeightft.setText(String.valueOf(selectedProfile.getHeightFeet()));
        textHeightin.setText(String.valueOf(selectedProfile.getHeightInch()));
        textWeightLbs.setText(String.valueOf(selectedProfile.getWeight()));
        textEmergencyName.setText(selectedProfile.getEmerName());
        textEmergencyPhone.setText(selectedProfile.getEmerPhone());
        textEmergencyRel.setText(selectedProfile.getEmerRel());
        textNote.setText(selectedProfile.getNote());
        if (selectedProfile.getDateOfBirth() != null) {
            ageCount.setText("Age: " + Period.between(selectedProfile.getDateOfBirth(), LocalDate.now()).getYears());
        } else {
            ageCount.setText("Age: ");
        }

        // load records
        if (selectedId != 0) {
            recordsCollection = new RecordsCollection(selectedId);
            tableRecord.setItems(FXCollections.observableArrayList(recordsCollection.getRecords()));
        }

    }
    @FXML public void onSaveChanges() {
        try {
            // get all profile variables
            String name = textName != null ? textName.getText() : "";
            String date = textBirthday.getValue() != null ? textBirthday.getValue().toString() : "";
            int heightft = textHeightft != null ? Integer.valueOf(textHeightft.getText()) : 0;
            int heightin = textHeightin != null ? Integer.valueOf(textHeightin.getText()) : 0;
            float weightlbs = textWeightLbs != null ? Float.valueOf(textWeightLbs.getText()) : 0;
            String eName = textEmergencyName != null ? textEmergencyName.getText() : "";
            String ePhone = textEmergencyPhone != null ? textEmergencyPhone.getText() : "";
            String eRel = textEmergencyRel != null ? textEmergencyRel.getText() : "";
            String note = textNote != null ? textNote.getText() : "";

            // if user not "New User", update it. Else create a new profile in the database
            if (usersListView.getSelectionModel().getSelectedItem().contains("New User")) {
                selectedProfile.addProfile(name, date, heightft, heightin, weightlbs, eName, ePhone, eRel, note);
            } else {
                selectedProfile.updateProfile(name, date, heightft, heightin, weightlbs, eName, ePhone, eRel, note);
            }

            // reload listview
            usersListView.getItems().clear();
            userList = new UserList();
            for (String user : userList.getUserList().keySet()) {
                usersListView.getItems().add(user);
            }
        } catch (Exception e) {
            System.err.println("Failed profile save changes: " + e);
        }
    }
    // delete profile and remove from listview
    @FXML public void onDeleteProfile() {
        try {

            selectedProfile.deleteProfile();
            int selectedIndex = usersListView.getSelectionModel().getSelectedIndex();
            usersListView.getItems().remove(selectedIndex);
            tabPane.setDisable(true);
        } catch (Exception e) {
            System.err.println("Failed to delete profile: " + e);
        }
    }
    // create new window
    @FXML public void onAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("new-record.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 160);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Add Record");
        stage.setScene(scene);
        stage.show();
    }
    // refresh tableview
    @FXML public void onRefreshRecord() {
        recordsCollection = new RecordsCollection(selectedProfile.getUserID());
        tableRecord.setItems(FXCollections.observableArrayList(recordsCollection.getRecords()));
    }
    // delete record
    @FXML public void onDeleteRecord() {
        Record record = tableRecord.getSelectionModel().getSelectedItem();
        record.deleteRecord();
    }

}
