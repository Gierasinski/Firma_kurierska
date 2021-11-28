package org.example.PG;

public class Waybill {
    private int waybillNumber;
    private Address shippingAddress;
    private Address pickupAddress;

    public Waybill(int waybillNumber, Address shippingAddress, Address pickupAddress) {
        this.waybillNumber = waybillNumber;
        this.shippingAddress = shippingAddress;
        this.pickupAddress = pickupAddress;
    }
}
