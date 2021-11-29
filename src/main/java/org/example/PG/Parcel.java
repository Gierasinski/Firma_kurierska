package org.example.PG;

public class Parcel {
    private int parcelNumber;
    private float weight;
    private Waybill wayBill;
    private boolean delivered = false;
    //Tymczasowe rozwiazanie, trzeba przemyslec sposob generowania numeru przewozowego
    private int waybillNumber = 1;

    public Parcel(int parcelNumber, float weight, Address shippingAddress, Address pickupAddress) {
        this.parcelNumber = parcelNumber;
        this.weight = weight;

        wayBill = new Waybill(waybillNumber, shippingAddress, pickupAddress);
    }
    public void setDelivered(boolean status){
        delivered = status;
    }

}
