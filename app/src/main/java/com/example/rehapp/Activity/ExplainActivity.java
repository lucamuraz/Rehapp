package com.example.rehapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Adapter.ExercizeAdapter;
import com.example.rehapp.Model.TrainingMaker;
import com.example.rehapp.R;

import java.util.List;

public class ExplainActivity extends AppCompatActivity {

    private List<TrainingMaker.Exercize> exrecizeList;
    RecyclerView recyclerView;
    Context ctx=this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        Toolbar toolbar=findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spiegazione attività");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.exList);
        exrecizeList=TrainingMaker.getInstance().getGeneratedExercizeList();

        if(exrecizeList != null){
            ExercizeAdapter adapter = new ExercizeAdapter(exrecizeList); // la visulizzo con l'adpter
            //recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        }
    }
}
