package com.example.rehapp.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddNotification extends AppCompatActivity {

    private static final String CHANNEL_ID = "123";
    final Calendar calendar=Calendar.getInstance();
    Context ctx=this;
    Button button;
    Toolbar toolbar;
    EditText data;
    EditText ora;
    EditText titolo;
    DAO m = new DAO();
    int notificationId=3;
    String hour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.notification_new);

        data=findViewById(R.id.data_notifica);
        ora=findViewById(R.id.orario_notifica);
        titolo=findViewById(R.id.titolo_notifica);

        toolbar=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Aggiungi notifica");
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
                Intent i=new Intent(ctx, Home.class);
                i.putExtra("redirect", 3);
                startActivity(i);
                finish();
            }
        });
        super.onCreate(savedInstanceState);

        final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }

        };

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, datePicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TimePickerDialog.OnTimeSetListener timePicker = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(i<10 && i1<10){
                    hour="0"+i+":0"+i1;
                }else{
                    if(i<10){
                        hour="0"+i+":"+i1;
                    }else if(i1<10){
                        hour=i+":0"+i1;
                    }else{
                        hour=i+":"+i1;
                    }
                }

                updateHourLabel();
            }
        };

        ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(ctx, TimePickerDialog.THEME_HOLO_LIGHT, timePicker, 12, 0, true).show();
            }
        });


    }

    private void updateDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        data.setText(sdf.format(calendar.getTime()));
    }

    private void updateHourLabel() {
        ora.setText(hour);
    }
}
