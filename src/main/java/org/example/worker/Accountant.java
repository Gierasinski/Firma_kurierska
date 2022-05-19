package org.example.worker;

import org.example.client.ShipperInfo;
import org.example.global.Address;
import org.example.parcel.Parcel;
import org.example.parcel.Payment;
import org.example.PostgreSQL.ManageDataBase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.parcel.RoutePlan;

public class Accountant extends Employee{
    ManageDataBase base =  new  ManageDataBase();
    Payment payment = new Payment(10);

    public Accountant(int id, int pesel, int salary, int phoneNumber,String workerCode, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber,workerCode, name, surname, position, dateOfEmployment);
    }
    public Accountant() {}


    public boolean getFacture(long p) throws FileNotFoundException, SQLException {
        base.connectToDataBase();
        Parcel parcel = base.getParcelInfo(p);
        ShipperInfo shipper = base.getShipperInfo(p);
        Payment payment = base.getPaymentInfo(parcel.getPayment());
        payment.setStatus("Invoice issued");
        base.updatePayment(payment.getId(), payment.getStatus());
        parcel.createInvoice(shipper, payment);

        return true;
    }
    public boolean getFacture(long p, ManageDataBase manage) throws FileNotFoundException, SQLException {
        Parcel parcel = manage.getParcelInfo(p);
        ShipperInfo shipper = manage.getShipperInfo(p);
        Payment payment = manage.getPaymentInfo(parcel.getPayment());
        payment.setStatus("Invoice issued");
        manage.updatePayment(payment.getId(), payment.getStatus());
        parcel.createInvoice(shipper, payment);

        return true;
    }
    public void bookPayment(long p) throws SQLException {
        base.connectToDataBase();
        Parcel parcel = base.getParcelInfo(p);
        ShipperInfo shipper = base.getShipperInfo(p);
        Payment payment = base.getPaymentInfo(parcel.getPayment());
        payment.setStatus("Paid");
        base.updatePayment(payment.getId(), payment.getStatus());
    }

    public void assignCouriers() throws SQLException {
        base.connectToDataBase();
        ArrayList<RoutePlan> routes =  base.getNotAssignedRoutes();
        ArrayList<Delivery> couriers = base.getCouriers();

        for (int i = 0; i < routes.size(); i++){
            routes.get(i).setCourier(couriers.get((int)Math.random() * couriers.size()).getWorkerCode());
            base.updateRoutePlan(routes.get(i).getParcelNumber(), routes.get(i).getIdAddressA(),routes.get(i).getIdAddressB(),routes.get(i).getCourier(), routes.get(i).getStage());
        }
    }


    public void givePremium(String workerCode, int premium){
        try {
            int salaryFromDB = 0;
            base.connectToDataBase();
            salaryFromDB = base.searchEmployee(workerCode);
            salaryFromDB += premium;
            base.updateSalary(salaryFromDB,workerCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addEmployee(String name,String surname,String city,String road, String numberHause, int phoneNumber,int pesel, String position,int salary, Date dateOfEmployment) {
          String workerCode = ""+generateWorkerCodeNumber();
        try {
            int idAddress;
            base.connectToDataBase();
            idAddress = base.insertAdres(city, road,numberHause,null, "23-650");

            base.insertEmployee(workerCode, name,surname, phoneNumber, idAddress, pesel, position, salary, (java.sql.Date) dateOfEmployment, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int generateWorkerCodeNumber(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }


}
