package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.PT.Employee;

import java.io.IOException;
import java.sql.SQLException;

public class WorkerPanel {

    @FXML
    private Button btnexit;

    @FXML
    private Button btnlogin;

    @FXML
    private TextField tfworkercode;

    @FXML
    void SwitchToLogin(ActionEvent event)throws IOException {
        Employee employee = null;
        App.setRoot("Delivery");
        String code = tfworkercode.getText();
        try {
            if(employee.loginEmployee(code) == 1){
                App.setRoot("Delivery");
            }else if(employee.loginEmployee(code) == 2){
                App.setRoot("Storekeeper");
            }else if(employee.loginEmployee(code) == 3){
                App.setRoot("Accountant");
            }else{
               tfworkercode.setText("Nie ma takiego pracownika");
                App.setRoot("Login");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void workerlogin(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }

}
