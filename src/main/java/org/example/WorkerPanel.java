package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WorkerPanel {

    @FXML
    private Button btnDelivery;

    @FXML
    private Button btnStorekeeper;

    @FXML
    private Button btnaccountant;

    @FXML
    private Button btnexit;

    @FXML
    void SwitchToAccountant(ActionEvent event) throws IOException {
        App.setRoot("Accountant");
    }

    @FXML
    void SwitchToDelivery(ActionEvent event) throws IOException  {
        App.setRoot("Delivery");
    }

    @FXML
    void SwitchToLogin(ActionEvent event) throws IOException  {
        App.setRoot("Login");
    }

    @FXML
    void SwitchtoStorekeeper(ActionEvent event) throws IOException  {
        App.setRoot("Storekeeper");
    }

}
