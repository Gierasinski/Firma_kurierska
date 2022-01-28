package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class EmployeeTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";

    @Test()
    public void addEmployeeTestDB () {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableEmployee();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(format.parse("01-01-1999").getTime());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            testBase.insertEmployee("1234", "piotr", "Tkaczyl", 43242423, 1, 3432432, "delivery", 4344, (java.sql.Date) sqlDate, 1);

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
    public void addEmloyeeTestClass() {
        try {
            Accountant accountant = new Accountant();
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableEmployee();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(format.parse("01-01-1999").getTime());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            accountant.addEmployee("piotr","tkaczyk","warszawa","wiejska","21",432423423,53453453,"storekeeper",2600,(java.sql.Date)sqlDate);

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
    public void loginEmployeeTestDB () {
        try {
            Employee employee = new Employee();
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            testBase.createTableEmployee();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(format.parse("01-01-1999").getTime());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            testBase.insertEmployee("1234", "piotr", "Tkaczyl", 43242423, 1, 3432432, "delivery", 4344, (java.sql.Date) sqlDate, 1);
            employee.loginEmployee("1234");
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

}