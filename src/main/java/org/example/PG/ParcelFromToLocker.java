package org.example.PG;

public class ParcelFromToLocker extends Parcel {
    private int shipmentCode;
    private int pickupCode;

    public ParcelFromToLocker(long parcelNumber, long waybillNumber, float weight, int height, int width, int length,
                              int payment, int delivery_address, int shipment_address, String status, String localization,
                              int shipmentCode, int pickupCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address, shipment_address,
                status, localization);
        this.shipmentCode = shipmentCode;
        this.pickupCode = pickupCode;
    }

    public int getShipmentCode() {
        return shipmentCode;
    }

    public int getPickupCode() {
        return pickupCode;
    }
}