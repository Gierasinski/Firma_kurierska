package org.example.PT;

import org.example.AS.Branch;
import org.example.PG.Address;
import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;

public class Employee {
    private int id,pesel,salary,phoneNumber;
    private String name,surname, position;
    private Branch branch;
    private Address address;
    private Date dateOfEmployment;

    ManageDataBase base =  new  ManageDataBase();

    public Employee(int id, int pesel, int salary, int phoneNumber, String name, String surname,String position, Date dateOfEmployment){
        this.id = id;
        this.pesel = pesel;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.dateOfEmployment = dateOfEmployment;
    }
    public int getId() {return id;}
    public int getPesel() {return pesel;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getName() {return name;}
    public int getSalary() {return salary;}
    public String getSurname() {return surname;}
    public String getPosition() {return position;}
    public Date getDateOfEmployment() {return dateOfEmployment;}

    public void checkTheRoute(){

    }


    public void addEmployee(){
        int idBranch = branch.getId();
        int idAddress = address.getId();
        try {
            base.insertEmployee(id,name,surname, phoneNumber, idAddress, pesel, position, salary, (java.sql.Date) dateOfEmployment, idBranch);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
