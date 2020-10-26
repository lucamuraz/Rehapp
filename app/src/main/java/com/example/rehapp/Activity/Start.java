package com.example.rehapp.Activity;

import android.os.Bundle;

import androidx.fragment.app.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Start extends Fragment {

    private static final String TAG = "info";
    FirebaseDatabase mDB;
    EditText nome;
    EditText cognome;
    EditText età;
    EditText username;
    EditText email;
    EditText pwd;

    String name;
    String surname;
    String age;
    String user;
    String mail;
    String password;

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

                username = getActivity().findViewById(R.id.username);
                user = username.getText().toString();
                DatabaseReference myRef4 = mDB.getReference("Utenti").child(user);
                myRef4.setValue(user);

                nome = getActivity().findViewById(R.id.nome1);
                name = nome.getText().toString();
                DatabaseReference myRef = mDB.getReference("Utenti").child(user).child("Nome");
                myRef.setValue(name);

                cognome = getActivity().findViewById(R.id.cognome);
                surname = cognome.getText().toString();
                DatabaseReference myRef1 = mDB.getReference("Utenti").child(user).child("Cognome");
                myRef1.setValue(surname);

                età = getActivity().findViewById(R.id.numero);
                age = età.getText().toString();
                DatabaseReference myRef3 = mDB.getReference("Utenti").child(user).child("Età");
                myRef3.setValue(age);

                email = getActivity().findViewById(R.id.email);
                mail = email.getText().toString();
                DatabaseReference myRef5 = mDB.getReference("Utenti").child(user).child("Email");
                myRef5.setValue(mail);

                pwd = getActivity().findViewById(R.id.password);
                password = pwd.getText().toString();
                DatabaseReference myRef6 = mDB.getReference("Utenti").child(user).child("Password");
                myRef6.setValue(password);
                Start2 second = new Start2();
                transaction.replace(R.id.fragment,second);
                transaction.commit();

                saveLogin();

            }
        });
        return view;
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }

    private void saveLogin(){
        SaveSharedPreferences.setUserName(this.getContext(), name);
        SaveSharedPreferences.setUserSurname(this.getContext(), surname);
        SaveSharedPreferences.setUserEmail(this.getContext(), mail);
        SaveSharedPreferences.setUserPassword(this.getContext(), password);
        SaveSharedPreferences.setUser(this.getContext(), user);
    }
}