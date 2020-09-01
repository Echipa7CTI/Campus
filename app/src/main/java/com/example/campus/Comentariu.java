package com.example.campus;

public class Comentariu {

    String mailComentariu, textComentariu, idComentariu;

    public Comentariu(String mailComentariu, String textComentariu, String idComentariu) {
        this.mailComentariu = mailComentariu;
        this.textComentariu = textComentariu;
        this.idComentariu = idComentariu;
    }

    public Comentariu() {
    }

    public String getMailComentariu() {
        return mailComentariu;
    }

    public void setMailComentariu(String mailComentariu) {
        this.mailComentariu = mailComentariu;
    }

    public String getTextComentariu() {
        return textComentariu;
    }

    public void setTextComentariu(String textComentariu) {
        this.textComentariu = textComentariu;
    }

    public String getIdComentariu() {
        return idComentariu;
    }

    public void setIdComentariu(String idComentariu) {
        this.idComentariu = idComentariu;
    }
}
