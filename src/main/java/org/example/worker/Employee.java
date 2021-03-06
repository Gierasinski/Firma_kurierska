package org.example.worker;

import org.example.global.Branch;
import org.example.global.Address;
import org.example.PostgreSQL.ManageDataBase;
import org.example.parcel.RoutePlan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Employee {
    private int id,pesel,salary,phoneNumber;
    private String workerCode, name,surname, position;
    private Branch branch;
    private Address address;
    private Date dateOfEmployment;
    Employee employeeWorker;

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

    public void checkTheRoute(long parcelNumber) {
        ArrayList<RoutePlan> plan = new ArrayList<RoutePlan>();
        try {
            base.connectToDataBase();
            plan  = base.searchRouteToParcelNumber(parcelNumber);
            System.out.println("TRASA PRZESYLKI:");
            for(RoutePlan  x:plan) {
                String napis;
                napis =  base.selectAdresToRouteA(x.getIdAddressA());
                System.out.print("From: " + napis );
                napis =  base.selectAdresToRouteB(x.getIdAddressB());
                System.out.println(" TO: " + napis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int loginEmployee(String workerCode) throws SQLException {
        base.connectToDataBase();
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

    public Employee getEmployeeWorker() {
        return employeeWorker;
    }
}
