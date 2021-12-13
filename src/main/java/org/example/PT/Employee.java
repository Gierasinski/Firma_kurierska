package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.util.Date;

public class Employee {
    protected int pesel;
    private int pensja, numerTelefonu;
    private String imie,nazwisko;
    private Date dataZadtrudnienia;

    ManageDataBase base =  new  ManageDataBase();

    public Employee(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia){
        setPesel(pesel);
        setPensja(pensja);
        setNumerTelefonu(numerTelefonu);
        setImie(imie);
        setNazwisko(nazwisko);
        setDataZatrudnienia(dataZadtrudnienia);
    }

    public final void setPesel(int pesel) {this.pesel = pesel; }

    public void setPensja(int pensja) {this.pensja = pensja; }

    public void setNumerTelefonu(int numerTelefonu) {this.numerTelefonu = numerTelefonu;}

    public void setImie(String imie) {this.imie = imie;}

    public void setNazwisko(String nazwisko) {this.nazwisko = nazwisko;}

    public final void setDataZatrudnienia(Date dataZadtrudnienia) {this.dataZadtrudnienia = dataZadtrudnienia;}


    public final int getPesel() {return pesel; }
    public final Date getDataZadtrudnienia() {return dataZadtrudnienia; }


    public void sprawdzTrase(){

    }
    /*Nie wiem czy to ma byc tutaj czy w ksiegowej czy w jakims adminie co to robi  */
    public void dodajPracownika(int id, String imie, String nazwisko, String kontakt,
                                String adres, String pesel, String stanowisko,int pensja, String data_zatrudnienia){
        base.insertEmployee(id,imie,nazwisko, kontakt, adres, pesel, stanowisko, pensja,  data_zatrudnienia);


    }



}
