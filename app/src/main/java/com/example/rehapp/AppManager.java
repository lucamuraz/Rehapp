package com.example.rehapp;

import android.content.Context;
import android.util.Log;

import com.example.rehapp.Model.Activity;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private static AppManager singleInstance;
    private List<Activity> activityList;
    private String lastId;
    private Activity activity;

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

}