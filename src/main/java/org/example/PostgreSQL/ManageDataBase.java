package org.example.PostgreSQL;

import org.example.PG.Account;
import org.example.PG.Address;
import org.example.PG.Parcel;
import org.example.PG.Payment;
import org.example.PT.Employee;
import org.example.PT.Route;
import org.example.PT.RoutePlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
            createTablePayment();
            createTableParcels();
            createTableClients();
            createTableTruck();
            createTableBranch();
            createTableDistance();
            createTableRoutePlan();
            insertToTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void insertToTable() throws SQLException {
        Route route = new Route();
        String sql = "INSERT INTO employee(pesel,salary,phoneNumber,workerCode,name,surname,position,dateofemployment)\n" +
                "VALUES ('543534', '4344', '43432423', '1111','ppp','gdfgd','delivery','10-12-2021');\n" +
                "INSERT INTO klienci(imie, nazwisko, kontakt, email,login, haslo, id)\n" +
                "VALUES ('kuba','stawka','543534','kubafsdfsd','kuba','1234', nextval('klient_id'));\n";

        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table inserted");
        insertEmployee("2222","kuba","jalek",6666,3,939393,"accountant",3600, null,2);
        insertEmployee("3333","maciek","gutek",33332,1,435435435,"storekeeper",4300, null,2);
        //oddzialy
        insertAdres("Warszawa","zlota 1","11-111");
        insertAdres("Krakow","czarna 1","22-2222");
        insertAdres("Kiece","biala 23","33-333");
        //
        //odleglosci miedzy nimi
        insertDistance(1,2);
        insertDistance(1,3);
        insertDistance(2,3);
        //
        insertAdres("Kiece","czarna 32","23-300");
        insertAdres("Radom","zielona 42","23-300");
        insertAdres("Kiece","wysoka 1","23-300");
        insertAdres("Kiece","mala 43","23-300");

        route.calculateRoute(1111,5,6);
        insertParcel(1111,1,23,2,32,21,0,1,2,"oplacona","radom",2,1,1);

        Employee employee = new Employee();
        employee.checkTheRoute(1111);
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
                    "phoneNumber INTEGER, idAddress INTEGER, pesel INTEGER, position varchar(20), " +
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
        String sql = "CREATE TABLE Branch (id SERIAL,name varchar(20), code INTEGER, idAddress INTEGER)";
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

            sql = "CREATE SEQUENCE klient_id START 100000000 INCREMENT 1 OWNED BY klienci.id";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Klienci SEQUENCE created");
    }
    public long insertClient(String imie, String nazwisko, String kontakt,
                            String email, String login, String haslo) throws SQLException {
        long id = -1;
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
        int addressID = insertAdres("","","");
        String sql = "INSERT INTO klienci(imie, nazwisko, kontakt, email,login, haslo, id, adres) values (?,?,?,?,?,?,nextval('klient_id'),?) RETURNING id";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,imie);
        pst.setString(2,nazwisko);
        pst.setString(3,kontakt);
        pst.setString(4,email);
        pst.setString(5,login);
        pst.setString(6,haslo);
        pst.setInt(7,addressID);
        resultSet = pst.executeQuery();
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;

    }
    public long updateClient(long klientID,int adresID,String imie, String nazwisko, String kontakt,
                             String email, String ulica, String miasto, String kod) throws SQLException {

        String sql = "UPDATE adres SET miasto = ?, ulica = ?, kod_pocztowy = ? WHERE id = ?";
        PreparedStatement pst = null;
        pst = connection.prepareStatement(sql);
        pst.setString(1,miasto);
        pst.setString(2,ulica);
        pst.setString(3,kod);
        pst.setInt(4,adresID);
        pst.executeUpdate();

        String sql1 = "UPDATE klienci SET imie = ?, nazwisko = ?, kontakt = ?, email = ? WHERE id = ?";
        PreparedStatement pst2 = connection.prepareStatement(sql1);
        pst2.setString(1,imie);
        pst2.setString(2,nazwisko);
        pst2.setString(3,kontakt);
        pst2.setString(4,email);
        pst2.setLong(5,klientID);
        pst2.executeUpdate();

        return klientID;

    }
    public long updateClientPassword(long klientID,String haslo) throws SQLException {

        String sql1 = "UPDATE klienci SET haslo = ? WHERE id = ?";
        PreparedStatement pst2 = connection.prepareStatement(sql1);
        pst2.setString(1,haslo);
        pst2.setLong(2,klientID);
        pst2.executeUpdate();
        return klientID;

    }
    public Account loginClients(String login, String haslo) throws SQLException {
        Account myAccount = new Account();
        String query = "select * from klienci where login = ? AND haslo = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setString(1,login);
        preparedStatement .setString(2,haslo);
        resultSet = preparedStatement.executeQuery();
        int addressID=-1;
        while(resultSet.next()) {
            myAccount = new Account(resultSet.getLong("id"), login,haslo,resultSet.getString("email"), resultSet.getString("kontakt"),
                    resultSet.getString("imie"), resultSet.getString("nazwisko"));
            addressID = resultSet.getInt("adres");
        }
        if(addressID>0) {
            query = "select * from adres where id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, addressID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myAccount.setAddress(new Address(resultSet.getInt("id"), resultSet.getString("miasto"),
                        resultSet.getString("ulica"), resultSet.getString("kod_pocztowy")));
            }
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
            String sql = "CREATE TABLE przesylki (id BIGINT UNIQUE,list_przewozowy BIGINT UNIQUE, waga INTEGER," +
                    "wysokosc INTEGER, szerokosc INTEGER, dlugosc INTEGER, platnosc INTEGER UNIQUE, " +
                    "adres_dostawy INTEGER, adres_nadania INTEGER, status varchar(30), lokalizacja varchar(30)," +
                    " kod_nadania INTEGER , kod_odbioru INTEGER, id_klienta INTEGER )";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table przesylki Created");
    }
    public Long[] getParcels(long clientID) throws SQLException {
        Account myAccount = new Account();
        String query = "SELECT count(*) as total from przesylki where id_klienta = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setLong(1,clientID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long array[] = new Long[resultSet.getInt("total")];

        query = "select * from przesylki where id_klienta = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setLong(1,clientID);
        resultSet = preparedStatement.executeQuery();

        int i=0;
        while(resultSet.next()) {
           array[i] = resultSet.getLong("id");
           i+=1;
        }
        return array;
    }
    public Parcel getParcelInfo(long parcelID) throws SQLException {
        Parcel parcel = new Parcel();
        String query = "select * from przesylki where id = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setLong(1,parcelID);

        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            parcel = new Parcel(resultSet.getLong("id"),resultSet.getInt("platnosc"),
                    resultSet.getInt("adres_dostawy"),resultSet.getString("status"),
                    resultSet.getString("lokalizacja"));
        }
        return parcel;
    }
    public Address getAddressInfo(int addressID) throws SQLException {
        Address address = new Address();
        String query = "select * from adres where id = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setInt(1,addressID);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            address= new Address(resultSet.getInt("id"),resultSet.getString("miasto"),
                    resultSet.getString("ulica"),resultSet.getString("kod_pocztowy"));
        }
        return address;
    }
    public void insertParcel(long  id, long  list_przewozowy, float waga, int wysokosc,
                               int szerokosc, int dlugosc, int platnosc,int adres_dostawy,
                             int adres_nadania, String status, String lokalizacja, int kod_nadania, int kod_odbioru, long id_klienta) throws SQLException {
        String sql = "INSERT INTO przesylki(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacja, kod_odbioru, kod_nadania, id_klienta) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong (1,id);
        pst.setLong (2,list_przewozowy);
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
        pst.setLong(14,id_klienta);
        pst.execute();

    }
    public void insertParcel(long  id, long  list_przewozowy, float waga, int wysokosc,
                             int szerokosc, int dlugosc, int platnosc,int adres_dostawy, int adres_nadania, String status, String lokalizacja, long id_klienta) throws SQLException {
        String sql = "INSERT INTO przesylki(id, list_przewozowy, waga, wysokosc, szerokosc, dlugosc, platnosc, adres_dostawy, adres_nadania, status, lokalizacjal, id_klienta) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong (1,id);
        pst.setLong (2,list_przewozowy);
        pst.setFloat(3,waga);
        pst.setInt(4,wysokosc);
        pst.setInt(5,szerokosc);
        pst.setInt(6,dlugosc);
        pst.setInt(7,platnosc);
        pst.setInt(8,adres_dostawy);
        pst.setInt(9,adres_nadania);
        pst.setString(10,status);
        pst.setString(11,lokalizacja);
        pst.setLong(12,id_klienta);
        pst.execute();

    }
    public void createTableAdres() throws SQLException {
            String sql = "CREATE TABLE adres (id SERIAL, miasto varchar(20),ulica varchar(20), kod_pocztowy varchar(7))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Created");
    }
    public void createTablePayment() throws SQLException {
        String sql = "CREATE TABLE platnosc (id SERIAL, koszt INTEGER,status varchar(20))";
        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table platnosc Created");
    }
    public int insertPayment(double price, String status) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO platnosc(koszt, status) values (?,?) RETURNING id";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setDouble(1,price);
        pst.setString(2,status);
        resultSet = pst.executeQuery();
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
    public void updatePayment(int ID, String status) throws SQLException {
        String sql = "UPDATE platnosc SET status = ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,status);
        pst.setInt(2,ID);
        pst.executeUpdate();
    }
    public Payment getPaymentInfo(int paymentID) throws SQLException {
        Payment payment = new Payment();
        String query = "select * from platnosc where id = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setInt(1,paymentID);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            payment= new Payment(resultSet.getInt("id"),resultSet.getDouble("koszt"),
                    resultSet.getString("status"));
        }
        return payment;
    }
    public int insertAdres(String miasto, String ulica, String kod_pocztowy) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO adres(miasto, ulica, kod_pocztowy) values (?,?,?) RETURNING id";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,miasto);
        pst.setString(2,ulica);
        pst.setString(3,kod_pocztowy);
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
    public int searchEmployee(String workerCode) throws SQLException {
        int salary = 0;
        String query = "select salary from employee where workerCode like '"+workerCode+"';";
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
    public Employee searchEmployeeWorkerCode(String workerCode) throws SQLException {
        Employee myEmployee = new Employee();
        String query = "select * from employee where workerCode like '"+workerCode+"';";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            myEmployee = new Employee(resultSet.getInt("id"),resultSet.getInt("pesel"),resultSet.getInt("salary"),
                    resultSet.getInt("phoneNumber"),workerCode, resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("position"), resultSet.getDate("dateOfEmployment"));

            i++;
        }
        return myEmployee;
    }


    /**dodanie oddzialu */
    public void insertBranch(String name, int code,int idAddress) throws SQLException {
        String sql = "INSERT INTO Branch(name,code,idAdrress) values (?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,name);
        pst.setInt(2,code);
        pst.setInt(3,idAddress);
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
        System.out.println("Truck Add");
    }
    /**usuniecie samochodu */
    public void deleteTruck(int id) throws SQLException {
            String sql = "DELETE FROM Truck WHERE id = ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            System.out.println("Truck Delete");

    }
    /**aktualizacja pensji pracownika*/
    public void updateSalary(int salary,String workerCode) throws SQLException {
            String sql = "UPDATE employee SET salary = ? WHERE workercode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, salary);
            pst.setString(2, workerCode);
            pst.execute();
            System.out.println("Salary update");
    }

    /**aktualizacja statusu przesylki*/
    public void updateParcelStatus(String status, long numberParcel) throws SQLException {
        String sql = "UPDATE przesylki SET status = ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, status);
        pst.setLong(2, numberParcel);
        pst.execute();
    }



    /**zapytanie dajace miasto nadawcy*/
    public String selectAdresToRouteA(int id) throws SQLException {
        String city = null;
        String street = null;
        String query = "select miasto,ulica from adres where id="+id+";";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            city = resultSet.getString("miasto");
            street = resultSet.getString("ulica");
            i++;
        }
        return city+", "+street;
    }
    /**zapytanie dajace miasto odbiorce*/
    public String selectAdresToRouteB(int id) throws SQLException {
        String city = null;
        String street = null;
        String query = "select miasto,ulica from adres where id="+id+";";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            city = resultSet.getString("miasto");
            street = resultSet.getString("ulica");
            i++;
        }
        return city+", "+street;
    }
    /**zwraca paczke po numerze paczki*/
    public Parcel getParcelInfoNumberCode(long numberCode) throws SQLException {
        Parcel parcel = new Parcel();
        String query = "select * from przesylki where id = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement .setLong(1,numberCode);

        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            parcel = new Parcel(resultSet.getLong("id"),resultSet.getLong("list_przewozowy"),resultSet.getFloat("waga"),resultSet.getInt("wysokosc"),
                    resultSet.getInt("szerokosc"),resultSet.getInt("dlugosc"),resultSet.getInt("platnosc"),
                    resultSet.getInt("adres_dostawy"),resultSet.getInt("adres_nadania"),
                    resultSet.getString("status"), resultSet.getString("lokalizacja"));
        }
        return parcel;
    }


    /**TRASA PLANOWANIE  */
    /**stworzenie tabeli podajaca losowa ilosc kilometrow miedzy punktami */
    public void createTableDistance() throws SQLException {
        String sql = "CREATE TABLE Distance (id SERIAL,idPointA INTEGER, idPointB INTEGER, distance INTEGER)";
        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table Distance Created");
    }
    /**dodanie dystancu */
    public void insertDistance(int A, int B) throws SQLException {
        Random rand = new Random();
        int distance = rand.nextInt(100);
        String sql = "INSERT INTO distance(idPointA, idPointB, distance) values (?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,A);
        pst.setInt(2,B);
        pst.setInt(3,distance);
        pst.execute();
    }

    /**zwrocenie dystansu miedzy punktami */
    public int getDistance(int a, int b) throws SQLException {
        int distance = 0;
        String query = "select * from Distance where idPointA="+a+" AND idPointB="+b+";";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            distance = resultSet.getInt("distance");
            i++;
        }
        return distance;
    }

    /**stworzenie tabeli zapisujace trase */
    public void createTableRoutePlan() throws SQLException {
        String sql = "CREATE TABLE route_plan (id SERIAL,parcelNumber BIGINT, idPointA INTEGER, idPointB INTEGER)";
        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("Table route_plan Created");
    }

    /**dodanie dystancu */
    public void insertRoutePlan(long parcelNumber,int A, int B) throws SQLException {
        String sql = "INSERT INTO route_plan(parcelNumber,idPointA, idPointB) values (?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,parcelNumber);
        pst.setInt(2,A);
        pst.setInt(3,B);
        pst.execute();
    }


    /**wyszukanie trasy o podany numerze paczki*/

    public ArrayList<RoutePlan> searchRouteToParcelNumber(long parcelNumber) throws SQLException {
        ArrayList<RoutePlan> plan = new ArrayList<RoutePlan>();
        String query = "select * from route_plan where parcelNumber="+parcelNumber+";";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int i = 0;
        while(resultSet.next()) {
            plan.add(new RoutePlan(resultSet.getLong("parcelnumber"),resultSet.getInt("idPointA"),resultSet.getInt("idPointB")));
            i++;
        }
        return plan;
    }


}
