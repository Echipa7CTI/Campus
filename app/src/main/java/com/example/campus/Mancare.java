package com.example.campus;

import android.os.Parcel;
import android.os.Parcelable;

public class Mancare implements Parcelable {
    String numeMancare, tipMancare, pretMancare, idMancare;

    public Mancare(String numeMancare, String tipMancare, String pretMancare, String idMancare) {
        this.numeMancare = numeMancare;
        this.tipMancare = tipMancare;
        this.pretMancare = pretMancare;
        this.idMancare = idMancare;
    }

    public Mancare() {
    }

    protected Mancare(Parcel in) {
        numeMancare = in.readString();
        tipMancare = in.readString();
        pretMancare = in.readString();
        idMancare = in.readString();
    }

    public static final Creator<Mancare> CREATOR = new Creator<Mancare>() {
        @Override
        public Mancare createFromParcel(Parcel in) {
            return new Mancare(in);
        }

        @Override
        public Mancare[] newArray(int size) {
            return new Mancare[size];
        }
    };

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

    public String getIdMancare() {
        return idMancare;
    }

    public void setIdMancare(String idMancare) {
        this.idMancare = idMancare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(numeMancare);
        parcel.writeString(tipMancare);
        parcel.writeString(pretMancare);
        parcel.writeString(idMancare);
    }
}
