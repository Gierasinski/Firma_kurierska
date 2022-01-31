package org.example.parcel;

import org.example.parcel.Parcel;

public class ParcelFromLocker extends Parcel {

    private int shipmentCode;
    public ParcelFromLocker(long parcelNumber, long waybillNumber, float weight, int height, int width,
                            int length, int payment, int delivery_address, int shipment_address, String status,
                            String localization, int shipmentCode) {
        super(parcelNumber, waybillNumber, weight, height, width, length, payment, delivery_address,
                shipment_address, status, localization);
        this.shipmentCode = shipmentCode;
    }
    public int getShipmentCode() {
        return shipmentCode;
    }

}

