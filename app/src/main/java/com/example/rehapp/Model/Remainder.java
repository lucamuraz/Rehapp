package com.example.rehapp.Model;

import androidx.annotation.NonNull;

public class Remainder {

    private String title;
    private String date;
    private String hour;

    public Remainder(String title, String date, String hour) {
        this.title = title;
        this.date = date;
        this.hour = hour;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getHour(){ return hour;}
    public void setHour(String hour){ this.hour=hour; }


    public String getInfo(){
        return date+" - "+hour;
    }

    @NonNull
    public String toString(){
        return title+", "+date+", "+hour;
    }

}