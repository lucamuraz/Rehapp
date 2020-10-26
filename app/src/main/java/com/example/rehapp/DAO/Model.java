package com.example.rehapp.DAO;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Model {

    private FirebaseDatabase mDB;
    String TAG = "informazioni";

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
        Log.i(TAG,EDSS);
        myReF4.setValue(EDSS);
    }


}
