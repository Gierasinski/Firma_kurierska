package org.example.PostgeSQLTest;

import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.*;
import org.postgresql.util.PSQLException;


public class ManageDataBaseTest{
    ManageDataBase testBase = new ManageDataBase();
    public static String jdbcUrl = "jdbc:postgresql://localhost:5432/";
    public static String username = "postgres";
    public static String password = "postgres";
    public static String databaseName = "firma_kurierska_test";

    @Test()
    public void connectToPostgreSQL_test (){
        //Assertions.assertThrows(PSQLException.class, () -> testBase.connectToDataBase(databaseName));
        testBase.connectToPostgreSQL();
    }
    @Test()
    public void createDataBase_test (){
        testBase.connectToPostgreSQL();
        testBase.createDataBase(databaseName);
    }
    @Test()
    public void createDataBase_throwsPSQLException (){
        testBase.connectToPostgreSQL();
        //Assertions.assertThrows(PSQLException.class, () -> testBase.createDataBase(databaseName));
    }
    @Test()
    public void dropDataBase_test (){
        testBase.connectToPostgreSQL();
        testBase.dropDataBase(databaseName);
        //Assertions.assertThrows(PSQLException.class, () -> testBase.createDataBase(databaseName));
    }
}