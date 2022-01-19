package org.example.PG;

import java.util.Random;

public class ShipmentFromToLockerFactory extends ShipmentFactory{
    private ParcelFromToLocker myParcel;
    @Override
    public ParcelFromToLocker createParcel(int parcelNumber, int waybillNumber, float weight, int height, int width, int length,
                                           int payment, int delivery_address, int shipment_address, String status, String localization) {

        return myParcel = new ParcelFromToLocker(parcelNumber, waybillNumber, weight, height, width, length,
                payment, delivery_address, shipment_address, status, localization,generateShipmentCode(), generatePickupCode());
    }
    private int generateShipmentCode(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }
    private int generatePickupCode(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }

}
