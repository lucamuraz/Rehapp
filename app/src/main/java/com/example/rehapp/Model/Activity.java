package com.example.rehapp.Model;

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

    public String getInfo(){ return tipologia + " - "+data +" - "+ durata+" min."; }

    public String getCategoria() { return categoria; }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDurata(String durata) { this.durata = durata; }

    public void setCategoria(String categoria) { this.categoria=categoria; }

    public String toString(){
        return titolo+", "+durata+", "+tipologia+", "+categoria+", "+data;
    }
}
