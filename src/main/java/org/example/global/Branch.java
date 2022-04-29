package org.example.global;

public class Branch {
    private int id;
    private String name;
    private String code;
    private Address address;


    public Branch(int id, String name, String code, Address address) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Address getAddress() {
        return address;
    }
}

