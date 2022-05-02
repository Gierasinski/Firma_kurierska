package org.example.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.PostgreSQL.ManageDataBase;
import org.example.global.Address;

import java.sql.SQLException;

public class Locker {
    private int addressID;
    private Address address;
    private  final SimpleIntegerProperty id;
    private  final SimpleStringProperty code;
    private  final SimpleStringProperty description;
    private  final SimpleStringProperty city;
    private  final SimpleStringProperty postCode;
    private  final SimpleStringProperty street;
    private  final SimpleStringProperty number;
    public Locker(int id, int addressID, String code, String description) throws SQLException {
        this.id = new SimpleIntegerProperty(id);
        this.addressID = addressID;
        this.code = new SimpleStringProperty(code);
        this.description = new SimpleStringProperty(description);
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();
        address = base.getAddressInfo(addressID);

        this.city = new SimpleStringProperty(address.getCity());
        this.postCode = new SimpleStringProperty(address.getPostcode());
        this.street = new SimpleStringProperty(address.getStreet());
        this.number = new SimpleStringProperty(address.getNumber());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public String getPostCode() {
        return postCode.get();
    }

    public SimpleStringProperty postCodeProperty() {
        return postCode;
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public int getAddressID() {
        return addressID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setNumber(String number) {
        this.number.set(number);
    }
}
