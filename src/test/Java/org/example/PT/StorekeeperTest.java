package org.example.PT;

import org.example.PG.Parcel;
import org.example.PG.ShipmentFactory;
import org.example.PostgreSQL.ManageDataBase;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class StorekeeperTest {
    ManageDataBase testBase = new ManageDataBase();
    public static String databaseName = "firma_kurierska_test";
    Storekeeper storekeeper = new Storekeeper();
    @Test()
    public void reportTheDamageAndAcceptParcelTest() {
        try {
            testBase.connectToPostgreSQL();
            testBase.createDataBase(databaseName);
            testBase.connectToDataBase(databaseName);
            ShipmentFactory factory = new ShipmentFactory();
            Parcel myParcel = factory.createParcel(1,1,10.f,20,20,
                    20,1,1,1,"Shipped", "Sender");
            storekeeper.reportTheDamage(1);
            storekeeper.acceptParcel(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            testBase.connectToDataBase();
            testBase.dropDataBase(databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}