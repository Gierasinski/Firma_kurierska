package org.example.PostgeSQLTest;

import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.*;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;


public class ManageDataBaseTest{
    ManageDataBase testBase = new ManageDataBase();
    public static String jdbcUrl = "jdbc:postgresql://localhost:5432/";
    public static String username = "postgres";
    public static String password = "postgres";
    public static String databaseName = "firma_kurierska_test";

    @Test()
    public void connectToPostgreSQL_test (){
        testBase.connectToPostgreSQL();
    }
    @Test()
    public void createAndDropDataBase_test (){
        testBase.connectToPostgreSQL();
        try {
            testBase.createDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(PSQLException.class, () -> testBase.createDataBase(databaseName));
        try {
            testBase.dropDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}