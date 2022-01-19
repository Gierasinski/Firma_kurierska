package org.example.PostgeSQLTest;

import org.example.PG.*;
import org.junit.jupiter.api.*;



public class ParcelCreateTest{


    @Test()
    public void shipmentFactory_test (){
        ShipmentFactory factory = new ShipmentFactory();
        Parcel myParcel = factory.createParcel(1,1,10.f,20,20,
                20,1,1,1,"Shipped", "Sender");
    }
    @Test()
    public void shipmentFromToParcelFactory_test (){
        ShipmentFromToLockerFactory factory = new ShipmentFromToLockerFactory();
        ParcelFromToLocker myParcel = factory.createParcel(1,1,10.f,20,20,
                20,1,1,1,"Shipped", "Sender");
    }
}