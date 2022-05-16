package org.example.gui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.PostgreSQL.ManageDataBase;
import org.example.worker.Accountant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountantFX implements Initializable {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Accountant accountant = new Accountant();

    @FXML
    private Button btnAddWorker;


    @FXML
    private Button btnIssue;

    @FXML
    private Button btnexit;

    @FXML
    private Button btnpayout;

    @FXML
    private TextField tfWorkerNumber;


    @FXML
    private TextField tfICity;

    @FXML
    private TextField tfIHousenumber;

    @FXML
    private TextField tfIRoad;

    @FXML
    private TextField tfIamount;

    @FXML
    private TextField tfIname;

    @FXML
    private TextField tfSearch;

    @FXML
    private TextField tfWbranchname;

    @FXML
    private TextField tfWcity;

    @FXML
    private TextField tfWdate;

    @FXML
    private TextField tfWhousenumber;

    @FXML
    private TextField tfWname;

    @FXML
    private TextField tfWposition;

    @FXML
    private TextField tfWroad;

    @FXML
    private TextField tfWsalary;

    @FXML
    private TextField tfWsurname;

    @FXML
    private TextField tfamount;

    @FXML
    private TextField tfpesel;
    @FXML
    private TableView tvParcelList;
    @FXML private TableColumn<ParcelL, String> parcelID;
    @FXML private TableColumn<ParcelL, String> clientID;
    @FXML private TableColumn<ParcelL, String> paymentStatus;
    @FXML private TableColumn<ParcelL, String> status;
    @FXML private TableColumn<ParcelL, String> price;
    private ObservableList<ParcelL> parcelsList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        //load lockers FROM
        parcelID.setCellValueFactory(new PropertyValueFactory<>("parcelNumber"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("client"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        getParcelsToTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void getParcelsToTable() throws SQLException {
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();

        parcelsList = base.getParcels();

        FilteredList<ParcelL> filteredParcels = new FilteredList<>(parcelsList, b ->true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredParcels.setPredicate(parcel -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(parcel.getParcelNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (parcel.getPaymentStatus().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (String.valueOf(parcel.getPrice()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (String.valueOf(parcel.getClient()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (parcel.getStatus().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ParcelL> sortedParcels = new SortedList<>(filteredParcels);
        sortedParcels.comparatorProperty().bind(tvParcelList.comparatorProperty());
        tvParcelList.setItems(sortedParcels);
    }
    @FXML
    void AddWorker(ActionEvent event) {
        if(!tfWname.getText().isEmpty() && !tfWsurname.getText().isEmpty() && !tfWbranchname.getText().isEmpty()
        && !tfWcity.getText().isEmpty() && !tfWsalary.getText().isEmpty() && !tfWroad.getText().isEmpty()
        && !tfWposition.getText().isEmpty() && !tfWhousenumber.getText().isEmpty()
        && !tfWdate.getText().isEmpty() && !tfpesel.getText().isEmpty()){
            accountant.addEmployee(tfWname.getText(),tfWsurname.getText(),tfWcity.getText(),tfWroad.getText(),tfWhousenumber.getText(),Integer.parseInt(tfWhousenumber.getText()),Integer.parseInt(tfpesel.getText()),tfWposition.getText(), Integer.parseInt(tfWsalary.getText()),null);
            alert.setTitle("Added");
            alert.setHeaderText("Delivery is add");
            alert.setContentText("Press OK");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }else{
            alert.setTitle("Error");
            alert.setHeaderText("Delivery is not Add");
            alert.setContentText("Check data");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    @FXML
    void Issue(ActionEvent event) throws FileNotFoundException, SQLException {
            if(tvParcelList.getSelectionModel().getSelectedItem() != null) {


                ParcelL parcelL = (ParcelL) tvParcelList.getSelectionModel().getSelectedItem();
                accountant.getFacture(parcelL.getParcel());

                getParcelsToTable();

                alert.setTitle("Issue");
                alert.setHeaderText("An invoice has been issued");
                alert.setContentText("Press OK");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }

    }
    @FXML
    void PayForParcel(ActionEvent event) throws FileNotFoundException, SQLException {
        if(tvParcelList.getSelectionModel().getSelectedItem() != null) {

            ParcelL parcelL = (ParcelL) tvParcelList.getSelectionModel().getSelectedItem();
            accountant.bookPayment(parcelL.getParcel());

            getParcelsToTable();

            alert.setTitle("Payment");
            alert.setHeaderText("Payment status changed");
            alert.setContentText("Press OK");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }


    @FXML
    void SwitchToWorkerPanel(ActionEvent event)throws IOException {
        App.setRoot("WorkerPanel");
    }
    @FXML
    void assignCouriers() throws SQLException {
        accountant.assignCouriers();

        alert.setTitle("Assign Couriers");
        alert.setHeaderText("Couriers have been assigned");
        alert.setContentText("Press OK");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    @FXML
    void payout(ActionEvent event) {
        if(!tfamount.getText().isEmpty() && !tfWorkerNumber.getText().isEmpty() ){
            accountant.givePremium(tfWorkerNumber.getText(),Integer.parseInt(tfamount.getText()));
            alert.setTitle("Premium");
            alert.setHeaderText("Premium is add");
            alert.setContentText("Press OK");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }else{
            alert.setTitle("Premium");
            alert.setHeaderText("Premium is not add");
            alert.setContentText("Check data");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }

}
