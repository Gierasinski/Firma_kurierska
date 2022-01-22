package org.example.PT;

import org.example.PG.Payment;
import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Accountant extends Employee{
    ManageDataBase base =  new  ManageDataBase();
    Payment payment;

    public Accountant(int id, int pesel, int salary, int phoneNumber, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber, name, surname, position, dateOfEmployment);
    }


    public void getFacture(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        simpleDateFormat.format(date);

        payment.getMethod();
        payment.getPrice();
    }

    public void givePremium(int id, int premium){
        int salaryFromDB = 0;
        try {
            salaryFromDB = base.searchEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        salaryFromDB += premium;
        try {
            base.updateSalary(salaryFromDB,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void payWages(int id){
        try {
            int salary = base.searchEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
