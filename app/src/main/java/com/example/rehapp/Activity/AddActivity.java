package com.example.rehapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Spinner spinner;
    List<String> spinnerElmts;
    EditText duration;
    EditText date;
    EditText title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_new);
        super.onCreate(savedInstanceState);

        duration=findViewById(R.id.durata);
        date=findViewById(R.id.editTextDate);
        title=findViewById(R.id.titolo_attività);

        spinner=findViewById(R.id.spinner2);
        if(SaveSharedPreferences.getActivityType(this).equals("attività")){
            spinnerElmts= new ArrayList<>();
            spinnerElmts.add("Resistenza");
            spinnerElmts.add("Forza");
        }else if(SaveSharedPreferences.getActivityType(this).equals("seduta")){
            spinnerElmts= new ArrayList<>();
            spinnerElmts.add("Seduta riabilitativa");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerElmts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = findViewById(R.id.aggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type=spinner.getSelectedItem().toString();
                String titolo=title.getText().toString();
                String data=date.getText().toString();
                String durata= duration.getText().toString();
                //salva i dati e torna al calendario
                finish();
            }
        });
    }
}
