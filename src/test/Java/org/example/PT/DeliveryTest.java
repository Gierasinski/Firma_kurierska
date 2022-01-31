package org.example.PT;

import org.example.parcel.Parcel;
import org.example.parcel.ShipmentFactory;
import org.example.PostgreSQL.ManageDataBase;
import org.example.worker.Delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DeliveryTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";

    Delivery delivery = new Delivery();

    @Test()
    public void deliveryChangedStatusTest() {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            ShipmentFactory factory = new ShipmentFactory();
            Parcel myParcel = factory.createParcel(1,1,10.f,20,20,
                    20,1,1,1,"Shipped", "Sender");
            delivery.pickUpTheParcel(1);
            delivery.deliveredToParcelLocker(1);
            delivery.deliveredToParcelLocker(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Test()
    public void getInfoParcelTest() {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            ShipmentFactory factory = new ShipmentFactory();
            Parcel myParcel = factory.createParcel(1,1,10.f,20,20,
                    20,1,1,1,"Shipped", "Sender");

            Assertions.assertNotNull(delivery);
            Assertions.assertEquals("", delivery.getInfoParcel(1));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}