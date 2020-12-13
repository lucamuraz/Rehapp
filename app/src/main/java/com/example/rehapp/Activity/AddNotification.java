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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.Model.Remainder;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotification extends AppCompatActivity {

    private static final String TAG = "info";
    Context ctx=this;
    Button button;
    Toolbar toolbar;
    //Button data;
    EditText data;
    //Button ora;
    EditText ora;
    EditText titolo;
    DAO m = new DAO();
    String timeToNotify;
    String dateToNotify;
    String toParse;
    SimpleDateFormat formatter;

    public static final String NOTIFICATION_CHANNEL_ID = "123";
    public static final String default_notification_channel_id = "default";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*setContentView(R.layout.notification_new);

        data=findViewById(R.id.data_notifica);
        ora=findViewById(R.id.orario_notifica);
        titolo=findViewById(R.id.titolo_notifica);

        toolbar=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Aggiungi promemoria");
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

                if(date.equals("") && hour.equals("") && title.equals("")){
                    String toastMessage = "Campi mancanti!";
                    Toast mToast = Toast.makeText(ctx, toastMessage, Toast.LENGTH_LONG);
                    mToast.show();
                }else{
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
        });*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_new);
        data=findViewById(R.id.data_notifica);
        ora=findViewById(R.id.orario_notifica);
        data.setTextColor(getResources().getColor(R.color.nero));
        ora.setTextColor(getResources().getColor(R.color.nero));
        button=findViewById(R.id.aggiungi);
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

        /*Alarm service
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar myCalendar = Calendar.getInstance();
        */

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime();
            }
        });
        ;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String date=data.getText().toString();
                String hour=ora.getText().toString();
                String title=titolo.getText().toString();
                String username = SaveSharedPreferences.getUser(ctx);
                m.setRemainder(title,date,hour,username);
                if(data.getText().toString().equals("Data") || ora.getText().toString().equals("Ora")){
                    Toast.makeText(ctx,"Per favore seleziona la data e l'orario", Toast.LENGTH_SHORT).show();
                }
                //setAlarm(title,date,hour);


                AppManager.getInstance().addOnRemainderList(new Remainder(title,date,hour),ctx);
                //Date currentTime = Calendar.getInstance().getTime();
                //long current = currentTime.getTime();
                /*
                Log.i(TAG,"CURRENTTIME IN LONG : "+current);

                long delay =  myCalendar.getTimeInMillis() - current;
                Log.i(TAG,"DELAY IN LONG : "+ delay);

                long diff =   SystemClock.elapsedRealtime() + delay;
                Log.i(TAG,"DIFFTIME : " + diff);

                long delay = myCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                long futureInMillis = SystemClock.elapsedRealtime() + delay;
                Log.i(TAG,"passs"+futureInMillis);

                Intent notificationIntent = new Intent( AddNotification.this, NotificationReceiver. class ) ;
                PendingIntent broadcast = PendingIntent.getBroadcast(ctx,100,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis,broadcast);
                */
                Intent i=new Intent(ctx, Home.class);
                i.putExtra("redirect", 3);
                startActivity(i);
                finish();
            }
        });


    }

    /*public void setAlarm(String text, String date,String time) {
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AddNotification.this, NotificationReceiver.class);
        intent.putExtra("text", text);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_ONE_SHOT);
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.DAY_OF_MONTH, 19);

        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 23);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.AM);


        alarmManager.set(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(), pendingIntent);

    }*/


    public void selectTime(){
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeToNotify = hourOfDay+":"+minute;
                ora.setText(timeToNotify);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    public  void selectDate(){
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateToNotify = dayOfMonth+"/"+month+"/"+year;
                data.setText(dateToNotify);
            }
        },year,month,day);
        datePickerDialog.show();
    }


    public String formatTime(int minute){
        String formattedMinute = "";
        if(minute  < 10)
            formattedMinute = "0"+minute;
        return formattedMinute;
        /*
        if(hour == 0)
            time = "12" + ":" + formattedMinute + "AM";
        else if(hour < 12)
            time = hour + ":" + formattedMinute + "AM";
        else if(hour == 12)
            time = "12" + ":" + formattedMinute + "PM";
        else{
            int temp = hour -12;
            time = temp + ":" + formattedMinute + "PM";
        }
        return time;
         */
    }

    /*private void updateDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        data.setText(sdf.format(calendar.getTime()));
    }

    private void updateHourLabel() {
        ora.setText(hour);
    }*/
}

