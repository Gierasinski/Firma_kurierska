package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {

    @FXML
    private Button btnCreateanaccount;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnWorker;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnCreateanaccountClicked(ActionEvent event) {

    }

    @FXML
    void btnUserClicked(ActionEvent event) {

    }

    @FXML
    void btnWorkerClicked(ActionEvent event) {

    }

    @FXML
    void switchToUserPanel(ActionEvent event) throws IOException {
        App.setRoot("UserPanel");
    }

}
