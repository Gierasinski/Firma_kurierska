package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Accountant {

    @FXML
    private Button btnAddWorker;

    @FXML
    private Button btnIssue;

    @FXML
    private Button btnexit;

    @FXML
    private Button btnpayout;

    @FXML
    private ListView<?> lvlistapracownikow;

    @FXML
    private TextField tfICity;

    @FXML
    private TextField tfIHousenumber;

    @FXML
    private TextField tfIRoad;

    @FXML
    private TextField tfIamount;

    @FXML
    private TextField tfIblanktf;

    @FXML
    private TextField tfIname;

    @FXML
    private TextField tfIsurname;

    @FXML
    private TextField tfWblanktf;

    @FXML
    private TextField tfWbranchname;

    @FXML
    private TextField tfWcity;

    @FXML
    private TextField tfWdate;

    @FXML
    private TextField tfWhousenumber;

    @FXML
    private TextField tfWname;

    @FXML
    private TextField tfWposition;

    @FXML
    private TextField tfWroad;

    @FXML
    private TextField tfWsalary;

    @FXML
    private TextField tfWsurname;

    @FXML
    private TextField tfWworkernumber;

    @FXML
    private TextField tfamount;

    @FXML
    private TextField tfpesel;

    @FXML
    void AddWorker(ActionEvent event) {

    }

    @FXML
    void Issue(ActionEvent event) {

    }

    @FXML
    void SwitchToWorkerPanel(ActionEvent event)throws IOException {
        App.setRoot("WorkerPanel");
    }

    @FXML
    void payout(ActionEvent event) {

    }

}
