package com.example.campus;

import android.os.Parcel;
import android.os.Parcelable;

public class Cerere implements Parcelable {
    String numeStudent, prenumeStudent, numeFacultate, cicluStudii, adresa, pozaBuletin, pozaSemnatura, caminDorit, an, telefon, status, idUser;

    public Cerere(String numeStudent, String prenumeStudent, String numeFacultate, String cicluStudii, String adresa, String pozaBuletin, String pozaSemnatura, String caminDorit, String an, String telefon, String status, String idUser) {
        this.numeStudent = numeStudent;
        this.prenumeStudent = prenumeStudent;
        this.numeFacultate = numeFacultate;
        this.cicluStudii = cicluStudii;
        this.adresa = adresa;
        this.pozaBuletin = pozaBuletin;
        this.pozaSemnatura = pozaSemnatura;
        this.caminDorit = caminDorit;
        this.an = an;
        this.telefon = telefon;
        this.status = status;
        this.idUser = idUser;
    }

    Cerere(){

    }

    protected Cerere(Parcel in) {
        numeStudent = in.readString();
        prenumeStudent = in.readString();
        numeFacultate = in.readString();
        cicluStudii = in.readString();
        adresa = in.readString();
        pozaBuletin = in.readString();
        pozaSemnatura = in.readString();
        caminDorit = in.readString();
        an = in.readString();
        telefon = in.readString();
        status = in.readString();
        idUser = in.readString();
    }

    public static final Creator<Cerere> CREATOR = new Creator<Cerere>() {
        @Override
        public Cerere createFromParcel(Parcel in) {
            return new Cerere(in);
        }

        @Override
        public Cerere[] newArray(int size) {
            return new Cerere[size];
        }
    };

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public String getPrenumeStudent() {
        return prenumeStudent;
    }

    public void setPrenumeStudent(String prenumeStudent) {
        this.prenumeStudent = prenumeStudent;
    }

    public String getNumeFacultate() {
        return numeFacultate;
    }

    public void setNumeFacultate(String numeFacultate) {
        this.numeFacultate = numeFacultate;
    }

    public String getCicluStudii() {
        return cicluStudii;
    }

    public void setCicluStudii(String cicluStudii) {
        this.cicluStudii = cicluStudii;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPozaBuletin() {
        return pozaBuletin;
    }

    public void setPozaBuletin(String pozaBuletin) {
        this.pozaBuletin = pozaBuletin;
    }

    public String getPozaSemnatura() {
        return pozaSemnatura;
    }

    public void setPozaSemnatura(String pozaSemnatura) {
        this.pozaSemnatura = pozaSemnatura;
    }

    public String getCaminDorit() {
        return caminDorit;
    }

    public void setCaminDorit(String caminDorit) {
        this.caminDorit = caminDorit;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(numeStudent);
        parcel.writeString(prenumeStudent);
        parcel.writeString(numeFacultate);
        parcel.writeString(cicluStudii);
        parcel.writeString(adresa);
        parcel.writeString(pozaBuletin);
        parcel.writeString(pozaSemnatura);
        parcel.writeString(caminDorit);
        parcel.writeString(an);
        parcel.writeString(telefon);
        parcel.writeString(status);
        parcel.writeString(idUser);
    }
}
