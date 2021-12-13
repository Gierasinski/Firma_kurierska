package org.example.PostgreSQL;

import javafx.scene.control.Alert;

import java.sql.*;


public class ManageDataBase {
    String jdbcUrl = ConnectionParameters.jdbcUrl;
    String username = ConnectionParameters.username;
    String password = ConnectionParameters.password;
    Connection connection;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
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
    public void createTablePracownicy() {
        try {
            String sql = "CREATE TABLE pracownicy (id INTEGER,imie varchar(20), nazwisko varchar(20)," +
                    "kontakt varchar(12), adres varchar(30), pesel varchar(11) UNIQUE, stanowisko varchar(20), " +
                    "pensja INTEGER , data_zatrudnienia varchar(15))";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table employee Created");
        } catch (SQLException throwables) {
            System.out.println("Error employee create");
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


    public void insertEmployee(int id, String imie, String nazwisko, String kontakt,
                       String adres, String pesel, String stanowisko,int pensja, String data_zatrudnienia) {
        try {
            String sql = "INSERT INTO pracownicy(id,imie,nazwisko,kontakt,adres,pesel,stanowisko,pensja,data_zatrudnienia) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,imie);
            pst.setString(3,nazwisko);
            pst.setString(4,kontakt);
            pst.setString(5,adres);
            pst.setString(6,pesel);
            pst.setString(7,stanowisko);
            pst.setInt(8,pensja);
            pst.setString(9,data_zatrudnienia);
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




    public static void main(String[] args){
        ManageDataBase base = new ManageDataBase();
        base.deleteTablePracownicy();
        base.createTablePracownicy();
        base.deleteTableAdres();
        base.createTableAdres();

    }

}
