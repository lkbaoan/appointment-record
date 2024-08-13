package com.example.appointmentrecord;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collection;

public class MainController {
    @FXML
    private ListView<String> usersListView;
    @FXML
    private TabPane tabPane;
    /** Profile variables */
    @FXML
    TextField textName;
    @FXML
    DatePicker textBirthday;
    @FXML TextField textHeightft;
    @FXML TextField textHeightin;
    @FXML TextField textWeightLbs;
    @FXML TextField textEmergencyName;
    @FXML TextField textEmergencyPhone;
    @FXML TextField textEmergencyRel;
    @FXML TextField textNote;

    @FXML
    public void initialize() {
        // Get list of user from database
        UserList userList = new UserList();
        for (String user : userList.getUserList()) {
            usersListView.getItems().add(user);
        }
        // Disable tabPane if no user
        if (usersListView.getItems().isEmpty()) {
            tabPane.setDisable(true);
        }
    }
    // Create new empty user
    @FXML
    public void OnAddUserButtonClick() {
        Button text = new Button("text");
        usersListView.getItems().add("New User " + usersListView.getItems().size());

    }
    @FXML
    public void handleMouseClick() {
        if (tabPane.isDisable()) {tabPane.setDisable(false);}
        System.out.println("clicked on " + usersListView.getSelectionModel().getSelectedIndex());
        Profile selectedProfile = new Profile(usersListView.getSelectionModel().getSelectedIndex()+1);
        System.out.printf("%d | %s | %s | %d | %d | %f | %s | %s | %s | %s\n", selectedProfile.getUserID(), selectedProfile.getDisplayedName(), selectedProfile.getDateOfBirth(), selectedProfile.getHeightFeet(), selectedProfile.getHeightInch(), selectedProfile.getWeight(), selectedProfile.getEmerName(), selectedProfile.getEmerPhone(), selectedProfile.getEmerRel(), selectedProfile.getNote());
        // Set value to profile
        textName.setText(selectedProfile.getDisplayedName());
        textBirthday.setValue(selectedProfile.getDateOfBirth());
        textHeightft.setText(String.valueOf(selectedProfile.getHeightFeet()));
        textHeightin.setText(String.valueOf(selectedProfile.getHeightInch()));
        textWeightLbs.setText(String.valueOf(selectedProfile.getWeight()));
        textEmergencyName.setText(selectedProfile.getEmerName());
        textEmergencyPhone.setText(selectedProfile.getEmerPhone());
        textEmergencyRel.setText(selectedProfile.getEmerRel());
        textNote.setText(selectedProfile.getNote());
    }
}
