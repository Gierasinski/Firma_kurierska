package org.example.parcel;

import org.example.client.ShipperInfo;
import org.example.global.Address;

public class Parcel {
    private long parcelNumber;
    private long waybillNumber = 1;
    private float weight;
    private int height, width, length;
    private int payment;
    private int delivery_address, shipment_address;
    private String status;
    private String localization;
    private Invoice invoice;

    public Parcel(){}
    public Parcel(long parcelNumber, int payment, int delivery_address, String status, String localization) {
        this.parcelNumber = parcelNumber;
        this.payment = payment;
        this.delivery_address = delivery_address;
        this.status = status;
        this.localization = localization;
    }
    public Parcel(long parcelNumber, long waybillNumber, float weight, int height, int width, int length, int payment,
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
    public long  getParcelNumber() {
        return parcelNumber;
    }

    public long  getWaybillNumber() {
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

    public void createInvoice(ShipperInfo shipper, Payment pay){
        invoice = new Invoice();
        invoice.writeInvoice(parcelNumber, shipper, pay);
    }


}