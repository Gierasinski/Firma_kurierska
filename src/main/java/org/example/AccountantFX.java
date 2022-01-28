package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.PT.Accountant;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountantFX {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Accountant accountant = new Accountant();

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
        if(!tfWname.getText().isEmpty() && !tfWsurname.getText().isEmpty() && !tfWbranchname.getText().isEmpty()
        && !tfWcity.getText().isEmpty() && !tfWsalary.getText().isEmpty() && !tfWroad.getText().isEmpty()
        && !tfWposition.getText().isEmpty() && !tfWhousenumber.getText().isEmpty()
        && !tfWdate.getText().isEmpty() && !tfpesel.getText().isEmpty()){
            accountant.addEmployee(tfWname.getText(),tfWsurname.getText(),tfWcity.getText(),tfWroad.getText(),tfWhousenumber.getText(),Integer.parseInt(tfWhousenumber.getText()),Integer.parseInt(tfpesel.getText()),tfWposition.getText(), Integer.parseInt(tfWsalary.getText()),null);
            alert.setTitle("Added");
            alert.setHeaderText("Delivery is add");
            alert.setContentText("Press OK");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }else{
            alert.setTitle("Error");
            alert.setHeaderText("Delivery is not Add");
            alert.setContentText("Check data");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    @FXML
    void Issue(ActionEvent event) throws FileNotFoundException {
        if(!tfIname.getText().isEmpty() && !tfIsurname.getText().isEmpty() && !tfIamount.getText().isEmpty()
        && !tfIRoad.getText().isEmpty() && !tfIHousenumber.getText().isEmpty() && !tfICity.getText().isEmpty()){
                accountant.getFacture(tfIname.getText(),tfIsurname.getText(),tfICity.getText(),tfIRoad.getText(),Integer.parseInt(tfIHousenumber.getText()));
            alert.setTitle("Issue");
            alert.setHeaderText("An invoice has been issued");
            alert.setContentText("Press OK");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }else{
            alert.setTitle("Error");
            alert.setHeaderText("An invoice has not been issued");
            alert.setContentText("Check data");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }

    @FXML
    void SwitchToWorkerPanel(ActionEvent event)throws IOException {
        App.setRoot("WorkerPanel");
    }

    @FXML
    void payout(ActionEvent event) {

    }

}
