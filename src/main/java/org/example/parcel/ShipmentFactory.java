package org.example.parcel;


interface ParcelFactory{
    public Parcel createParcel(long parcelNumber, long waybillNumber, float weight, int height,
                               int width, int length, int payment, int delivery_address, int shipment_address,
                               String status, String localization);
}

public class ShipmentFactory implements ParcelFactory{
    private Parcel myParcel;
    @Override
    public Parcel createParcel(long parcelNumber, long waybillNumber, float weight, int height, int width, int length,
                               int payment, int delivery_address, int shipment_address, String status, String localization) {
        return myParcel = new Parcel(parcelNumber, waybillNumber, weight, height, width, length,
                payment, delivery_address, shipment_address, status, localization);
    }
}
