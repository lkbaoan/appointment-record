package com.example.appointmentrecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    private VBox usersVbox;

    /**
     * Create new empty user
     */
    @FXML
    void OnAddUserButtonClick() {
        Button text = new Button("text");
        usersVbox.getChildren().add(text);
    }
}
