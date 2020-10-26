package com.example.rehapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Context ctx=this;
    FirebaseDatabase mDB;

    Spinner spinner;
    List<String> spinnerElmts;
    EditText duration;
    EditText date;
    EditText title;
    Toolbar toolbar;

    String category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_new);

        toolbar=findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aggiungi attività");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initDatabase();

        super.onCreate(savedInstanceState);

        duration=findViewById(R.id.durata);
        date=findViewById(R.id.editTextDate);
        title=findViewById(R.id.titolo_attività);

        spinner=findViewById(R.id.spinner2);
        if(SaveSharedPreferences.getActivityType(this).equals("attività")){
            spinnerElmts= new ArrayList<>();
            spinnerElmts.add("Resistenza");
            spinnerElmts.add("Forza");
            category="Allenamenti";
        }else if(SaveSharedPreferences.getActivityType(this).equals("seduta")){
            spinnerElmts= new ArrayList<>();
            spinnerElmts.add("Seduta riabilitativa");
            category="Sedute";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerElmts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = findViewById(R.id.aggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=SaveSharedPreferences.getUser(ctx);
                String id="004";

                if(category.equals("Allenamenti")){
                    id="act_"+""; //todo generare id progressivo
                    DatabaseReference myRef0 = mDB.getReference("Utenti").child(username).child("Attività").child(category).child(id);
                    myRef0.setValue(id);

                    String type=spinner.getSelectedItem().toString();
                    DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Attività").child(category).child(id).child("Tipologia");
                    myRef1.setValue(type);

                }else if(category.equals("Sedute")){
                    id="seduta_"+""; //todo generare id progressivo
                    DatabaseReference myRef0 = mDB.getReference("Utenti").child(username).child("Attività").child("Sedute").child(id);
                    myRef0.setValue(id);
                }

                String titolo=title.getText().toString();
                DatabaseReference myRef2 = mDB.getReference("Utenti").child(username).child("Attività").child(category).child(id).child("Titolo");
                myRef2.setValue(titolo);

                String data=date.getText().toString();
                DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Attività").child(category).child(id).child("Data");
                myRef3.setValue(data);

                String durata= duration.getText().toString();
                DatabaseReference myRef4 = mDB.getReference("Utenti").child(username).child("Attività").child(category).child(id).child("Durata");
                myRef4.setValue(durata);

                finish();
            }
        });
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }

}
