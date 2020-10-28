package com.example.rehapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.AppManager;
import com.example.rehapp.DAO.Model;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Context ctx=this;

    final Model m = new Model();

    Spinner type;
    Spinner category;
    List<String> categoryElmts;
    List<String> typeElmts;
    EditText duration;
    EditText date;
    EditText title;
    Toolbar toolbar;

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


        super.onCreate(savedInstanceState);

        category=findViewById(R.id.spinner2);
        type=findViewById(R.id.spinner3);
        duration=findViewById(R.id.durata);
        date=findViewById(R.id.editTextDate);
        title=findViewById(R.id.titolo_attività);


        categoryElmts=new ArrayList<>();
        categoryElmts.add("Seduta riabilitativa");
        categoryElmts.add("Allenamento");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categoryElmts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(category.getSelectedItem().equals("Seduta riabilitativa")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Standard");
                }else if(category.getSelectedItem().equals("Allenamento")){
                    typeElmts= new ArrayList<>();
                    typeElmts.add("Resistenza");
                    typeElmts.add("Forza");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        ctx, android.R.layout.simple_spinner_item, typeElmts);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                type.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        Button button = findViewById(R.id.aggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=SaveSharedPreferences.getUser(ctx);
                String categoria=category.getSelectedItem().toString();
                String id="";
                id=AppManager.getInstance().getLastId();
                AppManager.getInstance().setLastId("00"+id.substring(2));

                String typeAct=type.getSelectedItem().toString();
                String titolo=title.getText().toString();
                String data=date.getText().toString();
                String durata= duration.getText().toString();
                m.addActivity(username, categoria, id, typeAct, durata, data, titolo);

                AppManager.getInstance().addOnActivityList(new Activity(typeAct, titolo, data, durata, categoria), ctx);

                finish();
            }
        });
    }

}
