package com.example.rehapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rehapp.R;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnAvanti(View view) {
        Intent i = new Intent(this,  LogActivity.class);
        startActivity(i);

    }

    public void btnRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}