package org.example.PG;

import java.util.Random;

public class ShipmentToLockerFactory extends ShipmentFactory{
    private ParcelToLocker myParcel;
    @Override
    public ParcelToLocker createParcel(long parcelNumber, long waybillNumber, float weight, int height, int width, int length,
                                       int payment, int delivery_address, int shipment_address, String status, String localization) {

        return myParcel = new ParcelToLocker(parcelNumber, waybillNumber, weight, height, width, length,
                payment, delivery_address, shipment_address, status, localization, generatePickupCode());
    }
    private int generatePickupCode(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }

}