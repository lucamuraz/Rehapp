package com.example.rehapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LogActivity extends AppCompatActivity{

    String psw = null;
    String name = null;
    String surname = null;
    String email = null;
    String edss = null;

    private static final String TAG = "info";
    FirebaseDatabase mDB;
    Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }


    public void back(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void btnRegistrati(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
    public void accedi(View view) {
        EditText u = findViewById(R.id.username);
        EditText p = findViewById(R.id.password);
        String username = u.getText().toString();
        String password = p.getText().toString();
        loginUser(username,password);
    }


    private void loginUser(final String username, final String password) {
        initDatabase();

        final DatabaseReference myRef = mDB.getReference("Utenti").child(username);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                Log.i(TAG, "Value is: " + map);
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();

                while(it.hasNext()){
                    Map.Entry<String, Object> p = it.next();
                    switch (p.getKey()) {
                        case "Password":
                            psw = p.getValue().toString();
                            break;
                        case "Nome":
                            name = p.getValue().toString();
                            break;
                        case "Cognome":
                            surname = p.getValue().toString();
                            break;
                        case "Email":
                            email = p.getValue().toString();
                            break;
                        case "EDSS":
                            edss = p.getValue().toString();
                            break;
                    }
                    Log.i(TAG, "Value is: " + psw); // legge la password.

                }
                if(username.equals(myRef.getKey()) && password.equals(psw)){
                    saveLogin();
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    i.putExtra("username", username);
                    startActivity(i);
                }
                myRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }

    private void saveLogin(){
        SaveSharedPreferences.setUserName(this, name);
        SaveSharedPreferences.setUserSurname(this, surname);
        SaveSharedPreferences.setUserEmail(this, email);
        SaveSharedPreferences.setUserPassword(this, psw);
        SaveSharedPreferences.setUserEdss(this, edss);
    }

}