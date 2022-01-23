package org.example.PostgreSQL;

import org.example.PG.Account;

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
    public void reInitDataBase(){
        connectToPostgreSQL();
        try {
            dropDataBase();
            createDataBase();
            connectToDataBase();

            createTableEmployee();
            createTableAdres();
            createTableParcels();
            createTableClients();
            createTableTruck();
            createTableBranch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void dropDataBase() throws SQLException {
        String sql = "DROP DATABASE "+databaseName+" WITH (FORCE)";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    public void dropDataBase(String databaseNameParam) throws SQLException {
        String sql = "DROP DATABASE "+databaseNameParam+" WITH (FORCE)";
        Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
    }
    /**
     * Metoda odpowiedzialna za utworzenie tabeli pracownicy
     * @throws  SQLException
     */
    public void createTableEmployee() throws SQLException {
            String sql = "CREATE TABLE employee (id SERIAL,workerCode varchar(20) UNIQUE, name varchar(20), surname varchar(20)," +
                    "phoneNumber INTEGER, idAddress INTEGER, pesel INTEGER UNIQUE, position varchar(20), " +
                    "salary INTEGER , dateOfEmployment Date, idBranch INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table employee Created");
    }
    /**usuniecie tabeli pracownicy */
    public void deleteTableEmployee() throws SQLException {
            String sql = "DROP TABLE employee";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table employee Delete");
    }
    /**stworzenie tabeli samochod */
    public void createTableTruck() throws SQLException {
            String sql = "CREATE TABLE truck (id SERIAL,sideNumber varchar(20), type varchar(20)," +
                    "mark varchar(20), yearOfProduction INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Created");
    }


    /**usuniecie tabeli samochod */
    public void deleteTableTruck() throws SQLException {
            String sql = "DROP TABLE Truck";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Delete");
    }
    /**stworzenie tabeli oddzial */
    public void createTableBranch() throws SQLException {
        String sql = "CREATE TABLE Branch (id SERIAL,name varchar(20), code INTEGER)";
        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table Branch Created");
    }
    /**usuniecie tabeli oddzial */
    public void deleteTableBranch() throws SQLException {
        String sql = "DROP TABLE Branch";
        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table Branch Delete");
    }

    public void createTableClients() throws SQLException {
            String sql = "CREATE TABLE klienci (id SERIAL ,imie varchar(20), nazwisko varchar(20)," +
                    "kontakt varchar(18), adres INTEGER,  email varchar(40) UNIQUE, " +
                    "NIP INTEGER , login varchar(30) UNIQUE, haslo varchar(30))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Created");
    }
    public int insertClient(String imie, String nazwisko, String kontakt,
                            String email, String login, String haslo) throws SQLException {
        int id = -1;
        String query = "select * from klienci where login like '"+login+"';";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            return -2;
        }

        query = "select * from klienci where email like '"+email+"';";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            return -3;
        }

        String sql = "INSERT INTO klienci(imie, nazwisko, kontakt, email,login, haslo) values (?,?,?,?,?,?) RETURNING id";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,imie);
        pst.setString(2,nazwisko);
        pst.setString(3,kontakt);
        pst.setString(4,email);
        pst.setString(5,login);
        pst.setString(6,haslo);
        resultSet = pst.executeQuery();
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;

    }
    public Account loginClients(String login, String haslo) throws SQLException {
        Account myAccount = new Account();
        String query = "select * from klienci where login like '"+login+"' AND haslo like '"+haslo+"';";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            myAccount = new Account(resultSet.getInt("id"), login,haslo,resultSet.getString("email"), resultSet.getString("kontakt"),
                    resultSet.getString("imie"), resultSet.getString("nazwisko"));
        }
        return myAccount;
    }
    public void deleteTableClients() throws SQLException {
            String sql = "DROP TABLE klienci";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Delete");
    }
    public void createTableParcels() throws SQLException {
            String sql = "CREATE TABLE przesylki (id INTEGER UNIQUE,list_przewozowy INTEGER UNIQUE, waga INTEGER," +
                    "wysokosc INTEGER, szerokosc INTEGER, dlugosc INTEGER, platnosc INTEGER UNIQUE, " +
                    "adres_dostawy INTEGER, adres_nadania INTEGER, status varchar(30), lokalizacja varchar(30)," +
                    " kod_nadania INTEGER , kod_odbioru INTEGER, id_klienta INTEGER )";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table przesylki Created");
    }
    public void insertParcel(int id, int list_przewozowy, float waga, int wysokosc,
                               int szerokosc, int dlugosc, int platnosc,int adres_dostawy,
                             int adres_nadania, String status, String lokalizacja, int kod_nadania, int kod_odbioru, int id_klienta) throws SQLException {
        String sql = "INSERT INTO przesylki(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacja, kod_odbioru, kod_nadania, id_klienta) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        pst.setInt(14,id_klienta);
        pst.execute();

    }
    public void insertParcel(int id, int list_przewozowy, float waga, int wysokosc,
                             int szerokosc, int dlugosc, int platnosc,int adres_dostawy, int adres_nadania, String status, String lokalizacja, int id_klienta) throws SQLException {
        String sql = "INSERT INTO przesylki(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacjal, id_klienta) values (?,?,?,?,?,?,?,?,?,?,?,?)";
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
        pst.setInt(12,id_klienta);
        pst.execute();

    }
    public void createTableAdres() throws SQLException {
            String sql = "CREATE TABLE adres (id SERIAL, miasto varchar(20),ulica varchar(20), kod_pocztowy varchar(7))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Created");
    }
    public int insertAdres(String miasto, String ulica, String kod_poczotwy) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO adres(miasto, ulica, kod_pocztowy) values (?,?,?) RETURNING id";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,miasto);
        pst.setString(2,ulica);
        pst.setString(3,kod_poczotwy);
        resultSet = pst.executeQuery();
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }

    public void deleteTableAdres() throws SQLException {
            String sql = "DROP TABLE adres";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Delete");
    }


    /**wyszukanie pracownika o podany id */
    public int searchEmployee(int id) throws SQLException {
        int salary = 0;
        String query = "select salary from employee where id="+id+";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while(resultSet.next()) {
               salary = resultSet.getInt("salary");
                i++;
            }
        return salary;
    }

/**dodanie pracownika */
    public void insertEmployee(String workerCode, String name, String surname, int phoneNumber,
                       int idAddress, int pesel, String position,int salary, Date dateOfEmployment, int idBranch) throws SQLException {
            String sql = "INSERT INTO employee(workerCode, name,surname,phoneNumber,idAddress,pesel,position,salary,dateOfEmployment,idBranch) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,workerCode);
            pst.setString(2,name);
            pst.setString(3,surname);
            pst.setInt(4,phoneNumber);
            pst.setInt(5,idAddress);
            pst.setInt(6,pesel);
            pst.setString(7,position);
            pst.setInt(8,salary);
            pst.setDate(9, dateOfEmployment);
            pst.setInt(10,idBranch);
            pst.execute();
    }

    /**wyszukanie pracownika o podany numerze pracownika */
    public String searchEmployeeWorkerCode(String workerCode) throws SQLException {
        String code = null;
       // Employee myEmployee = new Employee();
        String query = "select salary from employee where workerCode="+workerCode+";";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        int i = 0;
        while(resultSet.next()) {
            /*
            myEmployee = new Employee(resultSet.getInt("id"),resultSet.getInt("pesel"),resultSet.getInt("salary"),
                    resultSet.getInt("phoneNumber"),workerCode, resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("position"), resultSet.getDate("dateOfEmployment"));

             */
            code = resultSet.getString("workerCode");
            i++;
        }
        return code;
    }

    /**dodanie oddzialu */
    public void insertBranch(String name, int code) throws SQLException {
        String sql = "INSERT INTO Branch(name,code) values (?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,name);
        pst.setInt(2,code);
        pst.execute();
    }
    /**usuniecie oddzialu */
    public void deleteBranch(int id) throws SQLException {
        String sql = "DELETE * FROM Branch WHERE id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();

    }
    /**dodanie samochodu */
    public void insertTruck(String sideNumber, String type, String mark, int yearOfProduction) throws SQLException {
            String sql = "INSERT INTO truck(sideNumber,type,mark,yearOfProduction) values (?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,sideNumber);
            pst.setString(2,type);
            pst.setString(3,mark);
            pst.setInt(4,yearOfProduction);
            pst.execute();
    }
    /**usuniecie samochodu */
    public void deleteTruck(int id) throws SQLException {
            String sql = "DELETE * FROM Truck WHERE id = ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();

    }
    /**aktualizacja pensji pracownika*/
    public void updateSalary(int salary,int id) throws SQLException {
            String sql = "UPDATE employee SET salary = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, salary);
            pst.setInt(2, id);
            pst.execute();
    }

    /**aktualizacja statusu przesylki*/
    public void updateParcelStatus(String status, int numberParcel) throws SQLException {
        String sql = "UPDATE parcel SET status = ? WHERE numberParcel = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, status);
        pst.setInt(2, numberParcel);
        pst.execute();
    }

}
