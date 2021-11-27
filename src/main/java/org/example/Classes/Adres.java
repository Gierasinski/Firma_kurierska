package org.example.Classes;

public class Adres {

    private String wojewodztwo, miasto, ulica;
    private int nrBloku;

    public Adres(String wojewodztwo, String miasto, String ulica, int nrBloku) {
        this.wojewodztwo = wojewodztwo;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nrBloku = nrBloku;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNrBloku() {
        return nrBloku;
    }

    public void setNrBloku(int nrBloku) {
        this.nrBloku = nrBloku;
    }
}
