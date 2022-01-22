package org.example.AS;

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

    public void addBranch(){
        try {
            base.insertBranch(name,code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBranch(int id){
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

