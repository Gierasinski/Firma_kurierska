package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;

public class Truck extends Delivery{
    protected String nrBoczny;
    private String typ, marka;
    private int rokProdukcji;

    ManageDataBase base =  new  ManageDataBase();


    public Truck(int pesel, int pensja, int numerTelefonu, String imie, String nazwisko, Date dataZadtrudnienia, String typ, String marka, String nrBoczny, int rokProdukcji ) {
        super(pesel, pensja, numerTelefonu, imie, nazwisko, dataZadtrudnienia);
        setTyp(typ);
        setMarka(marka);
        setNrBoczny(nrBoczny);
        setRokProdukcji(rokProdukcji);

    }
    public void setTyp(String typ) {this.typ = typ; }
    public void setMarka(String marka) {this.marka = marka; }
    public final void setNrBoczny(String nrBoczny) {this.nrBoczny = nrBoczny; }
    public final void setRokProdukcji(int rokProdukcji) {this.rokProdukcji = rokProdukcji; }

    public void addTruck(int id){
        try {
            base.insertTruck(id,nrBoczny,typ,marka,rokProdukcji);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTruck(int id){
        try {
            base.deleteTruck(id,nrBoczny);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
