package org.example.AS;

public class Branch {
    private int id;
    private int idAdress;
    private String name;
    private int code;

    public Branch(int id, int idAdress, String name, int code) {
        this.id = id;
        this.idAdress = idAdress;
        this.name = name;
        this.code = code;
    }

    public int getId() {return id;}

    public void receivetheparcel() {
    }
    public void sendtheparcel() {
    }
}

