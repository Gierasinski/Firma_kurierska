package org.example.PG;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Client {
    private ManageDataBase manageDataBase = new ManageDataBase();
    private Account account;
    private List<Parcel> parcels;
    private int originAddress = -1;
    private int destinationAddress = -1;

    public void addParcel(Parcel parcel){
        this.parcels.add(parcel);
    }
    public void shipParcel(float weight, int height, int width, int length, int payment,
                           int delivery_address, int shipment_address) throws SQLException {
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        Parcel parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
        delivery_address, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
        ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
        , parcel.getStatus(), parcel.getLocalization(), 0,0, account.getId());
    }
    public void shipParcel(float weight, int height, int width, int length, int payment,
                           ManageDataBase manage) throws SQLException {
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        Parcel parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manage.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), 0,0, account.getId());
    }
    public void shipParcelToLocker(float weight, int height, int width, int length, int payment,
                           int recive_code) throws SQLException {
        ShipmentToLockerFactory shipmentFactory = new ShipmentToLockerFactory();
        ParcelToLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), 0,recive_code, account.getId());
    }
    public void shipParcelFromLocker(float weight, int height, int width, int length, int payment,
                                    int ship_code) throws SQLException {
        ShipmentFromLockerFactory shipmentFactory = new ShipmentFromLockerFactory();
        ParcelFromLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), ship_code,0, account.getId());
    }
    public void shipParcelFromToLocker(float weight, int height, int width, int length, int payment,
                                     int ship_code, int recive_code) throws SQLException {
        ShipmentFromToLockerFactory shipmentFactory = new ShipmentFromToLockerFactory();
        ParcelFromToLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), ship_code,recive_code, account.getId());
    }

    public boolean login(String login, String password) throws SQLException {
        manageDataBase.connectToDataBase();
        account = manageDataBase.loginClients(login,password);

       if(account.getIsNull()){
           return false;
       }else{
           return true;
       }
   }
    public boolean register(String name, String surname, String phoneNumber, String email, String login, String password) throws SQLException {
        manageDataBase.connectToDataBase();
        int id = manageDataBase.insertClient(name, surname, phoneNumber, email, login, password);

        if(id < 0){
            return false;
        }else{
            return true;
        }
    }
   public int generateParcelNumber(){
       Calendar calendar= Calendar.getInstance();
       int month = calendar.get(Calendar.MONTH) + 1;
       int day = calendar.get(Calendar.DAY_OF_MONTH);
       Random rand = new Random();
       return day+month*100+account.getId()*10000+rand.nextInt(10)*10000000;
   }

    public void setOriginAddress(String city, String street, String postcode, ManageDataBase manage) throws SQLException {
        originAddress = manage.insertAdres(city, street, postcode);
        if(originAddress == -1){
            System.out.println("Something went wrong with setting up Origin Address");
        }
    }

    public void setDestinationAddress(String city, String street, String postcode, ManageDataBase manage) throws SQLException {
        destinationAddress = manage.insertAdres(city, street, postcode);
        if(destinationAddress == -1){
            System.out.println("Something went wrong with setting up Destination Address");
        }
    }
    public void setOriginAddress(String city, String street, String postcode) throws SQLException {
        manageDataBase.connectToDataBase();
        originAddress = manageDataBase.insertAdres(city, street, postcode);
        if(originAddress == -1){
            System.out.println("Something went wrong with setting up Origin Address");
        }
    }

    public void setDestinationAddress(String city, String street, String postcode) throws SQLException {
        manageDataBase.connectToDataBase();
        destinationAddress = manageDataBase.insertAdres(city, street, postcode);
        if(destinationAddress == -1){
            System.out.println("Something went wrong with setting up Destination Address");
        }
    }

    public int getOriginAddress() {
        return originAddress;
    }

    public int getDestinationAddress() {
        return destinationAddress;
    }
}
