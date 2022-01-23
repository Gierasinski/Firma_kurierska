package org.example.PT;



public final class EmployeeHolder {
    private Employee employee;
    private final static EmployeeHolder INSTANCE = new EmployeeHolder();

    private EmployeeHolder() {}

    public static EmployeeHolder getInstance() {
        return INSTANCE;
    }
    /*
    public void setEmployee (Employee c){
        this.Employee = c;
    }
    public Employee getEmployee() {
        return this.Employee;
    }
    */

}
