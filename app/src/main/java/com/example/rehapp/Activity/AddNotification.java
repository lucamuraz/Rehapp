package com.example.rehapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.R;

import com.example.rehapp.Model.Remainder;
import com.example.rehapp.SaveSharedPreferences;

public class AddNotification extends AppCompatActivity {

    private static final String CHANNEL_ID = "123";
    private static final String TAG = "info";
    Context ctx=this;
    Button button;
    Toolbar toolbar;
    EditText data;
    EditText ora;
    EditText titolo;
    DAO m = new DAO();
    int notificationId=3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.notification_new);

        data=findViewById(R.id.data_notifica);
        ora=findViewById(R.id.orario_notifica);
        titolo=findViewById(R.id.titolo_notifica);

        toolbar=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aggiungi notifica");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button=findViewById(R.id.aggiungi);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String date=data.getText().toString();
                String hour=ora.getText().toString();
                String title=titolo.getText().toString();
                String username = SaveSharedPreferences.getUser(ctx);
                m.setRemainder(title,date,hour,username); // scrivo nel database il nuovo promemoria.
                AppManager.getInstance().addOnRemainderList(new Remainder(title,date,hour),ctx); // aggiorno la lista.
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_bell_foreground)
                        .setContentTitle("Promemoria!")
                        .setContentText(title)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setWhen(1);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(notificationId, builder.build());

                // Torno al fragment delle notifiche.
                finish();
            }
        });
        super.onCreate(savedInstanceState);
    }






}
