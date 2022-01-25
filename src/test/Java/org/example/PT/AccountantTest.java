package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountantTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";
    Accountant accountant = new Accountant();

    @Test()
    public void givePremiumEmloyeeTest() {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableEmployee();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(format.parse("01-01-1999").getTime());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            accountant.addEmployee("piotr","tkaczyk",1,1123111,1231231,"storekeeper",2600,(java.sql.Date)sqlDate,2);
            accountant.givePremium(1,100);

        } catch (SQLException | ParseException e) {
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
    public void getFactureTest() {
        try {
            accountant.getFacture();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}