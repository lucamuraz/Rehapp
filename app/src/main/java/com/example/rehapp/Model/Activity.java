package com.example.rehapp.Model;

import androidx.annotation.NonNull;

public class Activity {



    private String categoria;
    private String tipologia;
    private String titolo;
    private String data;
    private String durata;

    public Activity(String tipologia, String titolo, String data, String durata, String categoria) {
        this.tipologia = tipologia;
        this.titolo = titolo;
        this.data = data;
        this.durata = durata;
        this.categoria=categoria;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getData() {
        return data;
    }

    public String getDurata() {
        return durata;
    }

    public String getDescription(){
        return categoria +" - "+ titolo;
    }

    public String getInfo() {
        String res = "";
        res += tipologia + " - " + data + " - ";
        if (!durata.equals("")) {
            res += durata.substring(0, 2) + " h " + durata.substring(3) + " m";
        }
        return res;
    }

    public String getCategoria() { return categoria; }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(String data) {
        this.data = data;
    }

    @NonNull
    public String toString(){
        return titolo+", "+durata+", "+tipologia+", "+categoria+", "+data;
    }
}
