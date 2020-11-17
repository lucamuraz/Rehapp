package com.example.rehapp.Model;

import androidx.annotation.NonNull;

public class Activity {


    private String id;
    private String categoria;
    private String tipologia;
    private String titolo;
    private String data;
    private String durata;

    public Activity(String id, String tipologia, String titolo, String data, String durata, String categoria) {
        this.id=id;
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

    public String getId(){ return id; }

    public void setId(String id){ this.id=id; }

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

    public void setCategoria(String categoria){ this.categoria=categoria; }

    public void setTipologia(String tipologia){ this.tipologia=tipologia; }

    public void setDurata(String durata){ this.durata=durata; }

    @NonNull
    public String toString(){
        return id+", "+titolo+", "+durata+", "+tipologia+", "+categoria+", "+data;
    }
}
