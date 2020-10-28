package com.example.rehapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rehapp.Activity.AddNotification;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotifyFragment extends Fragment {

    List<Activity> activityList= new ArrayList<>();
    RecyclerView recyclerView;

    public NotifyFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NotifyFragment newInstance(String param1, String param2) {
        NotifyFragment fragment = new NotifyFragment();;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_notify, container, false);
        FloatingActionButton floatingActionButton=rootView.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(rootView.getContext(), AddNotification.class);
                startActivity(i);
            }
        } );

        recyclerView=rootView.findViewById(R.id.notify_list);

        // Initialize contacts
        /*Activity activity=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity1=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity2=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        Activity activity3=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity4=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity5=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        Activity activity6=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity7=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity8=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        Activity activity9=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity10=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity11=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        Activity activity12=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity13=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity14=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        Activity activity15=new Activity("Attività", "Camminata", "25/10/2020", "35 min");
        Activity activity16=new Activity("Attività", "Camminata veloce", "25/10/2020", "35 min");
        Activity activity17=new Activity("Attività", "Camminata lenta", "25/10/2020", "35 min");
        activityList.add(activity);
        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);
        activityList.add(activity4);
        activityList.add(activity5);
        activityList.add(activity6);
        activityList.add(activity7);
        activityList.add(activity8);
        activityList.add(activity9);
        activityList.add(activity10);
        activityList.add(activity11);
        activityList.add(activity12);
        activityList.add(activity13);
        activityList.add(activity14);
        activityList.add(activity15);
        activityList.add(activity16);
        activityList.add(activity17);
        // Create adapter passing in the sample user data
        NotifyAdapter adapter = new NotifyAdapter(activityList);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.hasFixedSize();*/

        return rootView;
    }
}
