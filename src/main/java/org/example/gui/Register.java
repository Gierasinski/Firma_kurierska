package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.App;
import org.example.client.Client;

import java.io.IOException;
import java.sql.SQLException;

public class Register {
    Client client = new Client();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Button btnexit;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfRPassword;
    @FXML
    private CheckBox cbPolicy;

    @FXML
    void SwitchToLogin(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    void registerPush(ActionEvent event) {
        try {
            if(cbPolicy.isSelected()){
                if(!DataValidation.validateEmail(tfEmail)){
                    alert.setTitle("Validate Email");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Enter Valid Email");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
                    return;
                }
            if(!tfEmail.getText().isEmpty() && !tfName.getText().isEmpty() && !tfPassword.getText().isEmpty()
                    && !tfRPassword.getText().isEmpty()) {
                if(tfPassword.getText().equals(tfRPassword.getText())) {
                    if(tfPassword.getText().length()<6){
                        alert.setTitle("Register error");
                        alert.setHeaderText("Check your data");
                        alert.setContentText("Passwords needs to have at least 6 characters");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                    }else if(client.register("", "", "123", tfEmail.getText(), tfName.getText(), tfPassword.getText())){
                       alert.setTitle("Register Succes");
                       alert.setHeaderText("Account has been created");
                       alert.setContentText("You can Log in now");
                       alert.showAndWait().ifPresent(rs -> {
                           if (rs == ButtonType.OK) {
                               try {
                                   App.setRoot("Login");
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                       });
                   }else{
                       alert.setTitle("Register error");
                       alert.setHeaderText("Check your data");
                       alert.setContentText("Login or E-mail are already used");
                       alert.showAndWait().ifPresent(rs -> {
                           if (rs == ButtonType.OK) {
                               System.out.println("Pressed OK.");
                           }
                       });
                   }
                }else{
                    alert.setTitle("Register error");
                    alert.setHeaderText("Check your data");
                    alert.setContentText("Password and Confirm password do not match");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
                }
            }else{
                alert.setTitle("Register error");
                alert.setHeaderText("Check your data");
                alert.setContentText("All fields needs to be filled");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }
            }else{
                alert.setTitle("Register error");
                alert.setHeaderText("Check your data");
                alert.setContentText("You need to accept regulations and privacy policy to proceed");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
                }
        } catch (SQLException e) {
            alert.setTitle("Blad SQL");
            alert.setHeaderText("Sprawdz poprawnosc danych");
            alert.setContentText(e.getMessage());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        } catch (Exception e){
            alert.setTitle("Blad dodawania");
            alert.setHeaderText("Sprawdz poprawnosc danych");
            alert.setContentText(e.getMessage());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }

}
