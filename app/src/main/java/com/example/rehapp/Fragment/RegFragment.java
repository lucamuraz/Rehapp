package com.example.rehapp.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.rehapp.SaveSharedPreferences;
import com.example.rehapp.R;



public class RegFragment extends Fragment {

    EditText nome;
    EditText cognome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        final Button avanti = view.findViewById(R.id.Avanti);
        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO interagire con il database
                nome = getActivity().findViewById(R.id.Nome);
                cognome = getActivity().findViewById(R.id.Cognome);
                String n = nome.getText().toString().replace("/[\u2190-\u21FF]|[\u2600-\u26FF]|[\u2700-\u27BF]|[\u3000-\u303F]|[\u1F300-\u1F64F]|[\u1F680-\u1F6FF]/g", "");
                String c = cognome.getText().toString();
                //TODO passare al secondo fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                RegFragment2 fragment2 = new RegFragment2();
                transaction.replace(R.id.fragment, fragment2);
                transaction.commit();
                // TODO salvo nome e cognome dell'utente
                saveData(n,c);
            }
        });
        return view;
    }

    private void saveData(String nome, String cognome){
        SaveSharedPreferences.setUserName(this.getContext(),nome);
        SaveSharedPreferences.setUserSurname(this.getContext(), cognome);
    }


}