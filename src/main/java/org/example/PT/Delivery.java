package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;

public class Delivery extends Employee{
    ManageDataBase base =  new  ManageDataBase();
    Status status;

    public Delivery(int id, int pesel, int salary, int phoneNumber, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber, name, surname, position, dateOfEmployment);
    }

    public void pickUpTheParcel(int parcelNumber){
        try {
            base.updateParcelStatus(status.getSa1(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deliveredToTheRecipient(int parcelNumber){
        try {
            base.updateParcelStatus(status.getSa7(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pobierzOplate(){

    }
}
