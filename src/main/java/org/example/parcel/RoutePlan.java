package org.example.parcel;

public class RoutePlan {

    private long parcelNumber;
    private int idAddressA;
    private int idAddressB;

    public RoutePlan(long parcelNumber,int idAddressA, int idAddressB){
        this.parcelNumber = parcelNumber;
        this.idAddressA = idAddressA;
        this.idAddressB = idAddressB;
    }
    public int getIdAddressB() {return idAddressB;}
    public int getIdAddressA() {return idAddressA;}
    public long getParcelNumber() {return parcelNumber;}
}
