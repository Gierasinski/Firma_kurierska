package org.example.client;

import org.example.global.Address;

public class ShipperInfo {
    private String  email, phoneNumber, name, surname;
    private long id;
    Address address;
    public ShipperInfo() {
    }
    public ShipperInfo(long id, String email, String phoneNumber, String name, String surname) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
    }

    public void addAddress(String voivodeship, String city, String street, int buildingNumber){
        //address = new Address(voivodeship, city, street, buildingNumber);
    }

    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
