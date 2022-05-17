package org.example.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class RouteL {
    private  long  parcelN;
    private  final SimpleLongProperty parcelNumber;
    private  final SimpleStringProperty clientName;
    private  final SimpleStringProperty clientSurname;
    private  final SimpleIntegerProperty clientPhone;
    private  final SimpleIntegerProperty stage;
    private  final SimpleIntegerProperty code;
    private  final SimpleStringProperty city;
    private  final SimpleStringProperty postCode;
    private  final SimpleStringProperty street;
    private  final SimpleStringProperty number;
    private  final SimpleStringProperty local;
    private  final SimpleStringProperty address;

    public RouteL(long parcelNumber, String clientName, String clientSurname, int clientPhone,
                  int stage, int code, String city, String postCode,
                  String street, String number, String local) {
        this.parcelN = parcelNumber;
        this.parcelNumber = new SimpleLongProperty(parcelNumber);
        this.clientName = new SimpleStringProperty(clientName);
        this.clientSurname = new SimpleStringProperty(clientSurname);
        this.clientPhone = new SimpleIntegerProperty(clientPhone);
        this.stage = new SimpleIntegerProperty(stage);
        this.code = new SimpleIntegerProperty(code);
        this.city = new SimpleStringProperty(city);
        this.postCode = new SimpleStringProperty(postCode);
        this.street = new SimpleStringProperty(street);
        this.number = new SimpleStringProperty(number);
        this.local = new SimpleStringProperty(local);
        if (String.valueOf(local) == "null") {
            local = "";
        }
        this.address = new SimpleStringProperty(city+" " +postCode +", " + street +" " + number +"/" +local);
    }

    public long getParcelN() {
        return parcelN;
    }

    public long getParcelNumber() {
        return parcelNumber.get();
    }

    public SimpleLongProperty parcelNumberProperty() {
        return parcelNumber;
    }

    public String getClientName() {
        return clientName.get();
    }

    public SimpleStringProperty clientNameProperty() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname.get();
    }

    public SimpleStringProperty clientSurnameProperty() {
        return clientSurname;
    }

    public int getClientPhone() {
        return clientPhone.get();
    }

    public SimpleIntegerProperty clientPhoneProperty() {
        return clientPhone;
    }

    public int getStage() {
        return stage.get();
    }

    public SimpleIntegerProperty stageProperty() {
        return stage;
    }

    public int getCode() {
        return code.get();
    }

    public SimpleIntegerProperty codeProperty() {
        return code;
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

    public String getLocal() {
        return local.get();
    }

    public SimpleStringProperty localProperty() {
        return local;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }
}
