package com.example.rehapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.rehapp.Model.DAO;
import com.example.rehapp.R;


public class LogActivity extends AppCompatActivity{

    String username;
    private CardView cardView;
    private ProgressBar progressBar;
    private Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        progressBar=findViewById(R.id.progressBar3);
        cardView=findViewById(R.id.loginCard);
        cardView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void back(View view) {
        finish();
    }

    public void btnRegistrati(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void accedi(View view) {
        cardView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        EditText u = findViewById(R.id.username);
        EditText p = findViewById(R.id.password);
        username = u.getText().toString();
        String password = p.getText().toString();
        DAO m= new DAO();
        m.loginUser(username,password, ctx);
    }


}