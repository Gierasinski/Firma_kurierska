package org.example.client;

import org.example.parcel.Route;
import org.example.PostgreSQL.ManageDataBase;
import org.example.global.Address;
import org.example.parcel.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

public class Client {
    private ManageDataBase manageDataBase = new ManageDataBase();
    private Account account;
    private int originAddress = -1;
    private int destinationAddress = -1;

    public long shipParcel(float weight, int height, int width, int length, int payment) throws SQLException {
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        Parcel parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
        ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
        , parcel.getStatus(), parcel.getLocalization(), 0,0, account.getId());
        return parcel.getParcelNumber();
    }
    public long shipParcel(float weight, int height, int width, int length, int payment,
                           ManageDataBase manage) throws SQLException {
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        Parcel parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manage.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), 0,0, account.getId());
        return parcel.getParcelNumber();
    }
    public long shipParcelToLocker(float weight, int height, int width, int length, int payment) throws SQLException {
        ShipmentToLockerFactory shipmentFactory = new ShipmentToLockerFactory();
        ParcelToLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), 0,parcel.getPickupCode(), account.getId());
        return parcel.getParcelNumber();
    }

    public long shipParcelToLocker(float weight, int height, int width, int length, int payment,
                                   ManageDataBase manage) throws SQLException {
        ShipmentToLockerFactory shipmentFactory = new ShipmentToLockerFactory();
        ParcelToLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");


        manage.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), 0,parcel.getPickupCode(), account.getId());
        return parcel.getParcelNumber();
    }
    public long shipParcelFromLocker(float weight, int height, int width, int length, int payment) throws SQLException {
        ShipmentFromLockerFactory shipmentFactory = new ShipmentFromLockerFactory();
        ParcelFromLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), parcel.getShipmentCode(),0, account.getId());
        return parcel.getParcelNumber();
    }

    public long shipParcelFromLocker(float weight, int height, int width, int length, int payment,ManageDataBase manage) throws SQLException {
        ShipmentFromLockerFactory shipmentFactory = new ShipmentFromLockerFactory();
        ParcelFromLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");


        manage.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), parcel.getShipmentCode(),0, account.getId());
        return parcel.getParcelNumber();
    }
    public long shipParcelFromToLocker(float weight, int height, int width, int length, int payment) throws SQLException {
        ShipmentFromToLockerFactory shipmentFactory = new ShipmentFromToLockerFactory();
        ParcelFromToLocker parcel = shipmentFactory.createParcel( generateParcelNumber(), generateParcelNumber(),weight, height, width, length, payment,
                destinationAddress, originAddress, "Shipped", "Shipper");

        manageDataBase.connectToDataBase();
        manageDataBase.insertParcel(parcel.getParcelNumber(),parcel.getWaybillNumber(), parcel.getWeight(), parcel.getHeight()
                ,parcel.getWidth(), parcel.getLength(), parcel.getPayment(), parcel.getDelivery_address(), parcel.getShipment_address()
                , parcel.getStatus(), parcel.getLocalization(), parcel.getShipmentCode(),parcel.getPickupCode(), account.getId());
        return parcel.getParcelNumber();
    }

    public boolean login(String login, String password) throws SQLException {
        manageDataBase.connectToDataBase();
        account = manageDataBase.loginClients(login,password);

       if(account.getIsNull()){
           System.out.println("Login failed");
           return false;
       }else{
           System.out.println("Login success");
           return true;
       }
   }
    public boolean login(String login, String password, ManageDataBase manage) throws SQLException {
        account = manage.loginClients(login,password);

        if(account.getIsNull()){
            System.out.println("Login failed");
            return false;
        }else{
            System.out.println("Login success");
            return true;
        }
    }
    public Long[] getParcelsID() throws SQLException {
        manageDataBase.connectToDataBase();
        Long[] array = manageDataBase.getParcels(account.getId());
        return array;
    }
    public Parcel getParcelInfo(long ID) throws SQLException {
        manageDataBase.connectToDataBase();
        Parcel parcel = manageDataBase.getParcelInfo(ID);
        return parcel;
    }
    public Payment getPaymentInfo(int ID) throws SQLException {
        manageDataBase.connectToDataBase();
        Payment payment = manageDataBase.getPaymentInfo(ID);
        return payment;
    }
    public Address getAddressInfo(int ID) throws SQLException {
        manageDataBase.connectToDataBase();
        Address address = manageDataBase.getAddressInfo(ID);
        return address;
    }
    public boolean register(String name, String surname, String phoneNumber, String email, String login, String password) throws SQLException {
        manageDataBase.connectToDataBase();
        long id = manageDataBase.insertClient(name, surname, phoneNumber, email, login, password);

        if(id < 0){
            return false;
        }else{
            return true;
        }
    }
    public boolean update(String name, String surname, String phoneNumber, String email, String street, String city, String post_code) throws SQLException {
        manageDataBase.connectToDataBase();
        long id = manageDataBase.updateClient(account.getId(), account.getAddress().getId(), name,surname,phoneNumber,email,street,city,post_code);

        if(id < 0){
            return false;
        }else{
            return true;
        }
    }
    public boolean updatePassword(String password) throws SQLException {
        manageDataBase.connectToDataBase();
        long id = manageDataBase.updateClientPassword(account.getId(), password);

        if(id < 0){
            return false;
        }else{
            return true;
        }
    }
   public long generateParcelNumber(){
       Calendar calendar= Calendar.getInstance();
       long month = (calendar.get(Calendar.MONTH) +1)* 100000;
       long day = calendar.get(Calendar.DAY_OF_MONTH) *1000000;
       long year = (calendar.get(Calendar.YEAR)-2000)*1000;
       Random rand = new Random();
       long firstTen = account.getId()*1000000000*10;

       return firstTen + day*100 + month*10 + year + rand.nextInt(999);
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

    public int createPayment(double price) throws SQLException {
        Payment payment = new Payment(price);

        manageDataBase.connectToDataBase();
        int id = manageDataBase.insertPayment(payment.getPrice(), payment.getStatus());
        if(id == -1){
            System.out.println("Something went wrong with setting up Payment");
        }
        return id;
    }

    public int getOriginAddress() {
        return originAddress;
    }

    public int getDestinationAddress() {
        return destinationAddress;
    }


    public Account getAccount() {
        return account;
    }
}
