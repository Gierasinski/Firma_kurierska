package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.worker.Employee;
import org.example.worker.EmployeeHolder;

import java.io.IOException;
import java.sql.SQLException;

public class WorkerPanel {
    private static EmployeeHolder employeeHolder = EmployeeHolder.getInstance();

    @FXML
    private Button btnexit;

    @FXML
    private Button btnlogin;

    @FXML
    private TextField tfworkercode;

    @FXML
    void SwitchToLogin(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }
    @FXML
    void workerlogin(ActionEvent event)throws IOException {
        employeeHolder.setEmployee(new Employee());


        String code = tfworkercode.getText();
        if(!tfworkercode.getText().isEmpty()) {
            try {
                if (employeeHolder.getEmployee().loginEmployee(code) == 1) {
                    App.setRoot("Delivery");
                } else if (employeeHolder.getEmployee().loginEmployee(code) == 2) {
                    App.setRoot("Storekeeper");
                } else if (employeeHolder.getEmployee().loginEmployee(code) == 3) {
                    App.setRoot("Accountant");
                } else {
                    tfworkercode.setText("Nie ma takiego pracownika");

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            tfworkercode.setText("Nie ma takiego pracownika");
        }


    }

}
