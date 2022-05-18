package org.example.gui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import org.example.App;
import org.example.PostgreSQL.ManageDataBase;
import org.example.worker.Delivery;
import org.example.worker.EmployeeHolder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DeliveryFX implements Initializable {
    Delivery delivery = new Delivery();
    @FXML
    private Button btn6;

    @FXML
    private Button btnexit;

    @FXML
    private Button btndeliveredtoapercellocker;

    @FXML
    private Button btndeliveredtorecipient;



    @FXML
    private TextArea taDelivery;

    @FXML
    private TextField tfSearchPickUp;
    @FXML
    private TextField tfSearchDeliver;

    @FXML
    private TableView tvPickUpList;
    @FXML private TableColumn<RouteL, String> parcelIDP;
    @FXML private TableColumn<RouteL, String> clientIDP;
    @FXML private TableColumn<RouteL, String> phoneP;
    @FXML private TableColumn<RouteL, String> addressP;
    private ObservableList<RouteL> pickUpList;

    @FXML
    private TableView tvDeliverList;
    @FXML private TableColumn<RouteL, String> parcelIDD;
    @FXML private TableColumn<RouteL, String> clientIDD;
    @FXML private TableColumn<RouteL, String> phoneD;
    @FXML private TableColumn<RouteL, String> addressD;
    @FXML private TableColumn<RouteL, String> codeD;
    private ObservableList<RouteL> deliverList;

    private static EmployeeHolder employeeHolder = EmployeeHolder.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            //load parcels to pick up
            parcelIDP.setCellValueFactory(new PropertyValueFactory<>("parcelNumber"));
            clientIDP.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            phoneP.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
            addressP.setCellValueFactory(new PropertyValueFactory<>("address"));

            //load parcels to deliver
            parcelIDD.setCellValueFactory(new PropertyValueFactory<>("parcelNumber"));
            clientIDD.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            phoneD.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
            addressD.setCellValueFactory(new PropertyValueFactory<>("address"));
            codeD.setCellValueFactory(new PropertyValueFactory<>("code"));

            getParcelsToPickUpList();
            getParcelsToDeliverList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void getParcelsToPickUpList() throws SQLException {
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();

        pickUpList = base.getCourierPickUpRoutes(employeeHolder.getEmployee().getEmployeeWorker().getWorkerCode());

        FilteredList<RouteL> filteredParcels = new FilteredList<>(pickUpList, b ->true);
        tfSearchPickUp.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredParcels.setPredicate(parcel -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(parcel.getParcelNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (parcel.getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (String.valueOf(parcel.getParcelNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (parcel.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else
                    return false;
            });
        });
        SortedList<RouteL> sortedParcels = new SortedList<>(filteredParcels);
        sortedParcels.comparatorProperty().bind(tvPickUpList.comparatorProperty());
        tvPickUpList.setItems(sortedParcels);
    }
    void getParcelsToDeliverList() throws SQLException {
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();

        deliverList = base.getCourierDeliverRoutes(employeeHolder.getEmployee().getEmployeeWorker().getWorkerCode());

        FilteredList<RouteL> filteredParcels = new FilteredList<>(deliverList, b ->true);
        tfSearchDeliver.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredParcels.setPredicate(parcel -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(parcel.getParcelNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (parcel.getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (String.valueOf(parcel.getParcelNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (parcel.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else
                    return false;
            });
        });
        SortedList<RouteL> sortedParcels = new SortedList<>(filteredParcels);
        sortedParcels.comparatorProperty().bind(tvDeliverList.comparatorProperty());
        tvDeliverList.setItems(sortedParcels);
    }

    @FXML
    void returnPackage(ActionEvent event) throws SQLException {

        RouteL routeL = (RouteL) tvDeliverList.getSelectionModel().getSelectedItem();
        delivery.returnParcel(routeL.getParcelNumber(), routeL.getStage());
        getParcelsToDeliverList();
        getParcelsToPickUpList();
    }
    @FXML
    void deliverPackage(ActionEvent event) throws SQLException {
        if(tvDeliverList.getSelectionModel().getSelectedItem() != null) {
            RouteL routeL = (RouteL) tvDeliverList.getSelectionModel().getSelectedItem();
            delivery.deliverParcel(routeL.getParcelNumber(), routeL.getStage());
            getParcelsToDeliverList();
            getParcelsToPickUpList();
        }
    }

    @FXML
    void pickUpPackage(ActionEvent event) throws SQLException {
        if(tvPickUpList.getSelectionModel().getSelectedItem() != null) {
            RouteL routeL = (RouteL) tvPickUpList.getSelectionModel().getSelectedItem();
            delivery.pickUpTheParcel(routeL.getParcelNumber(), routeL.getStage());
            getParcelsToPickUpList();
            getParcelsToDeliverList();
        }
    }


    @FXML
    void exit(ActionEvent event) throws IOException {
        App.setRoot("WorkerPanel");
    }

}
