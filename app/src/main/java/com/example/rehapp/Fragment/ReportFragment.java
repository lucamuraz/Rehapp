package com.example.rehapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Activity.MonthReportActivity;
import com.example.rehapp.Adapter.MonthReportAdapter;
import com.example.rehapp.AppManager;
import com.example.rehapp.Model.MonthReport;
import com.example.rehapp.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment implements MonthReportAdapter.ItemClickListener{
    private MonthReportAdapter.ItemClickListener itemClickListener=this;
    List<MonthReport> monthReportList= new ArrayList<>();
    RecyclerView recyclerView;
    Context ctx;

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_report, container, false);
        ctx=rootView.getContext();
        monthReportList = AppManager.getInstance().getMonthReportList(); // preno la lista piena
        if(monthReportList != null){
            TextView text = rootView.findViewById(R.id.testo1);
            text.setVisibility(View.INVISIBLE);
            MonthReportAdapter adapter = new MonthReportAdapter(monthReportList, itemClickListener); // la visulizzo con l'adpter
            recyclerView = rootView.findViewById(R.id.report_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        }
        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) throws ParseException {
        MonthReport mRep=monthReportList.get(clickedItemIndex);
        AppManager.getInstance().setMonthReport(mRep.getMonth(), mRep.getYear());
        String m=""+mRep.getMonth()+ ""+ mRep.getYear();
        Intent i=new Intent(ctx, MonthReportActivity.class);
        i.putExtra("month", m);
        startActivity(i);
    }
}
