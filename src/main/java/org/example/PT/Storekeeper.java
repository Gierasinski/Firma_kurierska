package org.example.PT;

import java.time.Instant;
import java.util.Date;

public class Storekeeper extends Employee {
    public Storekeeper(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
    }

    public void reportTheDamage() {
        Date date = new Date();
        Instant instant = Instant.now();


    }

    public void transportPackage() {

    }
}
