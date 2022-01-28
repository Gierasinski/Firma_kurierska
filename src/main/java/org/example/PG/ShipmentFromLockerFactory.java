package org.example.PG;

import java.util.Random;

public class ShipmentFromLockerFactory extends ShipmentFactory{
    private ParcelFromLocker myParcel;
    @Override
    public ParcelFromLocker createParcel(long parcelNumber, long waybillNumber, float weight, int height, int width, int length,
                                         int payment, int delivery_address, int shipment_address, String status, String localization) {

        return myParcel = new ParcelFromLocker(parcelNumber, waybillNumber, weight, height, width, length,
                payment, delivery_address, shipment_address, status, localization, generateShipmentCode());
    }
    private int generateShipmentCode(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }

}
