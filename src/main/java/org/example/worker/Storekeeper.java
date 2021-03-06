package org.example.worker;

import org.example.PostgreSQL.ManageDataBase;
import org.example.parcel.Status;

import java.sql.SQLException;
import java.util.Date;

public class Storekeeper extends Employee {
    ManageDataBase base =  new  ManageDataBase();
    Status status = new Status();

    public Storekeeper(int id, int pesel, int salary, int phoneNumber,String workerCode, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber,workerCode, name, surname, position, dateOfEmployment);
    }
    public Storekeeper() {}



    public void reportTheDamage(long numberParcel) {
        try {
            base.connectToDataBase();
            base.updateParcelStatus(status.getSa8(),numberParcel);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void acceptParcel(long parcelNumber)  {
        try {
            base.connectToDataBase();
            base.updateParcelStatus(status.getSa5(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
