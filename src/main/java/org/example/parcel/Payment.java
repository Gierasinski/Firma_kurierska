package org.example.parcel;

public class Payment {
    private double price;
    private int id = -1;
    private String method = "Bank transfer";
    private String status = "Not Paid";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

    }

    public int getId() {
        return id;
    }
}
