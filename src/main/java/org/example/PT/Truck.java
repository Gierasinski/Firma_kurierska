package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;

public class Truck {
    private int  yearOfProduction;
    private String type, mark, sideNumber;

    ManageDataBase base =  new  ManageDataBase();

    public Truck(String sideNumber, String type, String mark, int yearOfProduction ) {
        this.sideNumber = sideNumber;
        this.type = type;
        this.mark = mark;
        this.yearOfProduction = yearOfProduction;

    }


    public void addTruck() throws SQLException {
        base.connectToDataBase();
        try {
            base.insertTruck(sideNumber,type,mark,yearOfProduction);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTruck(int id) throws SQLException {
        base.connectToDataBase();
        try {
            base.deleteTruck(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
