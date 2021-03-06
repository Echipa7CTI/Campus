package com.example.campus;

import android.os.Parcel;
import android.os.Parcelable;

public class Anunt implements Parcelable {
    String textAnunt, titluAnunt, uploadId, data;
    boolean isExpanded;

    public Anunt(String textAnunt, String titluAnunt, String uploadId, String data) {
        this.textAnunt = textAnunt;
        this.titluAnunt = titluAnunt;
        this.uploadId = uploadId;
        this.data = data;
    }

    public Anunt(){

    }

    protected Anunt(Parcel in) {
        textAnunt = in.readString();
        titluAnunt = in.readString();
        uploadId = in.readString();
        data = in.readString();
    }

    public static final Creator<Anunt> CREATOR = new Creator<Anunt>() {
        @Override
        public Anunt createFromParcel(Parcel in) {
            return new Anunt(in);
        }

        @Override
        public Anunt[] newArray(int size) {
            return new Anunt[size];
        }
    };

    public String getTextAnunt() {
        return textAnunt;
    }

    public void setTextAnunt(String textAnunt) {
        this.textAnunt = textAnunt;
    }

    public String getTitluAnunt() {
        return titluAnunt;
    }

    public void setTitluAnunt(String titluAnunt) {
        this.titluAnunt = titluAnunt;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(textAnunt);
        parcel.writeString(titluAnunt);
        parcel.writeString(uploadId);
        parcel.writeString(data);
    }
}
