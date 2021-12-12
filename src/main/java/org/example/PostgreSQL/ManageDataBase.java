package org.example.PostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ManageDataBase {
    String jdbcUrl = ConnectionParameters.jdbcUrl;
    String username = ConnectionParameters.username;
    String password = ConnectionParameters.password;
    Connection connection;
    /**
     * Konstruktor klasy nawiazuje polaczenie z baza postgreSQL
     */
    public ManageDataBase(){
        try {
            connection = DriverManager.getConnection(jdbcUrl,username,password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }
    }
    /**
     * Metoda odpowiedzialna za utworzenie tabeli pracownicy
     * @throws  SQLException
     */
    public void createTable() {
        try {
            String sql = "CREATE TABLE pracownicy (imie varchar(20), nazwisko varchar(20)," +
                    "kontakt varchar(12), adres varchar(30), pesel varchar(11) UNIQUE, stanowisko varchar(20), " +
                    "pensja INTEGER , data_zatrudnienia varchar(15))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Created");
        } catch (SQLException throwables) {
            System.out.println("Error create");
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args){
        ManageDataBase base = new ManageDataBase();
        base.createTable();
    }

}
