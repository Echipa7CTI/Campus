package com.example.campus;

public class Mancare {
    String nume, tip;
    float pret;

    public Mancare(String nume, String tip, float pret) {
        this.nume = nume;
        this.tip = tip;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
