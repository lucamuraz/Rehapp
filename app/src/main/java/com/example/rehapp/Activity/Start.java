package com.example.rehapp.Activity;

import android.os.Bundle;

import androidx.fragment.app.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rehapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Start extends Fragment {

    private static final String TAG = "info";
    FirebaseDatabase mDB;
    EditText n;
    EditText c;
    EditText e;
    EditText u;
    EditText em;
    EditText pwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        initDatabase();

        final Button vai = view.findViewById(R.id.continua);
        vai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO parte lo Start1.
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                u = getActivity().findViewById(R.id.username);
                String username = u.getText().toString();
                DatabaseReference myRef4 = mDB.getReference("Utenti").child(username);
                myRef4.setValue(username);

                n = getActivity().findViewById(R.id.nome1);
                String nome = n.getText().toString();
                DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Nome");
                myRef.setValue(nome);

                c = getActivity().findViewById(R.id.cognome);
                String cognome = c.getText().toString();
                DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Cognome");
                myRef1.setValue(cognome);

                e = getActivity().findViewById(R.id.numero);
                int eta = Integer.parseInt(e.getText().toString());
                DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Età");
                myRef3.setValue(eta);

                em = getActivity().findViewById(R.id.email);
                String email = em.getText().toString();
                DatabaseReference myRef5 = mDB.getReference("Utenti").child(username).child("Email");
                myRef5.setValue(email);

                pwd = getActivity().findViewById(R.id.password);
                String password = pwd.getText().toString();
                DatabaseReference myRef6 = mDB.getReference("Utenti").child(username).child("Password");
                myRef6.setValue(password);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Start2 second = new Start2();
                second.setArguments(bundle);
                transaction.replace(R.id.fragment,second);
                transaction.commit();

            }
        });
        return view;
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }
}