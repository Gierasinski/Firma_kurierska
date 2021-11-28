package org.example.PG;

public class Address {

    private String voivodeship, city, street;
    private int buildingNumber;

    public Address(String voivodeship, String city, String street, int buildingNumber) {
        this.voivodeship = voivodeship;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
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

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
