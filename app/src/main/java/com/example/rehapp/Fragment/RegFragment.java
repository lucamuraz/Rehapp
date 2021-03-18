package com.example.rehapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.Activity.MainActivity;
import com.example.rehapp.AppManager;
import com.example.rehapp.R;


public class RegFragment extends Fragment {

    EditText nome;
    RegFragment fr=this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        final Button avanti = view.findViewById(R.id.Avanti);
        final ImageButton back=view.findViewById(R.id.backReg);
        AppManager.getInstance().getTmpusernameFromDB();
        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO interagire con il database
                nome = requireActivity().findViewById(R.id.Nome);
                String n = nome.getText().toString().replace("/[\u2190-\u21FF]|[\u2600-\u26FF]|[\u2700-\u27BF]|[\u3000-\u303F]|[\u1F300-\u1F64F]|[\u1F680-\u1F6FF]/g", "");
                //TODO passare al secondo fragment
                AppManager.getInstance().setReg(fr);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                RegFragment2 fragment2 = new RegFragment2();
                transaction.replace(R.id.fragment, fragment2);
                transaction.commit();
                // TODO salvo nome e cognome dell'utente
                saveTmpData(n);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                requireActivity().finish();
            }
        });
        return view;
    }


    private void saveTmpData(String nome){
        AppManager.getInstance().saveTmpData(nome, 0);
    }


}