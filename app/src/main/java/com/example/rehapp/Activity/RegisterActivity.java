package com.example.rehapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.*;

import android.os.Bundle;

import com.example.rehapp.Fragment.RegFragment;
import com.example.rehapp.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.fragment,new Start());
        transaction.add(R.id.fragment, new RegFragment());
        transaction.commit();
    }
}