package com.example.rehapp.Model;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.rehapp.Activity.Home;
import com.example.rehapp.AppManager;
import com.example.rehapp.SaveSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DAO {

    private FirebaseDatabase mDB;
    String TAG = "informazioni";
    private Map<String, Object> map = new HashMap<>();
    private List<Activity> activityList;
    private List<Remainder> remainderList;
    String psw = null;
    String name ="";
    String edss ="";
    String listSys="";

    public DAO(){
        initDatabase();
    }

    public void initDatabase(){
        mDB = FirebaseDatabase.getInstance();
    }

    public void register(String name, String userCode, String password, String EDSS, String sisetmi){
        DatabaseReference ref = mDB.getReference("Utenti").child(userCode);
        ref.setValue(userCode);
        DatabaseReference myRef = mDB.getReference("Utenti").child(userCode).child("Nome");
        myRef.setValue(name);
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(userCode).child("Password");
        myRef3.setValue(password);
        DatabaseReference myReF4 = mDB.getReference("Utenti").child(userCode).child("EDSS");
        myReF4.setValue(EDSS);
        DatabaseReference myRef6 = mDB.getReference("Utenti").child(userCode).child("Sistemi_funzionali");
        myRef6.setValue(sisetmi);
        DatabaseReference myReF5 = mDB.getReference("Num_utenti");
        long idx=AppManager.getInstance().getLastIdx();
        myReF5.setValue(idx);
    }

    public void addActivity(String username, String categoria, String id, String type, String duration, String date, String title){
        DatabaseReference myRef0 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id);
        myRef0.setValue(id);
        DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Tipologia");
        myRef1.setValue(type);
        DatabaseReference myRef2 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Titolo");
        myRef2.setValue(title);
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Data");
        myRef3.setValue(date);
        DatabaseReference myRef4 = mDB.getReference("Utenti").child(username).child("Attività").child(categoria).child(id).child("Durata");
        myRef4.setValue(duration);
    }

    // dopo la riuscita del login, recupera le attività svolte dall'utente dal DB
    public void getActivity(String username, final Context ctx) {
        final DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Attività");
        // Read from the database
        activityList=new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                if(map!=null){
                    for(Map.Entry<String, Object> stringObjectEntry : map.entrySet()){
                        Map<String, Object> nn=(Map<String, Object>) stringObjectEntry.getValue();
                        String category=stringObjectEntry.getKey();
                        for (Map.Entry<String, Object> stringObjectEntry1 : nn.entrySet()) {
                            Map<String, Object> p = (Map<String, Object>) stringObjectEntry1.getValue();
                            String id=stringObjectEntry1.getKey();
                            Iterator<Map.Entry<String, Object>> it1 = p.entrySet().iterator();
                            String data = "";
                            String durata = "";
                            String tipologia = "";
                            String titolo = "";

                            while (it1.hasNext()) {
                                Map.Entry<String, Object> pvalue = it1.next();
                                switch (pvalue.getKey()) {
                                    case "Data":
                                        data = pvalue.getValue().toString();
                                        break;
                                    case "Durata":
                                        durata = pvalue.getValue().toString();
                                        break;
                                    case "Tipologia":
                                        tipologia = pvalue.getValue().toString();
                                        break;
                                    case "Titolo":
                                        titolo = pvalue.getValue().toString();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            Activity act = new Activity(id, tipologia, titolo, data, durata, category);
                            activityList.add(act);
                        }
                    }
                }
                writeActivitiesToFile(activityList, ctx);
                AppManager.getInstance().setCurrentWeek();
                myRef.removeEventListener(this);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    // crea un file contenente le attività dell'utente
    public void writeActivitiesToFile(List<Activity> list, Context context) {
        AppManager.getInstance().setActivityList(list);
        StringBuilder data= new StringBuilder();
        for (Activity act: list) {
            data.append(act.toString()).append("\n");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void getRemainder(String username, final Context context){
        final DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Promemoria");
        remainderList = new ArrayList<>();
        //TODO: Read from the database.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                map =(Map<String, Object>) snapshot.getValue();
//                assert map != null;
//                Log.i(TAG,map.toString());
                if(map != null){
                    String titolo;
                    String data = "";
                    String ora = "";

                    for (Map.Entry<String, Object> key : map.entrySet()) {
                        Map<String, Object> map1 = ((Map<String, Object>) key.getValue()); // qui
                        titolo =key.getKey();
                        for (Map.Entry<String, Object> value : map1.entrySet()) {
                            switch (value.getKey()) {
                                case "Data":
                                    data = value.getValue().toString();
                                    break;
                                case "Orario":
                                    ora = value.getValue().toString();
                                    break;
                                default:
                                    break;
                            }
                        }
                        System.out.println(titolo);
                        Log.i(TAG,titolo);
                        Log.i(TAG,data);
                        Log.i(TAG,ora);
                        Remainder remainder = new Remainder(titolo,data,ora);
                        remainderList.add(remainder);
                    }
                }
                writeRemaindersToFile(remainderList,context); //TODO vado a scrivere il contenuto della lista in un file.
                myRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void writeRemaindersToFile(List<Remainder> list, Context context){
        AppManager.getInstance().setRemainderList(list);
        StringBuilder data= new StringBuilder();
        for (Remainder r: list) {
            data.append(r.toString()).append("\n");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("remainders.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void setRemainder(String title,String date, String hour, String username){
        DatabaseReference myRef = mDB.getReference("Utenti").child(username).child("Promemoria").child(title);
        myRef.setValue(title);
        DatabaseReference myRef1 = mDB.getReference("Utenti").child(username).child("Promemoria").child(title).child("Data");
        myRef1.setValue(date);
        DatabaseReference myRef2 = mDB.getReference("Utenti").child(username).child("Promemoria").child(title).child("Orario");
        myRef2.setValue(hour);

    }

    // esegue il login recuperando le informazioni dell'utente dal DB
    public void loginUser(final String username, final String password, final Context context) {
        initDatabase();
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final DatabaseReference myRef = mDB.getReference("Utenti").child(username);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                map =(Map<String, Object>) dataSnapshot.getValue();
                Log.i(TAG, "Value is: " + map);

                for (Map.Entry<String, Object> p : map.entrySet()) {
                    switch (p.getKey()) {
                        case "Password":
                            psw = p.getValue().toString();
                            break;
                        case "Nome":
                            name = p.getValue().toString();
                            break;
                        case "EDSS":
                            edss = p.getValue().toString();
                            break;
                        case "Sistemi_funzionali":
                            listSys = p.getValue().toString();
                            break;
                    }
                    Log.i(TAG, "Value is: " + psw); // legge la password.

                }
                if(username.equals(myRef.getKey()) && password.equals(psw)){
                    saveLogin(username, context);
                    getActivity(username, context);
                    getRemainder(username, context);
                    Intent i = new Intent(context, Home.class);
                    i.putExtra("redirect", 0);
                    context.startActivity(i);
                }else{
                    builder.setMessage("Password errata");
                    AlertDialog alert = builder.create();
                    alert.setTitle("OPS!");
                    alert.show();

                }
                myRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void getNewId(){
        final DatabaseReference myRef = mDB.getReference("Num_utenti");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long id = (long) snapshot.getValue()+1;
                Log.d("firebase", String.valueOf(snapshot.getValue()));
                String idUser="000000000000"+ id;
                Log.d("firebase", idUser);
                String idUserNew=idUser.substring(idUser.length()-7);
                AppManager.getInstance().saveTmpData(idUserNew,1);
                AppManager.getInstance().setLastIdx(id);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void saveLogin(String username, Context ctx){
        SaveSharedPreferences.setUserName(ctx, name);
        SaveSharedPreferences.setUserPassword(ctx, psw);
        SaveSharedPreferences.setUserEdss(ctx, edss);
        SaveSharedPreferences.setUser(ctx, username);
        SaveSharedPreferences.setSystems(ctx, listSys);
    }

    public void saveNewEDSS(String EDSS, String username){
        DatabaseReference myReF4 = mDB.getReference("Utenti").child(username).child("EDSS");
        myReF4.setValue(EDSS);
    }

    public void saveNewPassword(String password, String username){
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Password");
        myRef3.setValue(password);
    }

    public void editActivity(Activity oldActivity, Activity newActivity, String username){
        deleteActivity(oldActivity, username);
        addActivity(username, newActivity.getCategoria(), newActivity.getId(), newActivity.getTipologia(), newActivity.getDurata(), newActivity.getData(), newActivity.getTitolo());
    }

    public void deleteActivity(Activity activity, String username){
        String cat=activity.getCategoria();
        String id=activity.getId();
        DatabaseReference myRef3 = mDB.getReference("Utenti").child(username).child("Attività").child(cat).child(id);
        myRef3.removeValue();
    }

    public void editSystems(String user, String systems) {
        DatabaseReference myRef6 = mDB.getReference("Utenti").child(user).child("Sistemi_funzionali");
        myRef6.setValue(systems);
    }
}
