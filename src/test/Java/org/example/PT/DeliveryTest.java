package org.example.PT;

import org.example.PG.Parcel;
import org.example.PG.ShipmentFactory;
import org.example.PostgreSQL.ManageDataBase;
import org.junit.Assert;
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

            Assert.assertNotNull("Obiekt jest pusty",  delivery);
            Assert.assertEquals("", delivery.getInfoParcel(1));


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