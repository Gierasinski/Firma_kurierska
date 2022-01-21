package org.example.PT;

public class Status {
    String sa1 = "Odebrana przez kuriera";
    String sa2 = "Wyslana przez nadawce";
    String sa3 = "Odebrana przez odbiorce";
    String sa4 = "Gotowa do odbioru";
    String sa5 = "Przyjeta w oddziale";
    String sa6 = "Dostarczona do paczkomatu";
    String sa7 = "Dostarczona do odbiorcy";
    String sa8 = "Uszkodzona";


    public Status(String sa1, String sa2, String sa3, String sa4, String sa5, String sa6, String sa7, String sa8) {
        this.sa1 = sa1;
        this.sa2 = sa2;
        this.sa3 = sa3;
        this.sa4 = sa4;
        this.sa5 = sa5;
        this.sa6 = sa6;
        this.sa7 = sa7;
        this.sa8 = sa8;

    }

    public String getSa1() {return sa1;}
    public String getSa2() {return sa2;}
    public String getSa3() {return sa3;}
    public String getSa4() {return sa4;}
    public String getSa5() {return sa5;}
    public String getSa6() {return sa6;}
    public String getSa7() {return sa7;}
    public String getSa8() {return sa8;}
}
