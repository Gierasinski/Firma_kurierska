package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.App;
import org.example.client.Client;
import org.example.client.ClientHolder;
import org.example.PostgreSQL.ManageDataBase;

import java.io.IOException;
import java.sql.SQLException;

public class Login {
    private static ClientHolder clientHolder = ClientHolder.getInstance();
    @FXML
    private Button btnReInitDatabase;

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
    private Label lbLogin;

    @FXML
    void btnCreateanaccountClicked(ActionEvent event)throws IOException {
        App.setRoot("Register");
    }

    @FXML
    void btnUserClicked(ActionEvent event) {

    }

    @FXML
    void switchToUserPanel(ActionEvent event) throws IOException {
    clientHolder.setClient(new Client());
        try {
            if(clientHolder.getClient().login(tfUsername.getText(),pfPassword.getText())){
                App.setRoot("UserPanel");
            }else{
                lbLogin.setText("Wrong Username or Password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void SwitchToWorkerPanel(ActionEvent event) throws IOException {
        App.setRoot("WorkerPanel");
    }
    @FXML
    void switchToComplaint(ActionEvent event) throws IOException {
        App.setRoot("Complaint");
    }
    @FXML
    void pushReInitDataBase(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Possible data loss");
        alert.setContentText("Are you sure you want to restart your database? All data will be lost");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
                ManageDataBase base = new ManageDataBase();
                base.reInitDataBase();
            }
        });

    }

}
