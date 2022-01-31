package org.example.global;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;

public class Branch {
    private int id;
    private String name;
    private int code;

    ManageDataBase base =  new  ManageDataBase();

    public Branch(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {return id;}

    public void addBranch(String city, String road, String numberHause)  {

        try {
            int idAddress;
            base.connectToDataBase();
            idAddress = base.insertAdres(city, road+numberHause, "23-650");
            base.insertBranch(name,code, idAddress);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBranch(int id) throws SQLException {
        base.connectToDataBase();
        try {
            base.deleteBranch(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void receivetheparcel() {
    }
    public void sendtheparcel() {
    }
}

