package com.example.rehapp;

import android.content.Context;
import android.util.Log;

import com.example.rehapp.Model.Activity;
import com.example.rehapp.Model.MonthReport;
import com.example.rehapp.Model.Remainder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppManager {
    private static AppManager singleInstance;
    private List<Activity> activityList;
    private List<Remainder> remainderList;
    private String lastId;
    private Activity activity;
    BottomNavigationView bottomNavigationView;

    public static AppManager getInstance() {
        if (singleInstance == null) {
            singleInstance = new AppManager();
        }
        return singleInstance;
    }

    public void setActivityList(List<Activity> list){
        activityList=list;
        String id="00"+(list.size()+1);
        setLastId(id);
    }

    public void setActivity(Activity activity){
        this.activity=activity;
        Log.i("Info", "Attività: "+activity);
    }

    public String getTotalAct(){
        String res="";
        res+=activityList.size();
        return res;
    }

    public Activity getActivity(){ return this.activity; }

    public List<Activity> getActivityListForDate(String date){
        List<Activity> res=new ArrayList<>();
        for (Activity act: activityList) {
            if(act.getData().equals(date)){
                res.add(act);
            }
        }
        return res;
    }

    public void addOnActivityList(Activity act, Context context){
        activityList.add(act);
        writeToFile(activityList, context);
    }

    public void addOnRemainderList(Remainder remainder, Context context){
        remainderList.add(remainder);
        writeRemaindersToFile(remainderList, context);
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    public String getLastId(){ return lastId; }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    private void writeToFile(List<Activity> list, Context context) {
        AppManager.getInstance().setActivityList(list);
        StringBuilder data= new StringBuilder();
        for (Activity act: list) {
            data.append(act.toString()).append("\n");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void writeRemaindersToFile(List<Remainder> list, Context context){
        AppManager.getInstance().setRemainderList(list);
        StringBuilder data= new StringBuilder();
        for (Remainder r: list) {
            data.append(r.toString()).append("\n");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("remainders.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void setRemainderList(List<Remainder> remainderList) {
        this.remainderList=remainderList;
        Log.i("info", "Reminders: "+remainderList);
    }

    public List<Remainder> getRemainderList(){
        return remainderList;
    }

    public List<MonthReport> getMonthReportList() {
        List<MonthReport> monthReportList=new ArrayList<>();
        for (Activity act: activityList) {
            String year=act.getData().substring(6);
            String month=getMonth(act.getData().substring(3,5));
            int time=getTime(act.getDurata());
            if(monthReportList.isEmpty()){
                MonthReport monthReport=new MonthReport(month, year, 1, time);
                monthReportList.add(monthReport);
            }else{
                boolean found=false;
                Iterator<MonthReport> it= monthReportList.iterator();
                while(!found && it.hasNext()){
                    MonthReport m=it.next();
                    if(m.getMonth().equals(month) && m.getYear().equals(year)){
                        m.setNbActivities(1);
                        m.setTimeActivities(time);
                        found=true;
                    }
                }
                if(!found){
                    MonthReport monthReport=new MonthReport(month, year, 1, time);
                    monthReportList.add(monthReport);
                }
            }
        }
        //todo ordinare la lista in base al mese e anno
        Collections.reverse(monthReportList);
        return monthReportList;
    }

    private String getMonth(String m){
        String month="";
        switch (m){
            case "01":
                month="Gennaio";
                break;
            case "02":
                month="Febbraio";
                break;
            case "03":
                month="Marzo";
                break;
            case "04":
                month="Aprile";
                break;
            case "05":
                month="MAggio";
                break;
            case "06":
                month="Giugno";
                break;
            case "07":
                month="Luglio";
                break;
            case "08":
                month="Agosto";
                break;
            case "09":
                month="Settembre";
                break;
            case "10":
                month="Ottobre";
                break;
            case "11":
                month="Novembre";
                break;
            case "12":
                month="Dicembre";
                break;
        }
        return month;
    }

    private int getTime(String duration){
        int time;
        String minute=duration.substring(3);
        int min=Integer.parseInt(minute);
        String hours=duration.substring(0,2);
        int hour=Integer.parseInt(hours);
        time=min+(hour*60);
        return time;
    }

    public String getTotTime(){
        String res="";
        int time=0;
        for (Activity act : activityList) {
            String tm=act.getDurata();
            time+=getTime(tm);
        }
        return res+time;
    }
}