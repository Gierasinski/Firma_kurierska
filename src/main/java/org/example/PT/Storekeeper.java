package org.example.PT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Storekeeper extends Employee {
    Status status;

    public Storekeeper(int id, int pesel, int salary, int phoneNumber, String name, String surname, String position, Date dateOfEmployment) {
        super(id, pesel, salary, phoneNumber, name, surname, position, dateOfEmployment);
    }

    public void reportTheDamage() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        simpleDateFormat.format(date);
        status.getSa8();


    }
    public void acceptParcel(){
        status.getSa5();
    }

    public void transportPackage() {

    }
}
