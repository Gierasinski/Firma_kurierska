package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.util.Date;

public class Accountant extends Employee{
    ManageDataBase base =  new  ManageDataBase();

    public Accountant(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
    }

    public void wystawFakture(){

    }

    public void pzyznajPremie(int id){
        int pensja = base.searchEmployee(id);
        pensja += 100;


    }

    public void wyplacPensje(int id){
        int pensja = base.searchEmployee(id);


    }
}
