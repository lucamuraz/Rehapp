package com.example.rehapp.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.rehapp.R;

public class AddNotification extends AppCompatActivity {

    private static final String CHANNEL_ID = "123";
    Context ctx=this;
    Button button;

    int notificationId=3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.notification_new);
        button=findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_bell_foreground)
                        .setContentTitle("Notifica di prova")
                        .setContentText("Ecco il conenuto della notifica")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setWhen(1);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(notificationId, builder.build());

            }
        });

        super.onCreate(savedInstanceState);
    }






}
