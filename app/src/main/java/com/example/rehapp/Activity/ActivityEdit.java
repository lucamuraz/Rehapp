package com.example.rehapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.AppManager;
import com.example.rehapp.R;

import java.util.Objects;

public class ActivityEdit extends AppCompatActivity {

    Toolbar toolbar;
    TextView act_tit;
    TextView act_tip;
    TextView act_dat;
    TextView act_dur;
    TextView act_cat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dettagli attività");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        act_cat=findViewById(R.id.activity_cat);
        act_cat.setText(AppManager.getInstance().getActivity().getCategoria());
        act_tit=findViewById(R.id.activity_tit);
        act_tit.setText(AppManager.getInstance().getActivity().getTitolo());
        act_tip=findViewById(R.id.activity_tip);
        act_tip.setText(AppManager.getInstance().getActivity().getTipologia());
        act_dat=findViewById(R.id.activity_dat);
        act_dat.setText(AppManager.getInstance().getActivity().getData());
        act_dur=findViewById(R.id.activity_dur);
        act_dur.setText(AppManager.getInstance().getActivity().getDurata());
    }
}
