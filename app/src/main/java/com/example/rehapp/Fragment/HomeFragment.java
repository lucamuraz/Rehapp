package com.example.rehapp.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.rehapp.Activity.EnduranceActivityH;
import com.example.rehapp.Activity.EnduranceActivityL;
import com.example.rehapp.Activity.EnduranceActivityM;
import com.example.rehapp.Activity.StrengthActivity;
import com.example.rehapp.AppManager;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    List<ImageView> weekImg= new ArrayList<>();
    List<TextView> weekTxt= new ArrayList<>();

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

        weekImg.add((ImageView) rootView.findViewById(R.id.lunedì));
        weekImg.add((ImageView)rootView.findViewById(R.id.martedì));
        weekImg.add((ImageView)rootView.findViewById(R.id.mercoledì));
        weekImg.add((ImageView)rootView.findViewById(R.id.giovedì));
        weekImg.add((ImageView)rootView.findViewById(R.id.venerdì));
        weekImg.add((ImageView)rootView.findViewById(R.id.sabato));
        weekImg.add((ImageView)rootView.findViewById(R.id.domenica));
        weekTxt.add((TextView)rootView.findViewById(R.id.lunTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.marTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.merTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.gioTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.venTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.sabTxt));
        weekTxt.add((TextView)rootView.findViewById(R.id.domTxt));

        for(int i=0; i<7; i++){
            String value=AppManager.getInstance().getWeek()[i];
            int day=AppManager.getInstance().getDayWeek();
            if(day==i){
                weekTxt.get(i).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                weekTxt.get(i).setTypeface(Typeface.DEFAULT_BOLD);
            }
            switch (value){
                case "T":
                    weekImg.get(i).setImageResource(R.drawable.ic_done_foreground);
                    break;
                case "F":
                    weekImg.get(i).setImageResource(R.drawable.ic_not_foreground);
                    break;
                default:
                    break;
            }
        }

        CardView enduBtn=rootView.findViewById(R.id.endurance);
        enduBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edss=Integer.parseInt(SaveSharedPreferences.getUserEdss(rootView.getContext()));
                if(edss<=5){
                    Intent i = new Intent(rootView.getContext(), EnduranceActivityL.class);
                    startActivity(i);
                    requireActivity().finish();
                }else if(edss>=6.5){
                    Intent i = new Intent(rootView.getContext(), EnduranceActivityH.class);
                    startActivity(i);
                    requireActivity().finish();
                }else{
                    Intent i = new Intent(rootView.getContext(), EnduranceActivityM.class);
                    startActivity(i);
                    requireActivity().finish();
                }
            }
        });

        CardView strBtn=rootView.findViewById(R.id.force);
        strBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(rootView.getContext(), StrengthActivity.class);
                requireActivity().finish();
                startActivity(i);
            }
        });

        final BottomNavigationView bottomNavigationView= AppManager.getInstance().getBottomNavigationView();


        TextView nbActivities=rootView.findViewById(R.id.totAct);
        nbActivities.setText(AppManager.getInstance().getTotalAct());

        int totTime=AppManager.getInstance().getTotTime();
        TextView timeActivities=rootView.findViewById(R.id.totTime);
        TextView minActivities=rootView.findViewById(R.id.timetext);
        if(totTime>9999){
            totTime=totTime/60;
            String txt="ORE";
            minActivities.setText(txt);
        }else{
            String txt="MINUTI";
            minActivities.setText(txt);
        }
        String time=""+totTime;
        timeActivities.setText(time);

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