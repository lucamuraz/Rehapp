package com.example.rehapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rehapp.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_new);
        super.onCreate(savedInstanceState);
        Button button = findViewById(R.id.aggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //salva i dati e torna al calendario
                finish();
            }
        });
    }
}
