package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Register {

    @FXML
    private Button btnexit;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfRPassword;

    @FXML
    void SwitchToLogin(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

}
