package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class TruckTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";
    @Test()
    public void addTruckTestDB () {


        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableTruck();
            testBase.insertTruck("FHS743H", "Dostawczy", "Volvo", 2006);

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
    public void addTruckTestClass() {
        try {
            Truck truck = new Truck("HJSAS","Dostawczy","Hundai",2008);
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableTruck();
            truck.addTruck();
            truck.deleteTruck(1);

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