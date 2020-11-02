package com.example.rehapp.Model;

public class MonthReport {

    private String month;
    private String year;
    private int nbActivities;
    private int timeActivities;

    public MonthReport(String month, String year, int nbActivities, int timeActivities) {
        this.month = month;
        this.year = year;
        this.nbActivities = nbActivities;
        this.timeActivities = timeActivities;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getTitle(){
        return month +" "+ year;
    }

    public String getNbActivities() {
        return "Attività eseguite:  "+nbActivities;
    }

    public String getTimeActivities() {
        int min=timeActivities%60;
        int hour=timeActivities/60;
        return "Tempo totale attività:  "+hour+" ore "+min+" min.";
    }

    public void setNbActivities(int nb){
        nbActivities+=nb;
    }

    public void setTimeActivities(int time){
        timeActivities+=time;
    }
}
