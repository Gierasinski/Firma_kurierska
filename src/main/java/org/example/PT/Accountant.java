package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;

public class Accountant extends Employee{
    ManageDataBase base =  new  ManageDataBase();

    public Accountant(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
    }

    public void wystawFakture(){

    }

    public void pzyznajPremie(int id){
        int pensja = 0;
        try {
            pensja = base.searchEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pensja += 100;
        try {
            base.updatePensja(pensja,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void wyplacPensje(int id){
        try {
            int pensja = base.searchEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
