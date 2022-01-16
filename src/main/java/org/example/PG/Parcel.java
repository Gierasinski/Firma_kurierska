package org.example.PG;

import java.sql.PreparedStatement;
import java.sql.SQLException;

class Parcel {
    private int parcelNumber;
    private int waybillNumber = 1;
    private float weight;
    private int height, width, length;
    private int payment;
    private int delivery_address, shipment_address;
    private String status;
    private String localization;

    public Parcel(int parcelNumber, int waybillNumber, float weight, int height, int width, int length, int payment, int delivery_address, int shipment_address, String status, String localization) {
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

class ParcelFromLocker extends Parcel {

    private int shipmentCode;
    public ParcelFromLocker(int parcelNumber, int waybillNumber, float weight, int height, int width, int length, int payment, int delivery_address, int shipment_address, String status, String localization, int shipmentCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address, shipment_address, status, localization);
        this.shipmentCode = shipmentCode;
    }
}
class ParcelToLocker extends Parcel {

    private int pickupCode;
    public ParcelToLocker(int parcelNumber, int waybillNumber, float weight, int height, int width, int length, int payment, int delivery_address, int shipment_address, String status, String localization, int pickupCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address, shipment_address, status, localization);
        this.pickupCode = pickupCode;
    }
}
class ParcelFromToLocker extends Parcel {
    private int shipmentCode;
    private int pickupCode;

    public ParcelFromToLocker(int parcelNumber, int waybillNumber, float weight, int height, int width, int length, int payment, int delivery_address, int shipment_address, String status, String localization, int shipmentCode, int pickupCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address, shipment_address, status, localization);
        this.shipmentCode = shipmentCode;
        this.pickupCode = pickupCode;
    }
}
