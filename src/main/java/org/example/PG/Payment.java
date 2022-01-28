package org.example.PG;

public class Payment {
    private double price;
    private int id = -1;
    private String method = "Bank transfer";
    private String status = "Not Paid";
    private boolean paid = false;

    public Payment() {}
    public Payment(double price) {
        this.price = price;
    }
    public Payment(int id, double price, String status) {
        this.id = id;
        this.price = price;
        this.status = status;
    }

    public double getPrice() {return price;}
    public String getMethod() {return method;}

    public boolean isPaid() {
        return paid;
    }

    public String getStatus() {
        return status;
    }
}
