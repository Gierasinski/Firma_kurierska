package org.example.PT;

import org.example.AS.Branch;
import org.example.PG.Address;
import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;
import java.util.Date;

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
    public Employee(){}

    public int getId() {return id;}
    public int getPesel() {return pesel;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getWorkerCode() {return workerCode;}
    public String getName() {return name;}
    public int getSalary() {return salary;}
    public String getSurname() {return surname;}
    public String getPosition() {return position;}
    public Date getDateOfEmployment() {return dateOfEmployment;}

    public void checkTheRoute() throws SQLException {
        base.connectToDataBase();
        base.searchRoute(12);

    }

    public int loginEmployee(String workerCode) throws SQLException {
        base.connectToDataBase();
        Employee employeeWorker = new Employee();
        employeeWorker = base.searchEmployeeWorkerCode(workerCode);
        if(employeeWorker.id > 0){
            if(employeeWorker.position.startsWith("del")){
                return 1;
            }else  if(employeeWorker.position.startsWith("sto")){
                return 2;
            }else if(employeeWorker.position.startsWith("acc")){
                return 3;
            }
        }

        return -1;
    }









}
