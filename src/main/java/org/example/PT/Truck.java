package org.example.PT;

import java.util.Date;

public class Truck extends Delivery{
    protected String nrBoczny;
    private String typ, marka;
    private Date rokProdukcji;


    public Truck(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia, String typ, String marka, String nrBoczny, Date rokProdukcji ) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
        setTyp(typ);
        setMarka(marka);
        setNrBoczny(nrBoczny);
        setRokProdukcji(rokProdukcji);

    }
    public void setTyp(String typ) {this.typ = typ; }
    public void setMarka(String marka) {this.marka = marka; }
    public final void setNrBoczny(String nrBoczny) {this.nrBoczny = nrBoczny; }
    public final void setRokProdukcji(Date rokProdukcji) {this.rokProdukcji = rokProdukcji; }

    public void dodajPojazd(){

    }

    public void usunPojazd(){

    }

}
