package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserPanel {

    @FXML
    private Tab TabProfile;

    @FXML
    private Tab TabSendingapackage;

    @FXML
    private Tab TabTrackingyourpackage;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnSavePassword;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnlogout;

    @FXML
    private CheckBox chbhousetohouse;

    @FXML
    private CheckBox chbhousetopl;

    @FXML
    private CheckBox chbpltohouse;

    @FXML
    private CheckBox chbpltopl;

    @FXML
    private TextField tfAdress;

    @FXML
    private TextField tfCurrentPassword;

    @FXML
    private TextField tfDateofBirth;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNewPassword;

    @FXML
    private TextField tfNewpassword;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfadressfrom;

    @FXML
    private TextField tfadressto;

    @FXML
    private TextField tfcityfrom;

    @FXML
    private TextField tfcityto;

    @FXML
    private TextField tfemailadfrom;

    @FXML
    private TextField tfemailadto;

    @FXML
    private TextField tfnameandsurfrom;

    @FXML
    private TextField tfnameandsurto;

    @FXML
    private TextField tfpackagecode;

    @FXML
    private TextField tfphonefrom;

    @FXML
    private TextField tfphoneto;

    @FXML
    private TextField tfpostcodefrom;

    @FXML
    private TextField tfpostcodeto;

    @FXML
    void logout(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }

}
