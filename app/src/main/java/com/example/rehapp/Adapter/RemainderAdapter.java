package com.example.rehapp.Adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Model.Remainder;
import com.example.rehapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RemainderAdapter extends
        RecyclerView.Adapter<RemainderAdapter.ViewHolder> {

    private List<Remainder> remainderList;
    private int height;
    private int minHeight;
    private FloatingActionButton floatingActionButton;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout1, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Remainder remainder = remainderList.get(position); // prense il primo elemeto della lista.
        // prendo gli elementi dalla classe viewholder, dal row_layout e faccio le set.
        final CardView card =holder.card;
        TextView textView = holder.titolo;
        textView.setText(remainder.getTitle());
        TextView textView1 = holder.informazioni;
        textView1.setText(remainder.getInfo());
        ImageView imageView = holder.remainder_icon;
        imageView.setImageResource(R.drawable.ic_bell1_foreground);
        final ImageView expand= holder.edit_icon;
        final ConstraintLayout l=holder.constraintLayout;
        holder.edit_icon.setRotation(270);

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(l.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(card,new AutoTransition());
                    l.setVisibility(View.VISIBLE);
                    floatingActionButton.setVisibility(View.INVISIBLE);
                    holder.edit_icon.setRotation(90);
                }else{
                    TransitionManager.beginDelayedTransition(card,new AutoTransition());
                    l.setVisibility(View.GONE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    holder.edit_icon.setRotation(270);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remainderList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView card;
        public ConstraintLayout constraintLayout;
        public TextView titolo;
        public TextView informazioni;
        public ImageView remainder_icon;
        public ImageView edit_icon;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view=v;
            card = v.findViewById(R.id.card);
            constraintLayout = v.findViewById(R.id.expand);
            titolo = v.findViewById(R.id.titolo);
            informazioni = v.findViewById(R.id.info);
            remainder_icon = v.findViewById(R.id.icon);
            edit_icon = v.findViewById(R.id.edit);
        }
    }

    public RemainderAdapter(List<Remainder> remainderList, FloatingActionButton f){
        this.remainderList = remainderList;
        this.floatingActionButton=f;
    }

}