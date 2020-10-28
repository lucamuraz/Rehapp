package com.example.rehapp.DAO;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.rehapp.Activity.Home;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Model {

    private FirebaseDatabase mDB;
    String TAG = "informazioni";
    private Map<String, Object> map = new HashMap<>();
    private List<Activity> result;
    String psw = null;
    String name ="";
    String surname ="";
    String email ="";
    String edss ="";

    public Model(){
        initDatabase();
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }

    public void register(String name, String surname,String username, String email, String password, String EDSS){
        DatabaseReference ref = mDB.getReference("Utenti").child(username);
        ref.setValue(username);
        DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Nome");
        myRef.setValue(name);
        DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Cognome");
        myRef1.setValue(surname);
        DatabaseReference myRef2 = mDB.getReference("Utenti").child(username).child("Email");
        myRef2.setValue(email);
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Password");
        myRef3.setValue(password);
        DatabaseReference myReF4 = mDB.getReference("Utenti").child(username).child("EDSS");
        myReF4.setValue(EDSS);
    }

    public void addActivity(String username, String categoria, String id, String type, String duration, String date, String title){
        DatabaseReference myRef0 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id);
        myRef0.setValue(id);
        DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Tipologia");
        myRef1.setValue(type);
        DatabaseReference myRef2 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Titolo");
        myRef2.setValue(title);
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Data");
        myRef3.setValue(date);
        DatabaseReference myRef4 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Durata");
        myRef4.setValue(duration);
    }

    /*public void login(final String username, final String password, final Context ctx, final AlertDialog.Builder builder){
        //TODO: Accedo al nodo degli utenti.
        final boolean[] ok = {false};
        final DatabaseReference myRef = mDB.getReference("Utenti").child(username);
        // TODO: Read from the database.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                map =(Map<String, Object>) dataSnapshot.getValue();
                Log.i(TAG, "Value is: " + map);
                // TODO: controlo sul nome utente non esistente
                Iterator<Map.Entry<String, Object>> it = null;
                if(map == null){
                    Log.i(TAG,"ENTRA");
                    builder.setMessage("Nome utente inesistente");
                    AlertDialog alert = builder.create();
                    alert.setTitle("OPS!");
                    alert.show();
                }else{
                    it = map.entrySet().iterator();
                    while(it.hasNext()) {
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
                    }
                    if(username.equals(myRef.getKey()) && password.equals(psw)){
                        saveLogin(ctx, username);
                        Intent i = new Intent(ctx, Home.class);
                        i.putExtra("username", username);
                        ctx.startActivity(i);
                    }else{
                        SaveSharedPreferences.setUser(ctx, "fake");
                        builder.setMessage("Password errata");
                        AlertDialog alert = builder.create();
                        alert.setTitle("OPS!");
                        alert.show();
                    }
                }
                myRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
    }*/

    public List<Activity> activityFromDB(final String date, String username) {
        final DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Attività");
        result=new ArrayList<>();
        // Read from the database

        Log.i(TAG, "Date in model is"+ date);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                if(map!=null){
                    for(Map.Entry<String, Object> stringObjectEntry : map.entrySet()){
                        Map<String, Object> nn=(Map<String, Object>) stringObjectEntry.getValue();

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
                                if (data.equals(date)) {
                                    Activity act = new Activity(tipologia, titolo, data, durata, "ciao");
                                    result.add(act);
                                }
                            }
                        }
                    }
                }
                Log.i(TAG, "result is"+ result);
                myRef.removeEventListener(this);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
        Log.i(TAG, "1 result is"+ result);
        return result;
    }

    /*private void saveLogin(Context ctx, String username){
        SaveSharedPreferences.setUserName(ctx, name);
        SaveSharedPreferences.setUserSurname(ctx, surname);
        SaveSharedPreferences.setUserEmail(ctx, email);
        SaveSharedPreferences.setUserPassword(ctx, psw);
        SaveSharedPreferences.setUserEdss(ctx, edss);
        SaveSharedPreferences.setUser(ctx, username);
    }*/

}
