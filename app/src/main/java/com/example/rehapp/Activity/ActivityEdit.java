package com.example.rehapp.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.EditTextPreference;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ActivityEdit extends AppCompatActivity {

    Toolbar toolbar;
    TextView act_tit;
    TextView act_tip;
    TextView act_dat;
    TextView act_dur;
    TextView act_cat;
    EditText act_tit_new;
    EditText act_dat_new;
    EditText act_dur_new;
    Spinner act_cat_new;
    Spinner act_tip_new;
    Button delete;
    Button edit;
    TextView msg;

    List<String> categoryElmts;
    List<String> typeElmts;
    private Context ctx=this;
    private Activity activity;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    final Calendar calendar=Calendar.getInstance();
    String hour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dettagli attività");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        msg=findViewById(R.id.textView10);

        activity=AppManager.getInstance().getActivity();

        act_cat=findViewById(R.id.activity_cat);
        act_cat.setText(activity.getCategoria());
        act_tit=findViewById(R.id.activity_tit);
        act_tit.setText(activity.getTitolo());
        act_tip=findViewById(R.id.activity_tip);
        act_tip.setText(activity.getTipologia());
        act_dat=findViewById(R.id.activity_dat);
        act_dat.setText(activity.getData());
        act_dur=findViewById(R.id.activity_dur);
        act_dur.setText(activity.getDurata());

        act_cat_new=findViewById(R.id.act_cat);
        act_dat_new=findViewById(R.id.activity_dat_new);
        act_dur_new=findViewById(R.id.activity_dur_new);
        act_tit_new=findViewById(R.id.activity_tit_New);
        act_tip_new=findViewById(R.id.act_type);

        delete=findViewById(R.id.elimina);
        edit=findViewById(R.id.modifica);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().equals("Modifica")){ //gestione tadto modifica
                    edit.setText(R.string.save1);
                    delete.setText(R.string.annulla);
                    act_dur_new.setText(act_dur.getText());
                    act_dat_new.setText(act_dat.getText());
                    act_tit_new.setText(act_tit.getText());
                    act_cat_new.setVisibility(View.VISIBLE);
                    act_dat_new.setVisibility(View.VISIBLE);
                    act_dur_new.setVisibility(View.VISIBLE);
                    act_tit_new.setVisibility(View.VISIBLE);
                    act_tip_new.setVisibility(View.VISIBLE);
                    act_tit.setVisibility(View.INVISIBLE);
                    act_tip.setVisibility(View.INVISIBLE);
                    act_dat.setVisibility(View.INVISIBLE);
                    act_dur.setVisibility(View.INVISIBLE);
                    act_cat.setVisibility(View.INVISIBLE);
                }else {
                    activity.setData(act_dat_new.getText().toString());
                    activity.setTitolo(act_tit_new.getText().toString());
                    activity.setDurata(act_dur_new.getText().toString());
                    activity.setCategoria(act_cat_new.getSelectedItem().toString());
                    activity.setTipologia(act_tip_new.getSelectedItem().toString());
                    edit.setText(R.string.modifica);
                    delete.setText(R.string.elimina);
                    act_cat_new.setVisibility(View.INVISIBLE);
                    act_dat_new.setVisibility(View.INVISIBLE);
                    act_dur_new.setVisibility(View.INVISIBLE);
                    act_tit_new.setVisibility(View.INVISIBLE);
                    act_tip_new.setVisibility(View.INVISIBLE);
                    act_tit.setVisibility(View.VISIBLE);
                    act_tip.setVisibility(View.VISIBLE);
                    act_dat.setVisibility(View.VISIBLE);
                    act_dur.setVisibility(View.VISIBLE);
                    act_cat.setVisibility(View.VISIBLE);

                    AppManager.getInstance().editActivity(activity, ctx);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(delete.getText().equals("Elimina")){  //gestione tasto elimina
                    builder=new AlertDialog.Builder(ctx);
                    builder.setMessage("L'attività sarà eliminata");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.cancel();
                        }
                    });
                    dialog=builder.create();
                    dialog.show();

                }else{                                     // gestione tasto annulla
                    edit.setText(R.string.modifica);
                    delete.setText(R.string.elimina);
                    act_cat_new.setVisibility(View.INVISIBLE);
                    act_dat_new.setVisibility(View.INVISIBLE);
                    act_dur_new.setVisibility(View.INVISIBLE);
                    act_tit_new.setVisibility(View.INVISIBLE);
                    act_tip_new.setVisibility(View.INVISIBLE);
                    act_tit.setVisibility(View.VISIBLE);
                    act_tip.setVisibility(View.VISIBLE);
                    act_dat.setVisibility(View.VISIBLE);
                    act_dur.setVisibility(View.VISIBLE);
                    act_cat.setVisibility(View.VISIBLE);
                }
            }
        });


        categoryElmts=new ArrayList<>();
        typeElmts= new ArrayList<>();
        if(activity.getCategoria().equals("Seduta riabilitativa")){
            categoryElmts.add("Seduta riabilitativa");
            categoryElmts.add("Allenamento");
            typeElmts.add("Standard");
        }else{
            categoryElmts.add("Allenamento");
            categoryElmts.add("Seduta riabilitativa");
            if(activity.getTipologia().equals("Resistenza")){
                typeElmts.add("Resistenza");
                typeElmts.add("Forza");
            }else{
                typeElmts.add("Forza");
                typeElmts.add("Resistenza");
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ctx, android.R.layout.simple_spinner_item, categoryElmts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_cat_new.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                ctx, android.R.layout.simple_spinner_item, typeElmts);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_tip_new.setAdapter(adapter1);

        act_cat_new.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(act_cat_new.getSelectedItem().equals("Seduta riabilitativa")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Standard");
                }else if(act_cat_new.getSelectedItem().equals("Allenamento")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Resistenza");
                    typeElmts.add("Forza");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ctx, android.R.layout.simple_spinner_item, typeElmts);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                act_tip_new.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        act_dat_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, datePicker, getDate(activity.getData())[2], getDate(activity.getData())[1], getDate(activity.getData())[0]).show();
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

        act_dur_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(ctx, TimePickerDialog.THEME_HOLO_LIGHT, timePicker, getDuration(activity.getDurata())[0], getDuration(activity.getDurata())[1], true).show();
            }
        });

    }

    private void updateDateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        act_dat_new.setText(sdf.format(calendar.getTime()));
    }

    private void updateHourLabel() {
        act_dur_new.setText(hour);
    }

    private int[] getDate(String date){
        int[] res=new int[3];
        res[0]=Integer.parseInt(date.substring(0,2));
        res[1]=Integer.parseInt(date.substring(3,5));
        res[2]=Integer.parseInt(date.substring(6,10));
        return res;
    }

    private int[] getDuration(String dur){
        int[] res=new int[2];
        res[0]=Integer.parseInt(dur.substring(0,2));
        res[1]=Integer.parseInt(dur.substring(3,5));
        return res;
    }

}
