package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;
import org.example.client.Client;
import org.example.worker.Accountant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class AccountantTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";
    Accountant accountant = new Accountant();

    @Test()
    public void getFactureTest() {
        try {
        try {
            Client client = new Client();
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.createTableAdres();
            testBase.createTablePayment();
            testBase.insertClient("Adam","Nowak", "110110110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            testBase.createTableParcels();
            client.createPayment(10);
            long parcelid = client.shipParcel(10,10,10,10,4,testBase);
            Assertions.assertEquals(true,accountant.getFacture(parcelid, testBase));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        }

            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test()
    public void generateWorkerCodeNumberTest() {
        Assertions.assertTrue(accountant.generateWorkerCodeNumber()>0);
        Assertions.assertNotEquals(1,accountant.generateWorkerCodeNumber());
        Assertions.assertNotEquals(100,accountant.generateWorkerCodeNumber());
        Assertions.assertNotEquals(1000,accountant.generateWorkerCodeNumber());
    }



}