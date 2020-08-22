package com.example.campus;

public class Mancare {
    String numeMancare, tipMancare, pretMancare;

    public Mancare(String numeMancare, String tipMancare, String pretMancare) {
        this.numeMancare = numeMancare;
        this.tipMancare = tipMancare;
        this.pretMancare = pretMancare;
    }

    public Mancare() {
    }

    public String getNumeMancare() {
        return numeMancare;
    }

    public void setNumeMancare(String numeMancare) {
        this.numeMancare = numeMancare;
    }

    public String getTipMancare() {
        return tipMancare;
    }

    public void setTipMancare(String tipMancare) {
        this.tipMancare = tipMancare;
    }

    public String getPretMancare() {
        return pretMancare;
    }

    public void setPretMancare(String pretMancare) {
        this.pretMancare = pretMancare;
    }
}
