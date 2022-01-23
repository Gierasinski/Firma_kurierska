package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WorkerPanel {

    @FXML
    private Button btnexit;

    @FXML
    private Button btnlogin;

    @FXML
    private TextField tfworkercode;

    @FXML
    void SwitchToLogin(ActionEvent event)throws IOException {
    App.setRoot("Login");
    }

}
