package org.example.parcel;

public class RoutePlan {

    private long parcelNumber;
    private int idAddressA;
    private int idAddressB;
    private String courier;
    private int stage;
    private String state;

    public RoutePlan(long parcelNumber,int idAddressA, int idAddressB, int stage, String state){
        this.parcelNumber = parcelNumber;
        this.idAddressA = idAddressA;
        this.idAddressB = idAddressB;
        this.state = state;
        this.stage = stage;
    }
    public int getIdAddressB() {return idAddressB;}
    public int getIdAddressA() {return idAddressA;}
    public long getParcelNumber() {return parcelNumber;}

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getState() {
        return state;
    }

    public void setParcelNumber(long parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    public void setIdAddressA(int idAddressA) {
        this.idAddressA = idAddressA;
    }

    public void setIdAddressB(int idAddressB) {
        this.idAddressB = idAddressB;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStage() {
        return stage;
    }
}
