package org.example.PG;

import java.util.List;

public class Client {
    private Account account;
    private List<Parcel> parcels;

    public void addParcel(Parcel parcel){
        this.parcels.add(parcel);
    }
    public void createAccount(String login, String password, String email, String phoneNumber, String name, String surname){
        account = new Account(login, password, email, phoneNumber, name, surname);
    }
}
