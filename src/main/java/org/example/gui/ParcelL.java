package org.example.gui;

import javafx.beans.property.*;
import org.example.PostgreSQL.ManageDataBase;
import org.example.client.ShipperInfo;
import org.example.global.Address;
import org.example.parcel.Payment;

import java.sql.SQLException;

public class ParcelL {
    private  long  parcelN;
    private  final SimpleLongProperty parcelNumber;
    private  final SimpleStringProperty paymentStatus;
    private  final SimpleDoubleProperty price;
    private  final SimpleIntegerProperty client;
    private  final SimpleStringProperty status;

    public ParcelL(long parcelNumber, String status, int payment, int clientID) throws SQLException {
        this.parcelNumber = new SimpleLongProperty(parcelNumber);
        this.status = new SimpleStringProperty(status);
        this.client = new SimpleIntegerProperty(clientID);
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();
        Payment p = base.getPaymentInfo(payment);
        price = new SimpleDoubleProperty(p.getPrice());
        paymentStatus = new SimpleStringProperty(p.getStatus());

        parcelN = parcelNumber;

    }

    public void setParcelNumber(long parcelNumber) {
        this.parcelNumber.set(parcelNumber);
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus.set(paymentStatus);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setClient(int client) {
        this.client.set(client);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public long getParcelNumber() {
        return parcelNumber.get();
    }

    public SimpleLongProperty parcelNumberProperty() {
        return parcelNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus.get();
    }

    public SimpleStringProperty paymentStatusProperty() {
        return paymentStatus;
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public int getClient() {
        return client.get();
    }

    public SimpleIntegerProperty clientProperty() {
        return client;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public long getParcel() {
        return parcelN;
    }
}