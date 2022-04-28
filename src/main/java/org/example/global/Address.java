package org.example.global;

public class Address {
    private int id;
    private String  city, street, number, local;
    private String postcode;
    public Address() {}
    public Address(int id, String city, String street, String number, String local, String postcode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.local = local;
        this.postcode = postcode;
    }

    public int getId() {return id;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {this.city = city;}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street= street;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String street) {
        this.number= number;
    }
    public String getLocal() {
        return local;
    }

    public void setLocal(String street) {
        this.local= local;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
