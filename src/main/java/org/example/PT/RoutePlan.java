package org.example.PT;

public class RoutePlan {

    private int parcelNumber;
    private int idAddressA;
    private int idAddressB;

    public RoutePlan(int parcelNumber,int idAddressA, int idAddressB){
        this.parcelNumber = parcelNumber;
        this.idAddressA = idAddressA;
        this.idAddressB = idAddressB;
    }
    public int getIdAddressB() {return idAddressB;}
    public int getIdAddressA() {return idAddressA;}
    public int getParcelNumber() {return parcelNumber;}
}
