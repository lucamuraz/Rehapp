package com.example.rehapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rehapp.R;
import com.example.rehapp.Model.Remainder;

import java.util.List;

public class RemainderAdapter extends
        RecyclerView.Adapter<RemainderAdapter.ViewHolder> {

    private List<Remainder> remainderList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Remainder remainder = remainderList.get(position); // prense il primo elemeto della lista.
        // prendo gli elementi dalla classe viewholder, dal row_layout e faccio le set.
        TextView textView = holder.titolo;
        textView.setText(remainder.getTitle());
        TextView textView1 = holder.informazioni;
        textView1.setText(remainder.getInfo());
        ImageView imageView = holder.remainder_icon;
        //imageView.setImageResource(R.drawable.ic_baseline_notifications_active_24);
        imageView.setImageResource(R.drawable.ic_bell1_foreground);

    }

    @Override
    public int getItemCount() {
        return remainderList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titolo;
        public TextView informazioni;
        public ImageView remainder_icon;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view=v;
            titolo = (TextView) v.findViewById(R.id.activity_firstLine);
            informazioni = (TextView) v.findViewById(R.id.activity_secondLine);
            remainder_icon = (ImageView)v.findViewById(R.id.reportTime_icon);
        }
    }

    public RemainderAdapter(List<Remainder> remainderList){
        this.remainderList = remainderList;
    }

}