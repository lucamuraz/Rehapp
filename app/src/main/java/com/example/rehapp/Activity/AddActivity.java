package com.example.rehapp.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    Context ctx=this;

    final DAO m = new DAO();
    final Calendar calendar=Calendar.getInstance();
    String hour;

    Spinner type;
    Spinner category;
    List<String> categoryElmts;
    List<String> typeElmts;
    EditText duration;
    EditText date;
    EditText title;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_new);

        toolbar=findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Aggiungi attività");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        super.onCreate(savedInstanceState);

        category=findViewById(R.id.spinner2);
        type=findViewById(R.id.spinner3);
        duration=findViewById(R.id.durata);
        date=findViewById(R.id.editTextDate);
        title=findViewById(R.id.titolo_attività);


        categoryElmts=new ArrayList<>();
        categoryElmts.add("Seduta riabilitativa");
        categoryElmts.add("Allenamento");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categoryElmts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(category.getSelectedItem().equals("Seduta riabilitativa")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Standard");
                }else if(category.getSelectedItem().equals("Allenamento")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Resistenza");
                    typeElmts.add("Forza");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ctx, android.R.layout.simple_spinner_item, typeElmts);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                type.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        Button button = findViewById(R.id.aggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titolo=title.getText().toString();
                String data=date.getText().toString();
                String durata= duration.getText().toString();
                String categoria=category.getSelectedItem().toString();
                if(durata.equals("") && data.equals("")) {
                    String toastMessage = "Campi mancanti!";
                    Toast mToast = Toast.makeText(ctx, toastMessage, Toast.LENGTH_LONG);
                    mToast.show();
                }else{
                    if(titolo.equals("")){
                        titolo=categoria+" del "+data;
                    }
                    String typeAct=type.getSelectedItem().toString();
                    String username=SaveSharedPreferences.getUser(ctx);
                    String id;
                    id=AppManager.getInstance().getLastId();
                    AppManager.getInstance().setLastId("00"+id.substring(2));
                    m.addActivity(username, categoria, id, typeAct, durata, data, titolo);
                    AppManager.getInstance().addOnActivityList(new Activity(id, typeAct, titolo, data, durata, categoria), ctx);

                    Intent i=new Intent(ctx, Home.class);
                    i.putExtra("redirect", 1);
                    startActivity(i);

                    finish();
                }

            }
        });

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

        date.setOnClickListener(new View.OnClickListener() {
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

        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(ctx, TimePickerDialog.THEME_HOLO_LIGHT, timePicker, 0, 0, true).show();
            }
        });

    }

    private void updateDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        date.setText(sdf.format(calendar.getTime()));
    }

    private void updateHourLabel() {
        duration.setText(hour);
    }
}
