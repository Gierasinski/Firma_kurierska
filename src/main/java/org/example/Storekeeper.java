package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Storekeeper {

    @FXML
    private Button btnexit;

    @FXML
    private Button btnpackageaccepted;

    @FXML
    private Button btnreportdamage;

    @FXML
    private TextField tfpackagenumberS;

    @FXML
    void packageaccepted(ActionEvent event) {

    }

    @FXML
    void reportdamage(ActionEvent event) {

    }

    @FXML
    void switchtoWorkerpanel(ActionEvent event)throws IOException {
App.setRoot("WorkerPanel");
    }

}
