package org.example.gui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.example.App;
import org.example.PostgreSQL.ManageDataBase;
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
    private Tab TabProfile, TabSendingPackage, TabTrackingYourPackage;
    @FXML
    private Pane FormPane, ShipperFlatDetails, ShipperLockerDetails, RecipientFlatDetails, RecipientLockerDetails;

    @FXML
    private Button btnOK, btnSend, btnLogout, btnSaveChanges, btnSavePassword;
    @FXML
    private Button btnNextPage, btnNextPageFF, btnPreviousPageFF, btnNextPageTF, btnPreviousPageTF;

    @FXML
    private RadioButton fFlat,fLocker,tFlat,tLocker,sSmall,sMedium,sBig;

    @FXML
    private PasswordField tfCurrentPassword;

    @FXML
    private TextField tfStreet, tfCity, tfPostcode, tfNumber, tfLocal;
    @FXML
    private TextField tfDateOfBirth;
    @FXML
    private TextField tfEmail, tfName, tfNewPassword, tfChangePassword;
    @FXML
    private TextField tfPhone, tfSurname, tfPackageCode;
    @FXML
    private TextField tfEmailFromF, tfAddressFromF, tfCityFromF, tfNameAndSurFromF, tfPhoneFromF, tfPostcodeFromF,
            tfStreetFromF, tfNumberFromF, tfLocalFromF;
    //From Locker
    @FXML
    private TextField tfEmailFromL, tfSearchFromL,  tfNameAndSurFromL, tfPhoneFromL;
    @FXML
    private TableView tvLockerListF;
    @FXML private TableColumn<Locker, String> codeF;
    @FXML private TableColumn<Locker, String> cityF;
    @FXML private TableColumn<Locker, String> postCodeF;
    @FXML private TableColumn<Locker, String> streetF;
    @FXML private TableColumn<Locker, String> numberF;
    private ObservableList<Locker> lockerListF;
    @FXML
    private TextField tfEmailToF, tfAddressToF, tfCityToF, tfNameAndSurToF, tfPhoneToF, tfPostcodeToF,
            tfStreetToF, tfNumberToF, tfLocalToF;
    @FXML
    private TextField tfEmailToL, tfSearchToL,  tfNameAndSurToL, tfPhoneToL;
    @FXML
    private TableView tvLockerListT;
    @FXML private TableColumn<Locker, String> codeT;
    @FXML private TableColumn<Locker, String> cityT;
    @FXML private TableColumn<Locker, String> postCodeT;
    @FXML private TableColumn<Locker, String> streetT;
    @FXML private TableColumn<Locker, String> numberT;
    private ObservableList<Locker> lockerListT;
    @FXML
    private Label lPrice, lAddressDescription, tAddressDescription;
    @FXML
    private Label lLocalization,lStatus,lPackageID,lPayment,lAddress,lLoginLabel;
    @FXML
    private Label lWarningEmailFF, lWarningMobileFF, lWarningPostcodeFF, lWarningNameFF, lWarningCityFF, lWarningStreetFF;
    @FXML
    private Label lWarningEmailFL, lWarningMobileFL, lWarningNameFL, lWarningLockerFL;
    @FXML
    private Label lWarningEmailTF, lWarningMobileTF, lWarningPostcodeTF, lWarningNameTF, lWarningCityTF, lWarningStreetTF;
    @FXML
    private Label lWarningEmailTL, lWarningMobileTL, lWarningNameTL, lWarningLockerTL;
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
                tfStreet.setText(address.getStreet());
                tfNumber.setText(address.getNumber());
                tfLocal.setText(address.getLocal());
                tfCity.setText(address.getCity());
                tfPostcode.setText(address.getPostcode());
            }
            tfPhone.setText(account.getPhoneNumber());
            tfEmail.setText(account.getEmail());

            //set shipper data on load
            setUserShipmentData();

            //load lockers FROM
            codeF.setCellValueFactory(new PropertyValueFactory<>("code"));
            cityF.setCellValueFactory(new PropertyValueFactory<>("city"));
            postCodeF.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            streetF.setCellValueFactory(new PropertyValueFactory<>("street"));
            numberF.setCellValueFactory(new PropertyValueFactory<>("number"));

            ManageDataBase base = new ManageDataBase();
            base.connectToDataBase();
            lockerListF = base.getLockers();

            FilteredList<Locker> filteredLockers = new FilteredList<>(lockerListF, b ->true);
            tfSearchFromL.textProperty().addListener((observable, oldValue, newValue)-> {
                filteredLockers.setPredicate(locker -> {
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(locker.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (locker.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getPostCode().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getStreet().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getNumber().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else
                        return false;
                });
            });
            SortedList<Locker> sortedLockers = new SortedList<>(filteredLockers);
            sortedLockers.comparatorProperty().bind(tvLockerListF.comparatorProperty());
            tvLockerListF.setItems(sortedLockers);

            //load lockers To
            codeT.setCellValueFactory(new PropertyValueFactory<>("code"));
            cityT.setCellValueFactory(new PropertyValueFactory<>("city"));
            postCodeT.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            streetT.setCellValueFactory(new PropertyValueFactory<>("street"));
            numberT.setCellValueFactory(new PropertyValueFactory<>("number"));

            lockerListT = base.getLockers();

            FilteredList<Locker> filteredLockersT = new FilteredList<>(lockerListT, b ->true);
            tfSearchToL.textProperty().addListener((observable, oldValue, newValue)-> {
                filteredLockersT.setPredicate(locker -> {
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(locker.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (locker.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getPostCode().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getStreet().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (locker.getNumber().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else
                        return false;
                });
            });
            SortedList<Locker> sortedLockersT = new SortedList<>(filteredLockersT);
            sortedLockersT.comparatorProperty().bind(tvLockerListT.comparatorProperty());
            tvLockerListT.setItems(sortedLockersT);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setUserShipmentData(){
        Account account = clientHolder.getClient().getAccount();
        Address address = account.getAddress();

        tfNameAndSurFromF.setText(account.getName() + " " + account.getSurname());
        tfEmailFromF.setText(account.getEmail());
        tfPhoneFromF.setText(account.getPhoneNumber());

        tfNameAndSurFromL.setText(account.getName() + " " + account.getSurname());
        tfEmailFromL.setText(account.getEmail());
        tfPhoneFromL.setText(account.getPhoneNumber());

        tfPostcodeFromF.setText(address.getPostcode());
        tfCityFromF.setText(address.getCity());
        tfStreetFromF.setText(address.getStreet());
        tfNumberFromF.setText(address.getNumber());
        tfLocalFromF.setText(address.getLocal());
    }
    @FXML
    void logout(ActionEvent event)throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void changeFromFunction(ActionEvent event) {
        calculateCost();
    }
    public void resetText(){
         tfNameAndSurFromF.setText("");
         tfPostcodeFromF.setText("");
         tfCityFromF.setText("");
         tfStreetFromF.setText("");
         tfNumberFromF.setText("");
         tfLocalFromF.setText("");
         tfEmailFromF.setText("");
         tfPhoneFromF.setText("");

         tfNameAndSurToF.setText("");
         tfPostcodeToF.setText("");
         tfCityToF.setText("");
         tfStreetToF.setText("");
         tfNumberToF.setText("");
         tfLocalToF.setText("");
         tfEmailToF.setText("");
         tfPhoneToF.setText("");

         setUserShipmentData();
        try {
            choiceBoxParcel.getItems().removeAll(choiceBoxParcel.getItems());
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
    void pushSendButton() {
            float weight = 25;
            int width = 38;
            int length = 64;
            int height = 41;
            int payment = -1;
            long parcel_number = -1;
            if(sSmall.isSelected()){
                height = 8;
            }else if(sMedium.isSelected()) {
                height = 19;
            }
            if(fFlat.isSelected() && tFlat.isSelected()){

                try {
                    payment = clientHolder.getClient().createPayment(price);
                    clientHolder.getClient().setOriginAddress(tfCityFromF.getText(), tfStreetFromF.getText(), tfNumberFromF.getText(), tfLocalFromF.getText(),tfPostcodeFromF.getText(),tfNameAndSurFromF.getText());
                    clientHolder.getClient().setDestinationAddress(tfCityToF.getText(), tfStreetToF.getText(),tfNumberToF.getText(), tfLocalToF.getText(), tfPostcodeToF.getText(),tfNameAndSurToF.getText());

                    parcel_number = clientHolder.getClient().shipParcel(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fLocker.isSelected() && tFlat.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(price);
                    Locker locker = (Locker) tvLockerListF.getSelectionModel().getSelectedItem();
                    clientHolder.getClient().setOriginAddress(locker.getAddress());
                    clientHolder.getClient().setDestinationAddress(tfCityToF.getText(), tfStreetToF.getText(), tfNumberToF.getText(), tfLocalToF.getText(), tfPostcodeToF.getText(),tfNameAndSurToF.getText());

                    parcel_number = clientHolder.getClient().shipParcelFromLocker(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fFlat.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(price);
                    clientHolder.getClient().setOriginAddress(tfCityFromF.getText(), tfStreetFromF.getText(), tfNumberFromF.getText(), tfLocalFromF.getText(), tfPostcodeFromF.getText(), tfNameAndSurFromF.getText());
                    Locker locker = (Locker) tvLockerListT.getSelectionModel().getSelectedItem();
                    clientHolder.getClient().setDestinationAddress(locker.getAddress());

                    parcel_number =  clientHolder.getClient().shipParcelToLocker(weight, height, width, length, payment);
                    //clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else  if(fLocker.isSelected() && tLocker.isSelected()){
                try {
                    payment = clientHolder.getClient().createPayment(price);
                    Locker lockerF = (Locker) tvLockerListF.getSelectionModel().getSelectedItem();
                    clientHolder.getClient().setOriginAddress(lockerF.getAddress());
                    Locker lockerT = (Locker) tvLockerListT.getSelectionModel().getSelectedItem();
                    clientHolder.getClient().setDestinationAddress(lockerT.getAddress());

                    parcel_number = clientHolder.getClient().shipParcelFromToLocker(weight, height, width, length, payment);
                   // clientHolder.getClient().setRoute(parcel_number);
                    resetText();
                    shippedWarning(parcel_number, price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
            choiceBoxParcel.getItems().removeAll(choiceBoxParcel.getItems());
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

            lAddress.setText(String.valueOf(address.getPostcode() +" " + address.getCity() +" "+address.getStreet() + " "+ address.getNumber()+"/"+address.getLocal()));

            if(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getStatus().equals("Not Paid")){
                paymentInfo(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void pushShowEnter(ActionEvent event) {
        try {
            if(!tfPackageCode.getText().isEmpty()){
                Parcel parcel = null;
                parcel = clientHolder.getClient().getParcelInfo(Long.parseLong(tfPackageCode.getText()));
                lLocalization.setText(parcel.getLocalization());
                lStatus.setText(parcel.getStatus());
                lPackageID.setText(String.valueOf(parcel.getParcelNumber()));
                lPayment.setText(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getStatus());

                Address address = clientHolder.getClient().getAddressInfo(parcel.getDelivery_address());
                lAddress.setText(String.valueOf(address.getStreet()));
                if(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getStatus().equals("Not Paid")){
                    paymentInfo(clientHolder.getClient().getPaymentInfo(parcel.getPayment()).getPrice());
                }
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    @FXML
    void saveChanges(ActionEvent event) {
        try {
            if(clientHolder.getClient().update(tfName.getText(),tfSurname.getText(),tfPhone.getText(),tfEmail.getText(), tfStreet.getText(),
            tfNumber.getText(), tfLocal.getText(),tfCity.getText(),tfPostcode.getText())){

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
    void savePassword(ActionEvent event) {
        try {
            if(tfCurrentPassword.getText().equals(clientHolder.getClient().getAccount().getPassword())){
                if(tfNewPassword.getText().equals(tfChangePassword.getText())) {
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
            ShipperLockerDetails.setVisible(true);
        }

    }
    public void pushPreviousPageFF(ActionEvent event) {
        FormPane.setVisible(true);
        ShipperFlatDetails.setVisible(false);
        ShipperLockerDetails.setVisible(false);
    }

    public void pushPreviousPageTF(ActionEvent event) {
        if(fFlat.isSelected()){
            RecipientFlatDetails.setVisible(false);
            RecipientLockerDetails.setVisible(false);
            ShipperFlatDetails.setVisible(true);
        }else{
            RecipientFlatDetails.setVisible(false);
            RecipientLockerDetails.setVisible(false);
            ShipperLockerDetails.setVisible(true);
        }

    }
    public void pushNextPageFF(ActionEvent event) {
        hideWarnings();
        int validations = 0;
        if(!DataValidation.validateEmail(tfEmailFromF)) {
            lWarningEmailFF.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateMobile(tfPhoneFromF)) {
            lWarningMobileFF.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validatePostcode(tfPostcodeFromF)) {
            lWarningPostcodeFF.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfNameAndSurFromF)) {
            lWarningNameFF.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfCityFromF)) {
            lWarningCityFF.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfStreetFromF)) {
            lWarningStreetFF.setVisible(true);
            validations ++;
        }

        if(validations == 0){
            if(tFlat.isSelected()){
                ShipperFlatDetails.setVisible(false);
                ShipperLockerDetails.setVisible(false);
                RecipientFlatDetails.setVisible(true);
            }else{
                ShipperFlatDetails.setVisible(false);
                ShipperLockerDetails.setVisible(false);
                RecipientLockerDetails.setVisible(true);
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
    public void pushNextPageFL(ActionEvent event) {
        hideWarnings();
        int validations = 0;
        if(!DataValidation.validateEmail(tfEmailFromL)) {
            lWarningEmailFL.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateMobile(tfPhoneFromL)) {
            lWarningMobileFL.setVisible(true);
            validations ++;
        }
        if(!DataValidation.validateLazy(tfNameAndSurFromL)) {
            lWarningNameFL.setVisible(true);
            validations ++;
        }
        if(tvLockerListF.getSelectionModel().getSelectedItem() == null) {
            lWarningLockerFL.setVisible(true);
            validations ++;
        }
        if(validations == 0){
            if(tFlat.isSelected()){
                ShipperFlatDetails.setVisible(false);
                ShipperLockerDetails.setVisible(false);
                RecipientFlatDetails.setVisible(true);
            }else{
                ShipperFlatDetails.setVisible(false);
                ShipperLockerDetails.setVisible(false);
                RecipientLockerDetails.setVisible(true);
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
    public void checkSendTF(ActionEvent event) {
        hideWarnings();
        int validations = 0;
        if(!tLocker.isSelected()) {
            if(!DataValidation.validateEmail(tfEmailToF)) {
                lWarningEmailTF.setVisible(true);
                validations ++;
            }
            if(!DataValidation.validateMobile(tfPhoneToF)) {
                lWarningMobileTF.setVisible(true);
                validations ++;
            }
            if(!DataValidation.validateLazy(tfNameAndSurToF)) {
                lWarningNameTF.setVisible(true);
                validations ++;
            }
            if (!DataValidation.validatePostcode(tfPostcodeToF)) {
                lWarningPostcodeTF.setVisible(true);
                validations++;
            }
            if (!DataValidation.validateLazy(tfCityToF)) {
                lWarningCityTF.setVisible(true);
                validations++;
            }
            if (!DataValidation.validateLazy(tfStreetToF)) {
                lWarningStreetTF.setVisible(true);
                validations++;
            }
        }else {
            if(!DataValidation.validateEmail(tfEmailToL)) {
                lWarningEmailTL.setVisible(true);
                validations ++;
            }
            if(!DataValidation.validateMobile(tfPhoneToL)) {
                lWarningMobileTL.setVisible(true);
                validations ++;
            }
            if(!DataValidation.validateLazy(tfNameAndSurToL)) {
                lWarningNameTL.setVisible(true);
                validations ++;
            }
            if(tvLockerListT.getSelectionModel().getSelectedItem() == null) {
                lWarningLockerTL.setVisible(true);
                validations ++;
            }
        }
        if(validations == 0){
            pushSendButton();
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

        lPrice.setText("$" + price);
    }
    private void hideWarnings(){
        lWarningEmailFF.setVisible(false);
        lWarningMobileFF.setVisible(false);
        lWarningPostcodeFF.setVisible(false);
        lWarningCityFF.setVisible(false);
        lWarningStreetFF.setVisible(false);
        lWarningNameFF.setVisible(false);

        lWarningEmailTF.setVisible(false);
        lWarningMobileTF.setVisible(false);
        lWarningPostcodeTF.setVisible(false);
        lWarningCityTF.setVisible(false);
        lWarningStreetTF.setVisible(false);
        lWarningNameTF.setVisible(false);

        lWarningEmailFL.setVisible(false);
        lWarningMobileFL.setVisible(false);
        lWarningLockerFL.setVisible(false);
        lWarningNameFL.setVisible(false);

        lWarningEmailTL.setVisible(false);
        lWarningMobileTL.setVisible(false);
        lWarningLockerTL.setVisible(false);
        lWarningNameTL.setVisible(false);
    }
    public void paymentInfo(double p) {
        alert.setTitle("Payment Information");
        alert.setHeaderText("To pay for your parcel shipment\nTransfer money to the account below\nEnter your package code as transfer title");
        alert.setContentText("Price: "+p+"\nBank account for transfer 000100101101");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }


}
