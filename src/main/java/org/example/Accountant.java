package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Accountant {

    @FXML
    private Button btnGiveBonuses;

    @FXML
    private Button btnexit;

    @FXML
    private Button btnpayout;

    @FXML
    private ListView<?> lvlistapracownikow;

    @FXML
    private TextField tfamount;

    @FXML
    void SwitchToWorkerPanel(ActionEvent event) throws IOException {
        App.setRoot("WorkerPanel");
    }

}
