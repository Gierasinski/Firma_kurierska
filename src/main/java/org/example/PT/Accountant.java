package org.example.PT;

import java.util.Date;

public class Accountant extends Employee{


    public Accountant(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
    }

    public void wystawFakture(){

    }

    public void pzyznajPremie(int pesel, int pensja){
        if(pesel == getPesel()){
            pensja += 100;
        }


    }

    public void wyplacPensje(){

    }
}
