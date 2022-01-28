package org.example.PG;

public class ParcelToLocker extends Parcel {

    private int pickupCode;
    public ParcelToLocker(long parcelNumber, long waybillNumber, float weight, int height, int width, int length,
                          int payment, int delivery_address, int shipment_address, String status, String localization,
                          int pickupCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address, shipment_address,
                status, localization);
        this.pickupCode = pickupCode;
    }
}