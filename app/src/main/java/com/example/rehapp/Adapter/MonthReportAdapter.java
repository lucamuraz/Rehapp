package com.example.rehapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Model.MonthReport;
import com.example.rehapp.R;

import java.util.List;

public class MonthReportAdapter extends
        RecyclerView.Adapter<MonthReportAdapter.ViewHolder> {

    private List<MonthReport> monthReportList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.block_layout, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonthReport monthReport = monthReportList.get(position); // prense il primo elemeto della lista.
        // prendo gli elementi dalla classe viewholder, dal row_layout e faccio le set.
        TextView textView = holder.data;
        textView.setText(monthReport.getTitle());
        TextView textView1 = holder.tempo;
        textView1.setText(monthReport.getTimeActivities());
        TextView textView2 = holder.numero;
        textView2.setText(monthReport.getNbActivities());
        ImageView imageView = holder.activity_icon;
        imageView.setImageResource(R.drawable.ic_active_foreground);
        ImageView imageView2 = holder.time_icon;
        imageView2.setImageResource(R.drawable.ic_chrono_foreground);
    }

    @Override
    public int getItemCount() {
        return monthReportList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView data;
        public TextView tempo;
        public TextView numero;
        public ImageView activity_icon;
        public ImageView time_icon;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view=v;
            data = v.findViewById(R.id.activity_firstLine);
            tempo = v.findViewById(R.id.activity_secondLine);
            numero = v.findViewById(R.id.activity_secondLine2);
            time_icon = v.findViewById(R.id.reportTime_icon);
            activity_icon = v.findViewById(R.id.reportNum_icon);
        }
    }

    public MonthReportAdapter(List<MonthReport> monthReportList){
        this.monthReportList = monthReportList;
    }

}
