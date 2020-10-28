package com.example.rehapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rehapp.AppManager;
import com.example.rehapp.DAO.Model;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class LogActivity extends AppCompatActivity{

    String psw = null;
    String name = null;
    String surname = null;
    String email = null;
    String edss = null;
    String username;
    private CardView cardView;
    private ProgressBar progressBar;
    private static final String TAG = "info";
    FirebaseDatabase mDB;
    Map<String, Object> map = new HashMap<>();
    AlertDialog.Builder builder;
    List<Activity> activityList;

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
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
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
        //ctx=getApplicationContext();
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        loginUser(username,password);
        /*while(SaveSharedPreferences.getUser(ctx).length()==0){

        }
        if(SaveSharedPreferences.getUser(ctx).equals("fake")){
            SaveSharedPreferences.clearData(ctx);
        }else{
            Intent i = new Intent(getApplicationContext(),Home.class);
            i.putExtra("username", username);
            startActivity(i);
        }*/

    }


    private void loginUser(final String username, final String password) {
        initDatabase();
        builder = new AlertDialog.Builder(getApplicationContext());

        final DatabaseReference myRef = mDB.getReference("Utenti").child(username);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                Log.i(TAG, "Value is: " + map);

                for (Map.Entry<String, Object> p : map.entrySet()) {
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
                    getActivity(username);
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    i.putExtra("username", username);
                    startActivity(i);
                }else{
                    builder.setMessage("Password errata");
                    AlertDialog alert = builder.create();
                    alert.setTitle("OPS!");
                    alert.show();
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
        SaveSharedPreferences.setUser(this, username);
    }

    public void getActivity(String username) {
        final DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Attività");
        // Read from the database
        activityList=new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                if(map!=null){
                    for(Map.Entry<String, Object> stringObjectEntry : map.entrySet()){
                        Map<String, Object> nn=(Map<String, Object>) stringObjectEntry.getValue();
                        String category=stringObjectEntry.getKey();
                        for (Map.Entry<String, Object> stringObjectEntry1 : nn.entrySet()) {
                            Map<String, Object> p = (Map<String, Object>) stringObjectEntry1.getValue();
                            Iterator<Map.Entry<String, Object>> it1 = p.entrySet().iterator();
                            String data = "";
                            String durata = "";
                            String tipologia = "";
                            String titolo = "";

                            while (it1.hasNext()) {
                                Map.Entry<String, Object> pvalue = it1.next();
                                switch (pvalue.getKey()) {
                                    case "Data":
                                        data = pvalue.getValue().toString();
                                        break;
                                    case "Durata":
                                        durata = pvalue.getValue().toString();
                                        break;
                                    case "Tipologia":
                                        tipologia = pvalue.getValue().toString();
                                        break;
                                    case "Titolo":
                                        titolo = pvalue.getValue().toString();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            Activity act = new Activity(tipologia, titolo, data, durata, category);
                            activityList.add(act);
                        }
                    }
                }
                writeToFile(activityList, getApplicationContext());
                myRef.removeEventListener(this);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void writeToFile(List<Activity> list,Context context) {
        AppManager.getInstance().setActivityList(list);
        StringBuilder data= new StringBuilder();
        for (Activity act: list) {
            data.append(act.toString()).append("\n");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}