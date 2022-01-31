package org.example.worker;



public final class EmployeeHolder {
    private Employee employee;
    private final static EmployeeHolder INSTANCE = new EmployeeHolder();

    private EmployeeHolder() {}

    public static EmployeeHolder getInstance() {
        return INSTANCE;
    }

    public void setEmployee (Employee c){
        this.employee = c;
    }
    public Employee getEmployee() {
        return this.employee;
    }


}
