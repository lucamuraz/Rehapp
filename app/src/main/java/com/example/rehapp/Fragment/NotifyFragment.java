package com.example.rehapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehapp.Activity.AddNotification;
import com.example.rehapp.Adapter.RemainderAdapter;
import com.example.rehapp.AppManager;
import com.example.rehapp.Model.Remainder;
import com.example.rehapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NotifyFragment extends Fragment {

    List<Remainder> remainderList = new ArrayList<>();
    RecyclerView recyclerView;

    public NotifyFragment() {
        // Required empty public constructor
    }


    public static NotifyFragment newInstance() {
        return new NotifyFragment();
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

        //TODO lista gia presente:
        remainderList = AppManager.getInstance().getRemainderList(); // preno la lista piena
        if(remainderList != null){
            RemainderAdapter adapter = new RemainderAdapter(remainderList); // la visulizzo con l'adpter
            ImageView image = rootView.findViewById(R.id.icona);
            TextView text = rootView.findViewById(R.id.testo);
            text.setVisibility(View.INVISIBLE);
            image.setVisibility(View.INVISIBLE);
            recyclerView = rootView.findViewById(R.id.remainder_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        }
        //se non è piena ritono il fregament vuoto.

        return rootView;
    }


}