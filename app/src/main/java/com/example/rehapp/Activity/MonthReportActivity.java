package com.example.rehapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.AppManager;
import com.example.rehapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.Objects;

public class MonthReportActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton pieBtn;
    ImageButton barBtn;
    private boolean bar=true;
    private boolean pie=false;
    TextView monthTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        toolbar = findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dettaglio report");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pieBtn=findViewById(R.id.pieBtn);
        barBtn=findViewById(R.id.barBtn);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        String month = extras.getString("month");
        monthTxt=findViewById(R.id.textView6);
        monthTxt.setText(month);


        final BarChart barChart=findViewById(R.id.barChart);
        BarDataSet barDataSet= new BarDataSet(AppManager.getInstance().getRep1(), "Minuti di attività");

        barDataSet.setColor(R.color.colorBtn);
        barDataSet.setValueTextColor(Color.TRANSPARENT);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis=barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(1000);

        Legend legend =barChart.getLegend();
        legend.setTextSize(20);


        final PieChart pieChart=findViewById(R.id.pieChart);
        PieDataSet pieDataSet= new PieDataSet(AppManager.getInstance().getRep2(), "");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData= new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setText("");
        pieChart.animate();
        pieChart.setEntryLabelColor(Color.TRANSPARENT);
        pieChart.setCenterText("Suddivisione \n attività");
        pieChart.setCenterTextSize(20);

        Legend legend1 =pieChart.getLegend();
        legend1.setWordWrapEnabled(true);
        legend1.setTextSize(20);

        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bar){
                    bar=true;
                    pie=false;
                    barChart.setVisibility(View.VISIBLE);
                    pieChart.setVisibility(View.INVISIBLE);
                    pieBtn.setImageResource(R.drawable.ic_pie1_foreground);
                    barBtn.setImageResource(R.drawable.ic_bar_foreground);
                }
            }
        });

        pieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pie){
                    pie=true;
                    bar=false;
                    barChart.setVisibility(View.INVISIBLE);
                    pieChart.setVisibility(View.VISIBLE);
                    pieBtn.setImageResource(R.drawable.ic_pie_foreground);
                    barBtn.setImageResource(R.drawable.ic_bar1_foreground);
                }
            }
        });

    }
}
