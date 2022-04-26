package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.example.App;
import org.example.client.Account;
import org.example.global.Address;
import org.example.client.ClientHolder;
import org.example.parcel.Parcel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPanel implements Initializable {

    private float price = 0;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static ClientHolder clientHolder = ClientHolder.getInstance();
    @FXML
    private Tab TabProfile;
    @FXML
    private Pane FormPane;
    @FXML
    private Pane ShipperFlatDetails, RecipientFlatDetails;
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
    private Button btnNextPage;

    @FXML
    private Button btnNextPageSecond;

    @FXML
    private Button btnPreviousPage;

    @FXML
    private RadioButton fFlat,fLocker,tFlat,tLocker,sSmall,sMedium,sBig;

    @FXML
    private TextField tfAdress, tfCity, tfPostcode;

    @FXML
    private PasswordField tfCurrentPassword;

    @FXML
    private TextField tfDateofBirth;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfNewPassword;

    @FXML
    private PasswordField tfNewpassword;

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
    private TextField tfEmailFrom;

    @FXML
    private TextField tfEmailTo;

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
    private TextField tfstreetfrom;

    @FXML
    private TextField tfpostcodeto;

    @FXML
    private Label fPrice;

    @FXML
    private Label fAddressDescription;

    @FXML
    private Label tAddressDescription;

    @FXML
    private Label lLocalization,lStatus,lPackageID,lPayment,lAddress,lLoginLabel;

    //Warnings
    @FXML
    private Label lWarningEmail, lWarningMobile, lWarningPostcode, lWarningName, lWarningCity, lWarningStreet;
    @FXML
    private ChoiceBox<Long> choiceBoxParcel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            calculateCost();
            hideWarnings();
            choiceBoxParcel.getItems().addAll(clientHolder.getClient().getParcelsID());
            choiceBoxParcel.setOnAction(this::pushShowChoose);

            Account account = clientHolder.getClient().getAccount();
            lLoginLabel.setText(account.getLogin());
            tfName.setText(account.getName());
            tfSurname.setText(account.getSurname());

            Address address = account.getAddress();
            if(address != null) {
                tfAdress.setText(address.getStreet());
                tfCity.setText(address.getCity());
                tfPostcode.setText(address.getPostcode());
            }
            tfPhone.setText(account.getPhoneNumber());
            tfEmail.setText(account.getEmail());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logout(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void changeFromFunction(ActionEvent event) {
        calculateCost();
        if(fLocker.isSelected()) {
            fAddressDescription.setText("Locker name");
        }else if(fFlat.isSelected()){
            fAddressDescription.setText("House/Flat address");
        }
    }
    public void resetText(){
        tfnameandsurfrom.setText("");
         tfadressfrom.setText("");
         tfpostcodefrom.setText("");
         tfcityfrom.setText("");
         tfEmailFrom.setText("");
         tfphonefrom.setText("");
         tfnameandsurto.setText("");
         tfadressto.setText("");
         tfpostcodeto.setText("");
         tfcityto.setText("");
         tfEmailTo.setText("");
         tfphoneto.setText("");
        try {
            choiceBoxParcel.getItems().addAll(clientHolder.getClient().getParcelsID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void changeToFunction(ActionEvent event) {
        calculateCost();
        if(tLocker.isSelected()) {
            tAddressDescription.setText("Locker name");
        }else if(tFlat.isSelected()){
            tAddressDescription.setText("House/Flat address");
        }
    }
    @FXML
    public void changeSizeFunction(ActionEvent event) {
        calculateCost();
    }
    @FXML
    void pushSendButton(ActionEvent event) {
        if (!tfnameandsurfrom.getText().isEmpty() && !tfadressfrom.getText().isEmpty() && !tfpostcodefrom.getText().isEmpty()
                && !tfcityfrom.getText().isEmpty() && !tfEmailFrom.getText().isEmpty() && !tfphonefrom.getText().isEmpty() &&
                !tfnameandsurto.getText().isEmpty() && !tfadressto.getText().isEmpty() && !tfpostcodeto.getText().isEmpty()
                && !tfcityto.getText().isEmpty() && !tfEmailTo.getText().isEmpty() && !tfphoneto.getText().isEmpty()) {

            float weight = 25;
            int width = 38;
            int length = 64;
            int height = 64;
            int payment = -1;
            long parcel_number = -1;
            if(sSmall.isSelected()){
                height = 8;
            }else if(sMedium.isSelected()) {
                height = 19;
            }
            if(fFlat.isSelected() && tFlat.isSelected()){

                try {
                    payment = clientHolder.getClient().createPayment(16);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number = clientHolder.getClient().shipParcel(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, 16);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fLocker.isSelected() && tFlat.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(14.50);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number = clientHolder.getClient().shipParcelFromLocker(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, 14.50);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fFlat.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(13);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number =  clientHolder.getClient().shipParcelToLocker(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, 13);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fLocker.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(11.5);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number = clientHolder.getClient().shipParcelFromToLocker(weight, height, width, length, payment);
                   // clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, 11.50);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            alert.setTitle("Sending error");
            alert.setHeaderText("Check your data");
            alert.setContentText("All fields needs to be filled");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }
    public void shippedWarning(long parcel_number, double price){
        alert.setTitle("Shipped");
        alert.setHeaderText("Parcel has been shipped\nBe sure to transfer payment ont the account below");
        alert.setContentText("Price: "+price+"\nBank account for transfer 000100101101 \nYour parcel number: " +parcel_number);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
        try {
            choiceBoxParcel.getItems().addAll(clientHolder.getClient().getParcelsID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void pushShowChoose(ActionEvent event) {
        try {
            Parcel parcel = clientHolder.getClient().getParcelInfo(choiceBoxParcel.getValue());
            lLocalization.setText(parcel.getLocalization());
            lStatus.setText(parcel.getStatus());
            lPackageID.setText(String.valueOf(parcel.getParcelNumber()));
            lPayment.setText(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getStatus());

            Address address = clientHolder.getClient().getAddressInfo(parcel.getDelivery_address());
            lAddress.setText(String.valueOf(address.getStreet()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void pushShowEnter(ActionEvent event) {
        try {
            if(!tfpackagecode.getText().isEmpty()){
                Parcel parcel = null;
                parcel = clientHolder.getClient().getParcelInfo(Long.parseLong(tfpackagecode.getText()));
                lLocalization.setText(parcel.getLocalization());
                lStatus.setText(parcel.getStatus());
                lPackageID.setText(String.valueOf(parcel.getParcelNumber()));
                lPayment.setText(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getStatus());

                Address address = clientHolder.getClient().getAddressInfo(parcel.getDelivery_address());
                lAddress.setText(String.valueOf(address.getStreet()));
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    @FXML
    void savechanges(ActionEvent event) {
        try {
            if(clientHolder.getClient().update(tfName.getText(),tfSurname.getText(),tfPhone.getText(),tfEmail.getText(),tfAdress.getText(),
            tfCity.getText(),tfPostcode.getText())){

                alert.setTitle("Account update");
                alert.setHeaderText("Succes");
                alert.setContentText("Account has been updated");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }else{
                alert.setTitle("Account update");
                alert.setHeaderText("Failure");
                alert.setContentText("Account has not been updated");
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
    @FXML
    void savepassword(ActionEvent event) {
        try {
            if(tfCurrentPassword.getText().equals(clientHolder.getClient().getAccount().getPassword())){
                if(tfNewPassword.getText().equals(tfNewpassword.getText())) {
                    if(tfNewPassword.getText().length()<6) {
                        alert.setTitle("Password Update");
                        alert.setHeaderText("Failure");
                        alert.setContentText("New password needs to be 6 characters long");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                    }else if(clientHolder.getClient().updatePassword(tfNewPassword.getText())) {
                        alert.setTitle("Account update");
                        alert.setHeaderText("Succes");
                        alert.setContentText("Account has been updated");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                    } else {
                        alert.setTitle("Account update");
                        alert.setHeaderText("Failure");
                        alert.setContentText("Account has not been updated");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                    }
                } else {
                        alert.setTitle("Password Update");
                        alert.setHeaderText("Failure");
                        alert.setContentText("Passwords aren not the same");
                        alert.showAndWait().ifPresent(rs -> {
                            if (rs == ButtonType.OK) {
                                System.out.println("Pressed OK.");
                            }
                        });
                }
            }else{
                alert.setTitle("Password Update");
                alert.setHeaderText("Failure");
                alert.setContentText("Wrong current password");
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

    public void pushNextPage(ActionEvent event) {
        if(fFlat.isSelected()){
            FormPane.setVisible(false);
            ShipperFlatDetails.setVisible(true);
        }else {
            FormPane.setVisible(false);
            //ShipperLockerDetails.setVisible(true);
        }

    }
    public void pushPreviousPage(ActionEvent event) {
        FormPane.setVisible(true);
        ShipperFlatDetails.setVisible(false);
        //ShipperLockerDetails.setVisible(false);
    }

    public void pushPreviousPageSecond(ActionEvent event) {
        if(fFlat.isSelected()){
            RecipientFlatDetails.setVisible(false);
            //RecipientLockerDetails.setVisible(false);
            ShipperFlatDetails.setVisible(true);
        }else{
            RecipientFlatDetails.setVisible(false);
            //RecipientLockerDetails.setVisible(false);
            //ShipperLockerDetails.setVisible(true);
        }

    }
    public void pushNextPageSecond(ActionEvent event) {
        hideWarnings();
        int validations = 0;
        if(!DataValidation.validateEmail(tfEmailFrom)) {
            lWarningEmail.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateMobile(tfphonefrom)) {
            lWarningMobile.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validatePostcode(tfpostcodefrom)) {
            lWarningPostcode.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfnameandsurfrom)) {
            lWarningName.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfcityfrom)) {
            lWarningCity.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfstreetfrom)) {
            lWarningStreet.setVisible(true);
            validations ++;
        }

        if(validations == 0){
            if(tFlat.isSelected()){
                ShipperFlatDetails.setVisible(false);
                //ShipperLockerDetails.setVisible(false);
                RecipientFlatDetails.setVisible(true);
            }else{
                ShipperFlatDetails.setVisible(false);
                //ShipperLockerDetails.setVisible(false);
                //RecipientLockerDetails.setVisible(true);
            }
        } else {
            alert.setTitle("Some information's are invalid");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Informations");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }
    private void calculateCost() {
        price = 0;
        if (fLocker.isSelected()) {
            price += 5;
        } else {
            price += 7;
        }
        if (tLocker.isSelected()) {
            price += 5;
        } else {
            price += 7;
        }

        if (sSmall.isSelected()) {
            price += 2;
        } else if (sMedium.isSelected()) {
            price += 3;
        } else {
            price += 4;
        }

        fPrice.setText("$" + price);
    }
    private void hideWarnings(){
        lWarningEmail.setVisible(false);
        lWarningMobile.setVisible(false);
        lWarningPostcode.setVisible(false);
        lWarningCity.setVisible(false);
        lWarningStreet.setVisible(false);
        lWarningName.setVisible(false);
    }

}
