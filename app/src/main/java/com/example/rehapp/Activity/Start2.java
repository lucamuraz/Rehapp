package com.example.rehapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.fragment.app.*;


public class Start2 extends Fragment {
    EditText e;
    FirebaseDatabase mDB;
    Context ctx=this.getContext();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start2, container, false);
        initDatabase();
        final String username = SaveSharedPreferences.getUser(ctx);
        final Button accedi = view.findViewById(R.id.finito);
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                e = getActivity().findViewById(R.id.edss);
                String edss = e.getText().toString();
                DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("EDSS");
                myRef.setValue(edss);
                transaction.commit();

                SaveSharedPreferences.setUserEdss(ctx, edss);
            }
        });
        return view;
    }
    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }
}