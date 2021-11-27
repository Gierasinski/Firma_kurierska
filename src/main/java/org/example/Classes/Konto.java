package org.example.Classes;

public class Konto {
    private String login, haslo, email, numerTelefonu, imie, nazwisko;
    Adres adres;

    public Konto(String login, String haslo, String email, String numerTelefonu, String imie, String nazwisko) {
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public void dodajAdres(String wojewodztwo, String miasto, String ulica, int nrBloku){
        adres = new Adres(wojewodztwo, miasto, ulica, nrBloku);
    }
}
