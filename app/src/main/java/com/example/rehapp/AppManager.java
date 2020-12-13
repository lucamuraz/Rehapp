package com.example.rehapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.rehapp.Fragment.RegFragment;
import com.example.rehapp.Fragment.RegFragment2;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.Model.MonthReport;
import com.example.rehapp.Model.Remainder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AppManager {
    @SuppressLint("StaticFieldLeak")
    private static AppManager singleInstance;
    private List<Activity> activityList;
    private List<Remainder> remainderList;
    private String[] week = {"N","N","N","N","N","N","N"};
    private String[] days = new String[7];
    private String lastId;
    private Activity activity;
    BottomNavigationView bottomNavigationView;
    private Context ctx;
    private int dayOfWeek;

    private RegFragment reg;
    private RegFragment2 reg2;

    public void setReg(RegFragment fr){
        reg=fr;
    }
    public void setReg2(RegFragment2 fr){
        reg2=fr;
    }
    public RegFragment getReg(){ return reg; }
    public RegFragment2 getReg2(){ return reg2; }

    private String[] tmpData=new String [5];

    public String getTmpUsername(){
        if(tmpData[0]!=null && tmpData[1]!=null){
            String tmp1=tmpData[0].toLowerCase();
            String tmp2=tmpData[1].toLowerCase();
            tmpData[2]=tmp1+"_"+tmp2;
        }
        return tmpData[2];
    }

    public void saveTmpData(String data, int idx){
        tmpData[idx]=data;
    }

    public String getTmpData(int idx){
        return tmpData[idx];
    }

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
        if(activityList.isEmpty()){
            res="0";
        }else{
            res+=activityList.size();
        }
        return res;
    }

    public Context getCtx(){ return ctx; }

    public void setCtx(Context ctx){ this.ctx=ctx; }

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
        String date=act.getData();
        for(int i=0; i<7; i++){
            if(days[i].equals(date)){
                if(!week[i].equals("T")){
                    week[i]="T";
                }
            }
        }
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

    public void setCurrentWeek() {
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        Date dateobj = new Date();
        String date1 =format.format(dateobj);
        days = new String[7];
        int delta;
        if(now.get(GregorianCalendar.DAY_OF_WEEK)==1){
            delta=-6;
        }else{
            delta= -(now.get(GregorianCalendar.DAY_OF_WEEK))+2;

        }
        now.add(Calendar.DAY_OF_MONTH, delta );
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        List<Activity> tmp;
        boolean after=false;
        int i=0;
        for (String day: days) {
            tmp=getActivityListForDate(day);
            if(day.equals(date1)){
                dayOfWeek=i;
                if(tmp.size()>0){
                    week[i]="T";
                }else{
                    week[i]="N";
                }
                after=true;
            }else{
                if(after){
                    week[i]="N";
                }else{
                    if(tmp.size()>0){
                        week[i]="T";
                    }else{
                        week[i]="F";
                    }
                }
            }
            i++;
        }
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

    public int getTotTime(){
        int time=0;
        for (Activity act : activityList) {
            String tm=act.getDurata();
            time+=getTime(tm);
        }
        return time;
    }

    public String[] getWeek() {
        return week;
    }

    public void SetDayOfWeek(int i, String value){
        week[i]=value;
    }

    public int getDayWeek() {
        return dayOfWeek;
    }

    public void editActivity(Activity newActivity, Context context){
        boolean found=false;
        int i=0;
        while(!found){
            if(activityList.get(i).getId().equals(newActivity.getId())){
                activityList.get(i).setTitolo(newActivity.getTitolo());
                activityList.get(i).setData(newActivity.getData());
                activityList.get(i).setCategoria(newActivity.getCategoria());
                activityList.get(i).setTipologia(newActivity.getTipologia());
                activityList.get(i).setDurata(newActivity.getDurata());
                found=true;
            }
            i++;
        }
        DAO m=new DAO();
        m.editActivity(activity, newActivity, SaveSharedPreferences.getUser(context));// modifica nel db
        m.editActivitiesToFile(activityList, ctx);
        //todo file
        //todo db
    }
}