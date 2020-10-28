package com.example.rehapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Activity.ActivityEdit;
import com.example.rehapp.Activity.AddActivity;
import com.example.rehapp.Activity.Home;
import com.example.rehapp.AppManager;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarFragment extends Fragment implements ActivityAdapter.ItemClickListener{
    private ActivityAdapter.ItemClickListener itemClickListener=this;
    private List<Activity> activityList;
    RecyclerView recyclerView;
    CalendarView calendarView;
    Context ctx;
    String date1;

    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        Date dateobj = new Date();
        String date1 =df.format(dateobj);
        activityList= AppManager.getInstance().getActivityListForDate(date1);
        if(activityList.size()==0){
            activityList.add(new Activity("Clicca qui per iniziare una nuova attività", "", "", "", "Non hai ancora fatto attività!"));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        ctx=rootView.getContext();
        recyclerView=rootView.findViewById(R.id.list_cal);
        calendarView=rootView.findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        ActivityAdapter adapter = new ActivityAdapter(activityList, itemClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.hasFixedSize();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date=i2+"/"+(i1+1)+"/"+i;
                activityList= AppManager.getInstance().getActivityListForDate(date);
                if(date.equals(date1) && activityList.size()==0){
                    activityList.add(new Activity("Clicca qui per iniziare una nuova attività", "", "", "", "Non hai ancora fatto attività!"));
                }
                //Log.i("Info", "data is:"+activityList +"for date: "+date);
                ActivityAdapter adapter = new ActivityAdapter(activityList, itemClickListener);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                recyclerView.hasFixedSize();
               // Log.i("Info", "data is:"+activityList);
            }
        });

        FloatingActionButton floatingActionButton=rootView.findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(rootView.getContext(), AddActivity.class);
                startActivity(i);
            }
        } );

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Activity act=activityList.get(clickedItemIndex);
        if(act.getCategoria().equals("Seduta riabilitativa")||act.getCategoria().equals("Allenamento")){
            AppManager.getInstance().setActivity(act);
            Intent i=new Intent(ctx, ActivityEdit.class);
            startActivity(i);
        }else{
            Intent i=new Intent(ctx, Home.class);
            startActivity(i);
        }
    }
}