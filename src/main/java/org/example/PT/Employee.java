package org.example.PT;

import org.example.AS.Branch;
import org.example.PG.Address;
import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class Employee {
    private int id,pesel,salary,phoneNumber;
    private String workerCode, name,surname, position;
    private Branch branch;
    private Address address;
    private Date dateOfEmployment;

    ManageDataBase base =  new  ManageDataBase();

    public Employee(int id, int pesel, int salary, int phoneNumber,String workerCode, String name, String surname,String position, Date dateOfEmployment){
        this.id = id;
        this.pesel = pesel;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.workerCode = workerCode;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getId() {return id;}
    public int getPesel() {return pesel;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getWorkerCode() {return workerCode;}
    public String getName() {return name;}
    public int getSalary() {return salary;}
    public String getSurname() {return surname;}
    public String getPosition() {return position;}
    public Date getDateOfEmployment() {return dateOfEmployment;}

    public void checkTheRoute(){

    }

    public int loginEmployee(String workerCode) throws SQLException {
        base.connectToDataBase();
        String code  = base.searchEmployeeWorkerCode(workerCode);

        if(code.startsWith("del")){
            return 1;
        }else  if(code.startsWith("sto")){
            return 2;
        }else if(code.startsWith("acc")){
            return 3;
        }
        return -1;
    }




    public void addEmployee(){
        int idBranch = branch.getId();
        int idAddress = address.getId();
        if(position=="Delivery"){
            workerCode = "del"+generateWorkerCodeNumber();
        }else if(position=="Accountant"){
            workerCode = "acc"+generateWorkerCodeNumber();
        }else if(position=="Storekeeper"){
            workerCode = "sto"+generateWorkerCodeNumber();
        }
        try {
            base.insertEmployee(workerCode, name,surname, phoneNumber, idAddress, pesel, position, salary, (java.sql.Date) dateOfEmployment, idBranch);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int generateWorkerCodeNumber(){
        Random rand = new Random();
        return rand.nextInt(10)+rand.nextInt(10)*10+rand.nextInt(10)*100+rand.nextInt(10)*1000;
    }
}
