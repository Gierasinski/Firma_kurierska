package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import org.example.App;
import org.example.worker.Delivery;

import java.io.IOException;


public class DeliveryFX {
    Delivery delivery = new Delivery();
    @FXML
    private Button btn6;

    @FXML
    private Button btnexit;

    @FXML
    private Button btndeliveredtoapercellocker;

    @FXML
    private Button btndeliveredtorecipient;


    @FXML
    private Button btnpickingupthepackagef;

    @FXML
    private Button btnpickingupthepackaget;

    @FXML
    private TextArea taDelivery;

    @FXML
    private TextField tfpackagenumberD;

    @FXML
    void bt6(ActionEvent event) {
            if(!tfpackagenumberD.getText().isEmpty()){
                long code = Long.parseLong(tfpackagenumberD.getText());
                taDelivery.setText(delivery.getInfoParcel(code));
            }
    }

    @FXML
    void deliveredtoaparcellocker(ActionEvent event) {
        if(!tfpackagenumberD.getText().isEmpty()) {
            long code = Long.parseLong(tfpackagenumberD.getText());
            delivery.deliveredToParcelLocker(code);
        }
    }

    @FXML
    void deliveredtotherecipient(ActionEvent event) {
        if(!tfpackagenumberD.getText().isEmpty()) {
            long code = Long.parseLong(tfpackagenumberD.getText());
            delivery.deliveredToTheRecipient(code);
        }

    }


    @FXML
    void pickupthepackage0(ActionEvent event) {
        if(!tfpackagenumberD.getText().isEmpty()) {
            long code = Long.parseLong(tfpackagenumberD.getText());
            delivery.pickUpTheParcel(code);
        }
    }


    @FXML
    void taDeliverymethod(InputMethodEvent event) {


    }
    @FXML
    void exit(ActionEvent event) throws IOException {
        App.setRoot("WorkerPanel");
    }

}
