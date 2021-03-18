package com.example.rehapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.R;

import java.util.Objects;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        Toolbar toolbar=findViewById(R.id.toolbar12);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Termini e condizioni");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView textView = findViewById(R.id.text_terms);
        String text = "La piattaforma Rehapp è stata sviluppata con il solo obiettivo di fornire ai pazienti afffetti da Sclerosi Multipla uno strumento di supporto al percorso riabilitativo. " +
                "Il paziente è tenuto ad informare il proprio medico su eventuali cambiamenti di salute come farebbe normalmente\n\n." +
                "L'app deve essere utilizzata in modo sicuro e solo in condizioni appropriate: attendere di essere in un luogo sicuro per svolgere le attività proposte";
        textView.setText(text);
    }
}
