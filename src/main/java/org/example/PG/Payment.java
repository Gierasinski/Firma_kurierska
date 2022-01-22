package org.example.PG;

public class Payment {
    private float price;
    private String method;
    private boolean paid = false;

    public Payment(float price, String method) {
        this.price = price;
        this.method = method;
    }

    public float getPrice() {return price;}
    public String getMethod() {return method;}

    public void pay(){}
}
