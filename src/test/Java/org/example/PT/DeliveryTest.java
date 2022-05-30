package org.example.PT;

import org.checkerframework.checker.units.qual.A;
import org.example.client.Client;
import org.example.global.Address;
import org.example.parcel.Parcel;
import org.example.parcel.ShipmentFactory;
import org.example.PostgreSQL.ManageDataBase;
import org.example.worker.Accountant;
import org.example.worker.Delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

class DeliveryTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";

    Delivery delivery = new Delivery();
    Client client = new Client();
    Accountant accountant = new Accountant();

    @Test()
    public void deliveryTest() {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTables();
            testBase.insertClient("Adam","Nowak", "110110110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            client.createPayment(10);

            client.setOriginAddress(testBase.getAddressInfo(10));
            client.setDestinationAddress(testBase.getAddressInfo(11));
            long parcelId = client.shipParcelFromToLocker(10,10,10,10,4,testBase);
            accountant.assignCouriers();
            Assertions.assertEquals(true, delivery.pickUpTheParcel(parcelId, 0, testBase));
            Assertions.assertEquals(true, delivery.deliverParcel(parcelId, 0, testBase));


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
           // Assertions.assertEquals("", delivery.getInfoParcel(1));


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