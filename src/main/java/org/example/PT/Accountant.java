package org.example.PT;

import org.example.PG.Payment;
import org.example.PostgreSQL.ManageDataBase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Accountant extends Employee{
    ManageDataBase base =  new  ManageDataBase();
    Payment payment = new Payment(10);

    public Accountant(int id, int pesel, int salary, int phoneNumber,String workerCode, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber,workerCode, name, surname, position, dateOfEmployment);
    }
    public Accountant() {}


    public void getFacture(String name, String surname, String city, String road, int numberHause) throws FileNotFoundException {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        PrintWriter write = new PrintWriter("facture.txt");
        write.println("Faktura");
        write.println("Nazwa firmy: Firma Kurierska PSP Spolka Zoo");
        write.println("data wystawienia faktury: "+simpleDateFormat.format(date));
        write.println("Dane odbiorcy: ");
        write.println("Imie: "+ name);
        write.println("Nazwisko: "+ surname);
        write.println("Miasto: "+ city);
        write.println("Ulica: "+ road);
        write.println("Numer Domu: "+ numberHause);
        write.println("Metoda platnosc: "+payment.getMethod());
        write.println("Kwota: "+payment.getPrice());

        write.close();
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

    public void payWages(String id) throws SQLException {
        base.connectToDataBase();
        try {
            int salary = base.searchEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addEmployee(String name,String surname,String city,String road, String numberHause, int phoneNumber,int pesel, String position,int salary, Date dateOfEmployment) {
          String workerCode = ""+generateWorkerCodeNumber();
        try {
            int idAddress;
            base.connectToDataBase();
            idAddress = base.insertAdres(city, road+numberHause, "23-650");

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
