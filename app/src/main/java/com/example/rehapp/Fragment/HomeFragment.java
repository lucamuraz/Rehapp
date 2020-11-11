package com.example.rehapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rehapp.Activity.EnduranceActivityH;
import com.example.rehapp.Activity.EnduranceActivityL;
import com.example.rehapp.Activity.StrengthActivity;
import com.example.rehapp.AppManager;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        CardView enduBtn=rootView.findViewById(R.id.endurance);
        enduBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edss=Integer.parseInt(SaveSharedPreferences.getUserEdss(rootView.getContext()));
                if(edss<6.5){
                    Intent i = new Intent(rootView.getContext(), EnduranceActivityL.class);
                    startActivity(i);
                    //getActivity().finish();
                }else{
                    Intent i = new Intent(rootView.getContext(), EnduranceActivityH.class);
                    startActivity(i);
                    //getActivity().finish();
                }
            }
        });

        CardView strBtn=rootView.findViewById(R.id.force);
        strBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(rootView.getContext(), StrengthActivity.class);
                startActivity(i);
                //getActivity().finish();
            }
        });

        final BottomNavigationView bottomNavigationView= AppManager.getInstance().getBottomNavigationView();

        TextView nbActivities=rootView.findViewById(R.id.totAct);
        nbActivities.setText(AppManager.getInstance().getTotalAct());
        TextView timeActivities=rootView.findViewById(R.id.totTime);
        timeActivities.setText(AppManager.getInstance().getTotTime());

        CardView cardView=rootView.findViewById(R.id.cardTotal);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_report);
                Fragment fragment=new ReportFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView cardView1=rootView.findViewById(R.id.cardWeek);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_calendar);
                Fragment fragment=new CalendarFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }
}