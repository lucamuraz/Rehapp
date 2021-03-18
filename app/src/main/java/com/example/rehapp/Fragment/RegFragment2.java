package com.example.rehapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.AppManager;
import com.example.rehapp.R;


public class RegFragment2 extends Fragment {

    TextView username;
    EditText password;
    RegFragment2 fr=this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg2, container, false);
        final ImageButton back=view.findViewById(R.id.imageButton);
        final Button prosegui = view.findViewById(R.id.Prosegui);
        username = view.findViewById(R.id.Username);
        final String tmpUser= AppManager.getInstance().getTmpUsername();
        username.setText(tmpUser);

        prosegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                //TODO interagire con il database
                password = requireActivity().findViewById(R.id.Password);
                String p = password.getText().toString();
                //TODO passare al secondo fragment
                AppManager.getInstance().setReg2(fr);
                RegFragment3 fragment3 = new RegFragment3();
                transaction.replace(R.id.fragment, fragment3);
                transaction.commit();
                //TODO salvare le credenziali di accesso dell'utente
                saveTmpData(p, tmpUser);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                RegFragment fragment = AppManager.getInstance().getReg();
                transaction.replace(R.id.fragment, fragment);
                transaction.commit();
            }
        });
        return view;
    }

    private void saveTmpData(String password, String userName){
        AppManager.getInstance().saveTmpData(password, 2);
    }
}