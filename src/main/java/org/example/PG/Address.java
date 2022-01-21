package org.example.PG;

public class Address {
    private int id;
    private String  city, street;
    private String postcode;

    public Address(int id, String city, String street, String postcode) {
        this.id = id;
        this.city = city;
        this.street = street;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
