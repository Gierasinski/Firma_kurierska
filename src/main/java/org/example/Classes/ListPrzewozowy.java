package org.example.Classes;

public class ListPrzewozowy {
    private int numerListu;
    private Adres adresNadania;
    private Adres adresOdbioru;

    public ListPrzewozowy(int numerListu, Adres adresNadania, Adres adresOdbioru) {
        this.numerListu = numerListu;
        this.adresNadania = adresNadania;
        this.adresOdbioru = adresOdbioru;
    }
}
