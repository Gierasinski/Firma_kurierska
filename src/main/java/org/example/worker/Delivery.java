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

    public void pickUpTheParcel(long parcelNumber){
        try {
            base.connectToDataBase();
            base.updateParcelStatus(status.getSa1(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deliveredToTheRecipient(long parcelNumber){
        try {
            base.connectToDataBase();
            base.updateParcelStatus(status.getSa7(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deliveredToParcelLocker(long parcelNumber){
        try {
            base.connectToDataBase();
            base.updateParcelStatus(status.getSa6(),parcelNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getInfoParcel(long parcelNumber) {
        try {
            base.connectToDataBase();
            Parcel parcel;
            Address addressDelivery;
            Address addressShipment;
            parcel = base.getParcelInfoNumberCode(parcelNumber);
            addressDelivery = base.getAddressInfo(parcel.getDelivery_address());
            addressShipment = base.getAddressInfo(parcel.getShipment_address());
            if(parcel.getParcelNumber() != 0){
                return "NUMBER\n" + parcel.getParcelNumber() +"\nSTATUS\n"
                        + parcel.getStatus() +"\nDELIVERY ADDRESS\n" + addressDelivery.getCity() +"\n"
                        + addressDelivery.getPostcode() +"\n" + addressDelivery.getStreet() +"\nSHIPMENT ADDRESS\n" + addressShipment.getCity() +"\n"
                        + addressShipment.getPostcode() +"\n" + addressShipment.getStreet() +"\n" ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

}
