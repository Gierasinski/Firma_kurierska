package org.example.PG;

public class Account {
    private String login, password, email, phoneNumber, name, surname;
    private long id;
    boolean isNull;
    Address address;

    public Account(long id,String login, String password, String email, String phoneNumber, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.isNull = false;
    }
    public Account() {this.isNull = true;}

    public void addAddress(String voivodeship, String city, String street, int buildingNumber){
        //address = new Address(voivodeship, city, street, buildingNumber);
    }
    public boolean getIsNull(){return isNull;}

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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
