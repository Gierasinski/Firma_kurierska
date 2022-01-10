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
    public void connectToDataBase(){
        try {
            //connection = DriverManager.getConnection(jdbcUrl,username,password);
           // createDatabase();
            connection = DriverManager.getConnection(jdbcUrl+databaseName,username,password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }

    }
    public void connectToDataBase(String databaseNameParam){
        try {
            connection = DriverManager.getConnection(jdbcUrl+databaseNameParam,username,password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }
    }
    public void connectToPostgreSQL(){
        try {
            connection = DriverManager.getConnection(jdbcUrl,username,password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }
    }
    public void createDataBase(){
        // To zapytanie powinno tworzyć bazę danych jeśli jeszcze nie istnieje, jednak występuje problem ze znakiem "/" przy gexec
        /*String sql = "SELECT 'CREATE DATABASE "+databaseName+"'\n" +
                "WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"+databaseName+"')\gexec";*/
        //Tymczasowe zapytanie po prostu tworzące bazę danych
        String sql = "CREATE DATABASE "+databaseName+"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createDataBase(String databaseNameParam){
        // To zapytanie powinno tworzyć bazę danych jeśli jeszcze nie istnieje, jednak występuje problem ze znakiem "/" przy gexec
        /*String sql = "SELECT 'CREATE DATABASE "+databaseName+"'\n" +
                "WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"+databaseName+"')\gexec";*/
        //Tymczasowe zapytanie po prostu tworzące bazę danych
        String sql = "CREATE DATABASE "+databaseNameParam+"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropDataBase(){
        String sql = "DROP DATABASE "+databaseName+"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropDataBase(String databaseNameParam){
        String sql = "DROP DATABASE "+databaseNameParam+"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda odpowiedzialna za utworzenie tabeli pracownicy
     * @throws  SQLException
     */
    public void createTablePracownicy() {
        try {
            String sql = "CREATE TABLE pracownicy (id INTEGER,imie varchar(20), nazwisko varchar(20)," +
                    "numertelefonu INTEGER, adres varchar(30), pesel varchar(11) UNIQUE, stanowisko varchar(20), " +
                    "pensja INTEGER , data_zatrudnienia varchar(15), oddzial INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table pracownicy Created");
        } catch (SQLException throwables) {
            System.out.println("Error pracownicy create");
            throwables.printStackTrace();
        }
    }
    public void deleteTablePracownicy() {
        try {
            String sql = "DROP TABLE pracownicy";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table pracownicy Delete");
        } catch (SQLException throwables) {
            System.out.println("Error pracownicy  delete");
            throwables.printStackTrace();
        }
    }
    public void createTableTruck() {
        try {
            String sql = "CREATE TABLE truck (id INTEGER,numerBoczny varchar(20), typ varchar(20)," +
                    "marka varchar(20), rokProdukcji INTEGER)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Created");
        } catch (SQLException throwables) {
            System.out.println("Error truck create");
            throwables.printStackTrace();
        }
    }
    public void deleteTableTruck() {
        try {
            String sql = "DROP TABLE Truck";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table Truck Delete");
        } catch (SQLException throwables) {
            System.out.println("Error Truck  delete");
            throwables.printStackTrace();
        }
    }
    public void createTableClients() {
        try {
            String sql = "CREATE TABLE klienci (id INTEGER,imie varchar(20), nazwisko varchar(20)," +
                    "kontakt varchar(12), adres varchar(30), pesel varchar(11) UNIQUE, email varchar(40) UNIQUE, " +
                    "NIP INTEGER , login varchar(30) UNIQUE, haslo varchar(30))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Created");
        } catch (SQLException throwables) {
            System.out.println("Error klienci create");
            throwables.printStackTrace();
        }
    }
    public void deleteTableClients() {
        try {
            String sql = "DROP TABLE klienci";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table klienci Delete");
        } catch (SQLException e) {
            System.out.println("Error klienci delete");
            e.printStackTrace();
        }
    }
    public void createTableParcels() {
        try {
            String sql = "CREATE TABLE przesylki (id INTEGER UNIQUE,list_przewozowy INTEGER , waga INTEGER," +
                    "wysokosc INTEGER, szerokosc INTEGER, dlugosc INTEGER, platnosc INTEGER UNIQUE, " +
                    "adres_dostawy INTEGER, status varchar(30)), lokalizacja varchar(30))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table przesylki Created");
        } catch (SQLException throwables) {
            System.out.println("Error przesylki create");
            throwables.printStackTrace();
        }
    }
    public void createTableAdres() {
        try {
            String sql = "CREATE TABLE adres (id INTEGER,wojewodztwo varchar(20), miasto varchar(20)," +
                    "ulica varchar(20), nrBloku varchar(10), opis varchar(30) )";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Created");
        } catch (SQLException throwables) {
            System.out.println("Error adres create");
            throwables.printStackTrace();
        }
    }

    public void deleteTableAdres() {
        try {
            String sql = "DROP TABLE adres";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table adres Delete");
        } catch (SQLException throwables) {
            System.out.println("Error adres  delete");
            throwables.printStackTrace();
        }
    }

    public int searchEmployee(int id) {
        int pensja = 0;
        String query = "select pensja from pracownicy where id="+id+";";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while(resultSet.next()) {
               pensja = resultSet.getInt("pensja");
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();

            }
            catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return pensja;
    }


    public void insertEmployee(int id, String imie, String nazwisko, int numerTelefonu,
                       String adres, int pesel, String stanowisko,int pensja, String data_zatrudnienia, int oddzial) {
        try {
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

        } catch (SQLException throwables) {
            System.out.println("Error insert into database");
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert");
            alert.setHeaderText("Podczas dodawania danych wystapil blad");
            alert.setContentText(throwables.getMessage());
            alert.showAndWait().ifPresent(rs -> {
            });
        }
    }

    public void insertTruck(int id, String numerBoczny, String typ, String marka, int rokProdukcji) {
        try {
            String sql = "INSERT INTO truck(id,numerboczny,typ,marka,rokprodukcji) values (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,numerBoczny);
            pst.setString(3,typ);
            pst.setString(4,marka);
            pst.setInt(5,rokProdukcji);
            pst.execute();

        } catch (SQLException throwables) {
            System.out.println("Error insert into database");
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert");
            alert.setHeaderText("Podczas dodawania danych wystapil blad");
            alert.setContentText(throwables.getMessage());
            alert.showAndWait().ifPresent(rs -> {

            });
        }
    }

    public void deleteTruck(int id, String numerBoczny) {
        try {
            String sql = "DELETE * FROM Truck WHERE id = ? AND numerBoczny = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, numerBoczny);
            pst.execute();

        } catch (SQLException throwables) {
            System.out.println("Error Delete into database");
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert");
            alert.setHeaderText("Podczas usuwania danych wystapil blad");
            alert.setContentText(throwables.getMessage());
            alert.showAndWait().ifPresent(rs -> {

            });
        }
    }
    public void updatePensja(int pensja,int id) {
        try {
            String sql = "UPDATE Truck SET pensja = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, pensja);
            pst.setInt(2, id);
            pst.execute();
        } catch (SQLException throwables) {
            System.out.println("Error Update into database");
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert");
            alert.setHeaderText("Podczas aktualizacji danych wystapil blad");
            alert.setContentText(throwables.getMessage());
            alert.showAndWait().ifPresent(rs -> {

            });
        }
    }




    public static void main(String[] args){
        ManageDataBase base = new ManageDataBase();
        base.connectToDataBase();
        base.deleteTablePracownicy();
        base.createTablePracownicy();
        base.deleteTableAdres();
        base.createTableAdres();
        base.deleteTableTruck();
        base.createTableTruck();

    }

}
