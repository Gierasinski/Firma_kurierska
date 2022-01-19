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
}