package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.App;
import org.example.client.Client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Complaint implements Initializable {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Client client = new Client();

    @FXML
    private ChoiceBox<String> cbComplaintReason, cbDeclarant;

    @FXML
    private TextArea taDetails;

    @FXML
    private TextField tfMail, tfName, tfParcelNumber, tfPhone;

    @FXML
    private Label lName, lPhone, lMail, lParcel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String dec[] = {"Shipper", "Recipient"};
        String rea[] = {"Other", "Parcel lost", "Parcel delayed"};

        cbDeclarant.getItems().addAll(dec);
        cbComplaintReason.getItems().addAll(rea);

        cbDeclarant.setValue(dec[0]);
        cbComplaintReason.setValue(rea[0]);

        final int MAX_CHARS = 300;

        taDetails.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <=MAX_CHARS ? change: null));

        hideErrorLabels();
    }
    @FXML
    void onSubmitComplaint() throws IOException, SQLException {
        int validation = 0;
        hideErrorLabels();
        if(!DataValidation.validateLazy(tfName)) {
            lName.setVisible(true);
            validation++;
        }
        if(!DataValidation.validateMobile(tfPhone)) {
            lPhone.setVisible(true);
            validation++;
        }
        if(!DataValidation.validateEmail(tfMail)) {
            lMail.setVisible(true);
            validation++;
        }
        if(validation>0){
            alert.setTitle("Some information's are invalid");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Informations");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
            return;
        }
        client.insertComplaint(cbDeclarant.getValue(),tfName.getText(), Integer.valueOf(tfPhone.getText()),
         tfMail.getText(), Long.valueOf(tfParcelNumber.getText()), cbComplaintReason.getValue(), taDetails.getText());
        alert.setTitle("Complaint submited");
        alert.setHeaderText(null);
        alert.setContentText("Please wait for a reply");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
                try {
                    App.setRoot("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return;
    }
    @FXML
    void onExit()throws IOException {
        App.setRoot("Login");
    }

    void hideErrorLabels(){
        lName.setVisible(false);
        lPhone.setVisible(false);
        lMail.setVisible(false);
        lParcel.setVisible(false);
    }
}