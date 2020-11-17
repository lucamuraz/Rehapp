package com.example.rehapp.Activity;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.Model.Remainder;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Splash extends AppCompatActivity {
    private static final String CHANNEL_ID = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        final Context ctx=this;
        createNotificationChannel();
        if(SaveSharedPreferences.getUserName(ctx).length()!=0){
            readActivityFromFile(this);
            readRemainderFromFile(this);
        }
        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    Intent i;
                    if(SaveSharedPreferences.getUserName(ctx).length()!=0){
                        i=new Intent(Splash.this,Home.class);
                        i.putExtra("redirect", 0);
                    }
                    else{
                        i=new Intent(Splash.this,MainActivity.class);
                    }
                    finish();
                    startActivity(i);
                }
            }
        };
        timer.start();
    }

    private void readActivityFromFile(Context context){
        List<Activity> activityList=new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] str=receiveString.split(", ");
                    Activity act= new Activity(str[0], str[3], str[1], str[5], str[2], str[4]);
                    activityList.add(act);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        AppManager.getInstance().setActivityList(activityList);
        AppManager.getInstance().setCurrentWeek();
    }

    private void readRemainderFromFile(Context context){
        List<Remainder> remainderList=new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("remainders.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] str=receiveString.split(", ");
                    Remainder remainder= new Remainder(str[0], str[1], str[2]);
                    remainderList.add(remainder);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        AppManager.getInstance().setRemainderList(remainderList);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
