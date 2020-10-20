package com.example.rehapp.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        final Context ctx=this;
        //todo fare il controllo login
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
}
