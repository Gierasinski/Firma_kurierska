package org.example.PostgeSQLTest;

import org.example.PostgreSQL.ManageDataBase;
import org.example.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
            testBase.createTableAdres();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);

            Assertions.assertNotNull(client);
            Assertions.assertEquals(true, client.login("admin","admin",testBase));
            Assertions.assertEquals(false, client.login("nieadmin","admin",testBase));
            Assertions.assertDoesNotThrow(() -> client.login("admin","admin",testBase));

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
            testBase.createTableAdres();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            testBase.createTableParcels();
            client.shipParcel(10,10,10,10,1,testBase);
            Assertions.assertNotEquals(1111111111, client.shipParcel(10,10,10,10,2,testBase));
            Assertions.assertDoesNotThrow(() -> client.shipParcel(10,10,10,10,3,testBase));
            Assertions.assertThrows(Exception.class,() -> client.shipParcel(10,10,10,10,3,testBase));
            Assertions.assertTrue(client.shipParcel(10,10,10,10,4,testBase)>0);

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
    public void shipParcelToLocker_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.createTableAdres();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            testBase.createTableParcels();
            client.shipParcelToLocker(10,10,10,10,20,testBase);
            Assertions.assertNotEquals(1111111111, client.shipParcelToLocker(10,10,10,10,22,testBase));
            Assertions.assertDoesNotThrow(() -> client.shipParcelToLocker(10,10,10,10,23,testBase));
            Assertions.assertThrows(Exception.class,() -> client.shipParcelToLocker(10,10,10,10,23,testBase));
            Assertions.assertTrue(client.shipParcelToLocker(10,10,10,10,24,testBase)>0);

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
    public void shipParcelFromLocker_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.createTableAdres();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            testBase.createTableParcels();
            client.shipParcelFromLocker(10,10,10,10,15,testBase);
            Assertions.assertNotEquals(1111111111, client.shipParcelFromLocker(10,10,10,10,13,testBase));
            Assertions.assertDoesNotThrow(() -> client.shipParcelFromLocker(10,10,10,10,14,testBase));
            Assertions.assertThrows(Exception.class,() -> client.shipParcelFromLocker(10,10,10,10,14,testBase));
            Assertions.assertTrue(client.shipParcelFromLocker(10,10,10,10,16,testBase)>0);



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
    public void createAddresses_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableAdres();
            client.setOriginAddress("Kielce", "Nowowiejska 11/115","25-532",testBase);
            System.out.println("Client Origin address added, origin:"+client.getOriginAddress());
            client.setDestinationAddress("Krakow", "Długa 34/1","31-146",testBase);
            System.out.println("Client Origin address added, origin:"+client.getDestinationAddress());

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
    public void register_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();

            Assertions.assertEquals(false, client.register("Kuba","Bialy","1111","asd@asd.pl","kuba","Kuba"));
            Assertions.assertDoesNotThrow(() -> client.register("Kuba","Bialy","1111","asd@asd.pl","kuba","Kuba"));

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
    public void generateParcelNumber_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableClients();
            testBase.createTableAdres();
            testBase.insertClient("Adam","Nowak", "+48 110 110 110", "myEmail@gmail.com",
                    "admin","admin");
            client.login("admin","admin",testBase);
            long a = client.generateParcelNumber();
            Assertions.assertNotEquals(a,client.generateParcelNumber());
            Assertions.assertDoesNotThrow(() ->client.generateParcelNumber());
            Assertions.assertTrue(client.generateParcelNumber()>0);

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
    public void createPayment_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTablePayment();

            Assertions.assertNotEquals(-1,client.createPayment(10.1));
            Assertions.assertDoesNotThrow(() ->client.createPayment(10.1));
            Assertions.assertTrue(client.createPayment(10.1)>0);
            Assertions.assertFalse(client.createPayment(10.1)<0);

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
    public void getParcelInfo_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableParcels();

            testBase.insertParcel(1,1,10.f,1,1,1,1,1,1,"shipper","dania",1,1,1);
            Assertions.assertNotNull(client.getParcelInfo(1));

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
    public void getAddressInfo_test (){
        Client client = new Client();
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableAdres();

            testBase.insertAdres("Kielce","mala","1111");
            Assertions.assertNotNull(client.getAddressInfo(1));

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
