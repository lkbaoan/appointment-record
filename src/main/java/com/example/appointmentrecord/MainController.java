package com.example.appointmentrecord;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    private Label testLabel;
    @FXML
    private VBox usersVbox;

    /**
     * Create new empty user
     */
    @FXML
    protected void onAddUserButtonClick() {
        testLabel.setText("Welcome to JavaFX Application!");
        Button text = new Button("text");
        usersVbox.getChildren().add(text);
    }
}
