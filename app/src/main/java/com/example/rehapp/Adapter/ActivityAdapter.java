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

import java.util.List;

import com.example.rehapp.Model.Activity;

public class ActivityAdapter extends
        RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<Activity> activityList;
    final private ItemClickListener onClickListener;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Activity activity=activityList.get(position);
        TextView textView= holder.titolo;
        textView.setText(activity.getDescription());
        TextView textView1=holder.descrizione;
        textView1.setText(activity.getInfo());
        ImageView imageView=holder.activity_icon;
        if(activity.getCategoria().equals("Allenamento")){
            imageView.setImageResource(R.drawable.ic_activity_foreground);
        }else if(activity.getCategoria().equals("Seduta riabilitativa")){
            imageView.setImageResource(R.drawable.ic_physio_foreground);
        }else{
            imageView.setImageResource(R.drawable.ic_bell_foreground);
            textView.setText(activity.getCategoria());
            textView1.setText(activity.getTipologia());
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titolo;
        public TextView descrizione;
        public ImageView activity_icon;
        public View view;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            view=itemView;
            titolo = (TextView) itemView.findViewById(R.id.activity_firstLine);
            descrizione = (TextView) itemView.findViewById(R.id.activity_secondLine);
            activity_icon = (ImageView) itemView.findViewById(R.id.reportTime_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClick(clickedPosition);
            Activity clickedDataItem=activityList.get(clickedPosition);
        }
    }

    public interface ItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    // Pass in the contact array into the constructor
    public ActivityAdapter(List<Activity> activities, ItemClickListener onClickListener) {
        activityList = activities;
        this.onClickListener = onClickListener;
    }
}
