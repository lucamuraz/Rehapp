package com.example.rehapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.AppManager;
import com.example.rehapp.R;


public class RegFragment3 extends Fragment {

    CheckBox balance;
    CheckBox core;
    CheckBox artiSuperiori;
    CheckBox artiInferiori;
    CheckBox respirazione;
    CheckBox torace;
    CheckBox dorso;
    CheckBox stretching;
    RegFragment3 fr=this;
    Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg3, container, false);
        final ImageButton back=view.findViewById(R.id.reg3Back);
        final Button prosegui = view.findViewById(R.id.Prosegui);
        ctx=view.getContext();

        prosegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                AppManager.getInstance().setReg3(fr);
                RegFragment4 fragment3 = new RegFragment4();
                transaction.replace(R.id.fragment, fragment3);
                transaction.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                RegFragment2 fragment2 = AppManager.getInstance().getReg2();
                transaction.replace(R.id.fragment, fragment2);
                transaction.commit();
            }
        });


        balance.setChecked(false);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("balance", balance.isChecked()).apply();
            }
        });


        core.setChecked(false);
        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("core", core.isChecked()).apply();
            }
        });


        artiSuperiori.setChecked(false);
        artiSuperiori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("artiSuperiori", artiSuperiori.isChecked()).apply();
            }
        });


        artiInferiori.setChecked(false);
        artiInferiori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("artiInferiori", artiInferiori.isChecked()).apply();
            }
        });


        stretching.setChecked(false);
        stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("stretching", stretching.isChecked()).apply();
            }
        });


        respirazione.setChecked(false);
        respirazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("respirazione", respirazione.isChecked()).apply();
            }
        });


        torace.setChecked(false);
        torace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("torace", torace.isChecked()).apply();
            }
        });


        dorso.setChecked(false);
        dorso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                        .putBoolean("dorso", dorso.isChecked()).apply();
            }
        });
        return view;
    }

}