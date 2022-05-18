package org.example.worker;

import org.example.global.Address;
import org.example.parcel.Parcel;
import org.example.PostgreSQL.ManageDataBase;
import org.example.parcel.Status;

import java.sql.SQLException;
import java.util.Date;

public class Delivery extends Employee{
    ManageDataBase base =  new  ManageDataBase();
    Status status = new Status();

    public Delivery(int id, int pesel, int salary, int phoneNumber,String workerCode, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber,workerCode, name, surname, position, dateOfEmployment);
    }
    public Delivery() {}

    public void pickUpTheParcel(long parcelNumber, int stage){
        try {
            base.connectToDataBase();
            base.pickUpParcel(parcelNumber, stage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deliverParcel(long parcelNumber, int stage){
        try {
            base.connectToDataBase();
            base.deliverParcel(parcelNumber, stage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void returnParcel(long parcelNumber, int stage){
        try {
            base.connectToDataBase();
            base.returnParcel(parcelNumber, stage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
