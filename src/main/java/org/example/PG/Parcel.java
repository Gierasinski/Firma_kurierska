package org.example.PG;

import java.util.Random;

public class Parcel {
    private int parcelNumber;
    private int waybillNumber = 1;
    private float weight;
    private int height, width, length;
    private int payment;
    private int delivery_address, shipment_address;
    private String status;
    private String localization;

    public Parcel(int parcelNumber, int waybillNumber, float weight, int height, int width, int length, int payment,
                  int delivery_address, int shipment_address, String status, String localization) {
        this.parcelNumber = parcelNumber;
        this.waybillNumber = waybillNumber;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.payment = payment;
        this.delivery_address = delivery_address;
        this.shipment_address = shipment_address;
        this.status = status;
        this.localization = localization;
    }
    public int getParcelNumber() {
        return parcelNumber;
    }

    public int getWaybillNumber() {
        return waybillNumber;
    }

    public float getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getPayment() {
        return payment;
    }

    public int getDelivery_address() {
        return delivery_address;
    }

    public int getShipment_address() {
        return shipment_address;
    }

    public String getStatus() {
        return status;
    }

    public String getLocalization() {
        return localization;
    }

}