package com.example.rehapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rehapp.Adapter.MonthReportAdapter;
import com.example.rehapp.AppManager;
import com.example.rehapp.Model.MonthReport;
import com.example.rehapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {

    List<MonthReport> monthReportList= new ArrayList<>();
    RecyclerView recyclerView;
    final Context ctx=getContext();

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_report, container, false);
        monthReportList = AppManager.getInstance().getMonthReportList(); // preno la lista piena
        if(monthReportList != null){
            MonthReportAdapter adapter = new MonthReportAdapter(monthReportList); // la visulizzo con l'adpter
            recyclerView = rootView.findViewById(R.id.report_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        }
        return rootView;
    }
}
