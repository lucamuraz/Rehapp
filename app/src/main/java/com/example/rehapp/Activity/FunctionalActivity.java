package com.example.rehapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.Model.DAO;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.util.List;
import java.util.Objects;

public class FunctionalActivity extends AppCompatActivity {

    CheckBox balance;
    CheckBox core;
    CheckBox artiSuperiori;
    CheckBox artiInferiori;
    CheckBox respirazione;
    CheckBox torace;
    CheckBox dorso;
    CheckBox stretching;
    Context ctx;

    private final boolean[] edited = new boolean[1];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        edited[0]=false;
        ctx=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional);

        Toolbar toolbar=findViewById(R.id.toolbar12);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sistemi funzionali");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edited[0]){
                    List<String> sys= SaveSharedPreferences.getSystems(ctx);
                    String systems="";
                    for (String s: sys) {
                        systems+=s + " ";
                    }
                    String user=SaveSharedPreferences.getUser(ctx);
                    DAO m=new DAO();
                    m.editSystems(user,systems);
                }
                finish();
            }
        });


        balance=findViewById(R.id.box1);
        boolean checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("balance", false);
        balance.setChecked(checked);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("balance", balance.isChecked()).apply();
            }
        });

        core=findViewById(R.id.box2);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("core", false);
        core.setChecked(checked);
        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("core", core.isChecked()).apply();
            }
        });

        artiSuperiori=findViewById(R.id.box3);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("artiSuperiori", false);
        artiSuperiori.setChecked(checked);
        artiSuperiori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("artiSuperiori", artiSuperiori.isChecked()).apply();
            }
        });

        artiInferiori=findViewById(R.id.box4);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("artiInferiori", false);
        artiInferiori.setChecked(checked);
        artiInferiori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("artiInferiori", artiInferiori.isChecked()).apply();
            }
        });

        stretching=findViewById(R.id.box5);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("stretching", false);
        stretching.setChecked(checked);
        stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("stretching", stretching.isChecked()).apply();
            }
        });

        respirazione=findViewById(R.id.box6);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("respirazione", false);
        respirazione.setChecked(checked);
        respirazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("respirazione", respirazione.isChecked()).apply();
            }
        });

        torace=findViewById(R.id.box7);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("torace", false);
        torace.setChecked(checked);
        torace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("torace", torace.isChecked()).apply();
            }
        });

        dorso=findViewById(R.id.box8);
        checked = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getBoolean("dorso", false);
        dorso.setChecked(checked);
        dorso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited[0] =true;
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("dorso", dorso.isChecked()).apply();
            }
        });

    }
}
