package org.example.PostgeSQLTest;

import org.example.PG.*;
import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.*;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ClientTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";
    @Test()
    public void login_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", 0,776857986, "myEmail@gmail.com",
                    123,"admin","admin");
            client.login("admin","admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            client.login("admin","admin");
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
    public void shipParcel_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", 0,776857986, "myEmail@gmail.com",
                    123,"admin","admin");
            client.login("admin","admin");
            testBase.createTableParcels();
            client.shipParcel(10,10,10,10,1,1,1,testBase);
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
