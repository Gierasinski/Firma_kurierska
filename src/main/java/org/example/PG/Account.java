package org.example.PG;

public class Account {
    private String login, password, email, phoneNumber, name, surname;
    Address address;

    public Account(String login, String password, String email, String phoneNumber, String name, String surname) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
    }

    public void addAddress(String voivodeship, String city, String street, int buildingNumber){
        address = new Address(voivodeship, city, street, buildingNumber);
    }
}
