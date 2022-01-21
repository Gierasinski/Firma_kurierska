package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;

public class Truck {
    private int id, yearOfProduction;
    private String type, mark, sideNumber;

    ManageDataBase base =  new  ManageDataBase();

    public Truck(int id, String sideNumber, String type, String mark, int yearOfProduction ) {
        this.id = id;
        this.sideNumber = sideNumber;
        this.type = type;
        this.mark = mark;
        this.yearOfProduction = yearOfProduction;

    }


    public void addTruck(){
        try {
            base.insertTruck(id,sideNumber,type,mark,yearOfProduction);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTruck(){
        try {
            base.deleteTruck(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
