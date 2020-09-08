package com.example.campus;

import android.os.Parcel;
import android.os.Parcelable;

public class Plangere implements Parcelable {
    String titlu, descriere, uploadId, email, userId, status, raspuns;

    public Plangere(String titlu, String descriere, String uploadId, String email, String userId, String status, String raspuns) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.uploadId = uploadId;
        this.email = email;
        this.userId = userId;
        this.status = status;
        this.raspuns = raspuns;
    }

    Plangere(){
    }

    protected Plangere(Parcel in) {
        titlu = in.readString();
        descriere = in.readString();
        uploadId = in.readString();
        email = in.readString();
        userId = in.readString();
        status = in.readString();
        raspuns = in.readString();
    }

    public static final Creator<Plangere> CREATOR = new Creator<Plangere>() {
        @Override
        public Plangere createFromParcel(Parcel in) {
            return new Plangere(in);
        }

        @Override
        public Plangere[] newArray(int size) {
            return new Plangere[size];
        }
    };

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRaspuns() {
        return raspuns;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titlu);
        parcel.writeString(descriere);
        parcel.writeString(uploadId);
        parcel.writeString(email);
        parcel.writeString(userId);
        parcel.writeString(status);
        parcel.writeString(raspuns);

    }
}
