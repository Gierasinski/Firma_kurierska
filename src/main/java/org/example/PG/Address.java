package org.example.PG;

public class Address {

    private String  city, street;
    private String postcode;

    public Address(String city, String street, String postcode) {
        this.city = city;
        this.street = street;
        this.postcode = postcode;
    }

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
