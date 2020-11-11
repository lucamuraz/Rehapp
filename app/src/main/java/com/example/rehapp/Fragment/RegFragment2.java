package com.example.rehapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;


public class RegFragment2 extends Fragment {

    EditText username;
    EditText email;
    EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg2, container, false);


        final Button prosegui = view.findViewById(R.id.Prosegui);
        prosegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                //TODO interagire con il database
                username = requireActivity().findViewById(R.id.Username);
                email = requireActivity().findViewById(R.id.Email);
                password = requireActivity().findViewById(R.id.Password);
                String u = username.getText().toString();
                String e = email.getText().toString();
                String p = password.getText().toString();
                //TODO passare al secondo fragment
                RegFragment3 fragment3 = new RegFragment3();
                transaction.replace(R.id.fragment, fragment3);
                transaction.commit();
                //TODO salvare le credenziali di accesso dell'utente
                saveData(u,e,p);
            }
        });
        return view;
    }

    private void saveData(String username, String email, String password){
        SaveSharedPreferences.setUser(this.getContext(), username);
        SaveSharedPreferences.setUserEmail(this.getContext(),email);
        SaveSharedPreferences.setUserPassword(this.getContext(), password);
    }
}