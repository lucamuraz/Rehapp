package com.example.rehapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Model.TrainingMaker;
import com.example.rehapp.R;

import java.util.List;

public class ExercizeAdapter extends RecyclerView.Adapter<ExercizeAdapter.ViewHolder> {

    private List<TrainingMaker.Exercize> exrecizeList;

    @NonNull
    @Override
    public ExercizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.training_layout, parent, false);

        // Return a new holder instance
        return new ExercizeAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercizeAdapter.ViewHolder holder, int position) {
        TrainingMaker.Exercize exrecize = exrecizeList.get(position); // prense il primo elemeto della lista.
        // prendo gli elementi dalla classe viewholder, dal row_layout e faccio le set.
        TextView textView = holder.titolo;
        textView.setText(exrecize.getTitle());
        TextView textView1 = holder.informazioni;
        textView1.setText(exrecize.getDescription());
    }

    @Override
    public int getItemCount() {
        return exrecizeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titolo;
        public TextView informazioni;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view=v;
            titolo = v.findViewById(R.id.activity_firstLine);
            informazioni = v.findViewById(R.id.activity_secondLine);
        }
    }

    public ExercizeAdapter(List<TrainingMaker.Exercize> exrecizeList){
        this.exrecizeList = exrecizeList;
    }
}
