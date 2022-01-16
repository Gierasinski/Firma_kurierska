package org.example.PostgreSQL;

import javafx.scene.control.Alert;
import java.sql.*;

public class ManageDataBase {
    String jdbcUrl = ConnectionParameters.jdbcUrl;
    String username = ConnectionParameters.username;
    String password = ConnectionParameters.password;
    String databaseName = ConnectionParameters.databaseName;
    Connection connection;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    /**
     * Konstruktor klasy nawiazuje polaczenie z baza postgreSQL
     */
    public ManageDataBase(){}
    public void connectToDataBase() throws SQLException {
            //connection = DriverManager.getConnection(jdbcUrl,username,password);
           // createDatabase();
            connection = DriverManager.getConnection(jdbcUrl+databaseName,username,password);
    }
    public void connectToDataBase(String databaseNameParam) throws SQLException {
            connection = DriverManager.getConnection(jdbcUrl+databaseNameParam,username,password);
    }
    public void connectToPostgreSQL(){
        try {
            connection = DriverManager.getConnection(jdbcUrl,username,password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }
    }
    public void createDataBase() throws SQLException {
        // To zapytanie powinno tworzyć bazę danych jeśli jeszcze nie istnieje, jednak występuje problem ze znakiem "/" przy gexec
        /*String sql = "SELECT 'CREATE DATABASE "+databaseName+"'\n" +
                "WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"+databaseName+"')\gexec";*/
        //Tymczasowe zapytanie po prostu tworzące bazę danych
        String sql = "CREATE DATABASE "+databaseName+"";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    public void createDataBase(String databaseNameParam) throws SQLException {
        // To zapytanie powinno tworzyć bazę danych jeśli jeszcze nie istnieje, jednak występuje problem ze znakiem "/" przy gexec
        /*String sql = "SELECT 'CREATE DATABASE "+databaseName+"'\n" +
                "WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"+databaseName+"')\gexec";*/
        //Tymczasowe zapytanie po prostu tworzące bazę danych
        String sql = "CREATE DATABASE "+databaseNameParam+"";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    public void dropDataBase() throws SQLException {
        String sql = "DROP DATABASE "+databaseName+"";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    public void dropDataBase(String databaseNameParam) throws SQLException {
        String sql = "DROP DATABASE "+databaseNameParam+"";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    /**
     * Metoda odpowiedzialna za utworzenie tabeli pracownicy
     * @throws  SQLException
     */
    public void createTablePracownicy() throws SQLException {
            String sql = "CREATE TABLE pracownicy (id INTEGER,imie varchar(20), nazwisko varchar(20)," +
                    "numertelefonu INTEGER, adres varchar(30), pesel varchar(11) UNIQUE, stanowisko varchar(20), " +
                    "pensja INTEGER , data_zatrudnienia varchar(15), oddzial INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table pracownicy Created");
    }
    public void deleteTablePracownicy() throws SQLException {
            String sql = "DROP TABLE pracownicy";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table pracownicy Delete");
    }
    public void createTableTruck() throws SQLException {
            String sql = "CREATE TABLE truck (id INTEGER,numerBoczny varchar(20), typ varchar(20)," +
                    "marka varchar(20), rokProdukcji INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Created");
    }
    public void deleteTableTruck() throws SQLException {
            String sql = "DROP TABLE Truck";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Delete");
    }
    public void createTableClients() throws SQLException {
            String sql = "CREATE TABLE klienci (id INTEGER,imie varchar(20), nazwisko varchar(20)," +
                    "kontakt varchar(12), adres varchar(30), pesel varchar(11) UNIQUE, email varchar(40) UNIQUE, " +
                    "NIP INTEGER , login varchar(30) UNIQUE, haslo varchar(30))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Created");
    }
    public void deleteTableClients() throws SQLException {
            String sql = "DROP TABLE klienci";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Delete");
    }
    public void createTableParcels() throws SQLException {
            String sql = "CREATE TABLE przesylki (id INTEGER UNIQUE,list_przewozowy INTEGER , waga INTEGER," +
                    "wysokosc INTEGER, szerokosc INTEGER, dlugosc INTEGER, platnosc INTEGER UNIQUE, " +
                    "adres_dostawy INTEGER, adres_nadania INTEGER, status varchar(30)), lokalizacja varchar(30)), INTEGER kod_nadania, INTEGER kod_odbioru";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table przesylki Created");
    }
    public void insertParcel(int id, int list_przewozowy, float waga, int wysokosc,
                               int szerokosc, int dlugosc, int platnosc,int adres_dostawy, int adres_nadania, String status, String lokalizacja, int kod_nadania, int kod_odbioru) throws SQLException {
        String sql = "INSERT INTO pracownicy(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacjal, kod_odbioru, kod_nadania) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,id);
        pst.setInt(2,list_przewozowy);
        pst.setFloat(3,waga);
        pst.setInt(4,wysokosc);
        pst.setInt(5,szerokosc);
        pst.setInt(6,dlugosc);
        pst.setInt(7,platnosc);
        pst.setInt(8,adres_dostawy);
        pst.setInt(9,adres_nadania);
        pst.setString(10,status);
        pst.setString(11,lokalizacja);
        pst.setInt(12,kod_odbioru);
        pst.setInt(13,kod_nadania);
        pst.execute();

    }
    public void insertParcel(int id, int list_przewozowy, float waga, int wysokosc,
                             int szerokosc, int dlugosc, int platnosc,int adres_dostawy, int adres_nadania, String status, String lokalizacja) throws SQLException {
        String sql = "INSERT INTO pracownicy(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacjal) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,id);
        pst.setInt(2,list_przewozowy);
        pst.setFloat(3,waga);
        pst.setInt(4,wysokosc);
        pst.setInt(5,szerokosc);
        pst.setInt(6,dlugosc);
        pst.setInt(7,platnosc);
        pst.setInt(8,adres_dostawy);
        pst.setInt(9,adres_nadania);
        pst.setString(10,status);
        pst.setString(11,lokalizacja);
        pst.execute();

    }
    public void createTableAdres() throws SQLException {
            String sql = "CREATE TABLE adres (id INTEGER,wojewodztwo varchar(20), miasto varchar(20)," +
                    "ulica varchar(20), nrBloku varchar(10), opis varchar(30) )";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Created");
    }

    public void deleteTableAdres() throws SQLException {
            String sql = "DROP TABLE adres";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Delete");
    }

    public int searchEmployee(int id) throws SQLException {
        int pensja = 0;
        String query = "select pensja from pracownicy where id="+id+";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while(resultSet.next()) {
               pensja = resultSet.getInt("pensja");
                i++;
            }
        return pensja;
    }


    public void insertEmployee(int id, String imie, String nazwisko, int numerTelefonu,
                       String adres, int pesel, String stanowisko,int pensja, String data_zatrudnienia, int oddzial) throws SQLException {
            String sql = "INSERT INTO pracownicy(id,imie,nazwisko,numerTelefonu,adres,pesel,stanowisko,pensja,data_zatrudnienia,oddzial) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,imie);
            pst.setString(3,nazwisko);
            pst.setInt(4,numerTelefonu);
            pst.setString(5,adres);
            pst.setInt(6,pesel);
            pst.setString(7,stanowisko);
            pst.setInt(8,pensja);
            pst.setString(9,data_zatrudnienia);
            pst.setInt(10,oddzial);
            pst.execute();

    }

    public void insertTruck(int id, String numerBoczny, String typ, String marka, int rokProdukcji) throws SQLException {
            String sql = "INSERT INTO truck(id,numerboczny,typ,marka,rokprodukcji) values (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,numerBoczny);
            pst.setString(3,typ);
            pst.setString(4,marka);
            pst.setInt(5,rokProdukcji);
            pst.execute();
    }

    public void deleteTruck(int id, String numerBoczny) throws SQLException {
            String sql = "DELETE * FROM Truck WHERE id = ? AND numerBoczny = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, numerBoczny);
            pst.execute();

    }
    public void updatePensja(int pensja,int id) throws SQLException {
            String sql = "UPDATE Truck SET pensja = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, pensja);
            pst.setInt(2, id);
            pst.execute();
    }

}
