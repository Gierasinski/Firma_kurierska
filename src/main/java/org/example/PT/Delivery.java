package org.example.PT;

import java.util.Date;

public class Delivery extends Employee{
    Status status;

    public Delivery(int id, int pesel, int salary, int phoneNumber, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber, name, surname, position, dateOfEmployment);
    }

    public void pickUpTheParcel(){
        status.getSa1();

    }

    public void deliveredToTheRecipient(){
        status.getSa7();
        status.getSa6();

    }

    public void pobierzOplate(){

    }
}
