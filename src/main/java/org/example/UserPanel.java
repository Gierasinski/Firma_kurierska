package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.PG.Address;
import org.example.PG.ClientHolder;
import org.example.PG.Parcel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPanel implements Initializable {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static ClientHolder clientHolder = ClientHolder.getInstance();
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
    private RadioButton fFlat,fLocker,tFlat,tLocker,sSmall,sMedium,sBig;

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
    private Label fAddressDescription;

    @FXML
    private Label tAddressDescription;

    @FXML
    private Label lLocalization,lStatus,lPackageID,lPayment,lAddress;

    @FXML
    private ChoiceBox<Long> choiceBoxParcel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            choiceBoxParcel.getItems().addAll(clientHolder.getClient().getParcelsID());
            choiceBoxParcel.setOnAction(this::pushShowChoose);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logout(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }
    @FXML
    void savechanges(ActionEvent event) {
    }

    @FXML
    void savepassword(ActionEvent event) {

    }
    @FXML
    public void changeFromFunction(ActionEvent event) {
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
         tfemailadfrom.setText("");
         tfphonefrom.setText("");
         tfnameandsurto.setText("");
         tfadressto.setText("");
         tfpostcodeto.setText("");
         tfcityto.setText("");
         tfemailadto.setText("");
         tfphoneto.setText("");
    }
    @FXML
    public void changeToFunction(ActionEvent event) {
        if(tLocker.isSelected()) {
            tAddressDescription.setText("Locker name");
        }else if(tFlat.isSelected()){
            tAddressDescription.setText("House/Flat address");
        }
    }
    @FXML
    void pushSendButton(ActionEvent event) {
        if (!tfnameandsurfrom.getText().isEmpty() && !tfadressfrom.getText().isEmpty() && !tfpostcodefrom.getText().isEmpty()
                && !tfcityfrom.getText().isEmpty() && !tfemailadfrom.getText().isEmpty() && !tfphonefrom.getText().isEmpty() &&
                !tfnameandsurto.getText().isEmpty() && !tfadressto.getText().isEmpty() && !tfpostcodeto.getText().isEmpty()
                && !tfcityto.getText().isEmpty() && !tfemailadto.getText().isEmpty() && !tfphoneto.getText().isEmpty()) {

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
                    clientHolder.getClient().setRoute(parcel_number);
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
                    clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    alert.setTitle("Shipped");
                    alert.setHeaderText("Parcel has been shipped");
                    alert.setContentText("Parcel has been shipped");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fFlat.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(13);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number =  clientHolder.getClient().shipParcelToLocker(weight, height, width, length, payment);
                    clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    alert.setTitle("Shipped");
                    alert.setHeaderText("Parcel has been shipped");
                    alert.setContentText("Parcel has been shipped");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fLocker.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(11.5);
                    clientHolder.getClient().setOriginAddress(tfcityfrom.getText(), tfadressfrom.getText(), tfpostcodefrom.getText());
                    clientHolder.getClient().setDestinationAddress(tfcityto.getText(), tfadressto.getText(), tfpostcodeto.getText());

                    parcel_number = clientHolder.getClient().shipParcelFromToLocker(weight, height, width, length, payment);
                    clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    alert.setTitle("Shipped");
                    alert.setHeaderText("Parcel has been shipped");
                    alert.setContentText("Parcel has been shipped");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
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
        alert.setHeaderText("Parcel has been shipped\n Be sure to transfer payment ont the account below");
        alert.setContentText("Price: "+price+"\n Bank account for transfer 000100101101 \n Your parcel number:" +parcel_number);
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

    }



}
