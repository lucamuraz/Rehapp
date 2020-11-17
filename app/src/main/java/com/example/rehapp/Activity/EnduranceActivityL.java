package com.example.rehapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.Activity;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.Model.TrainingMaker;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EnduranceActivityL extends AppCompatActivity {

    private static final long COUNTDOWN_TO_START = 4000; // 4 sec
    private static final long COUNTDOWN_WARMUP = 21000; //301000 5 min
    private static final long COUNTDOWN_REST = 11000; //61000 1 min
    private static final long COUNTDOWN_COOLDOWN = 21000; //301000 5 min

    Context ctx=this;

    private Button buttonStart;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private CardView cardView;
    private Button buttonPauseResume;
    private Button buttonStop;
    private Chronometer chronometer;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView txt7;
    private TextView txt8;
    private TextView txt9;
    private TextView txtsave;
    private TextView txtDelete;
    private View div1;
    private View div2;
    private Button buttonSave;
    private Button buttonDelete;
    private ImageButton info;
    private ImageButton info1;
    private ImageView image;

    private boolean timerRunning;
    private boolean timeComplete;
    private long mTimeLeftInMilliseconds= COUNTDOWN_TO_START;
    private int stepNum=0;
    private int totalTimeCount=0;
    private boolean start=false;
    private boolean crono=false;
    private boolean cronoRunning;
    private long pauseOffset=0;
    private boolean exit=false;
    private List<TrainingMaker.Exercize> exercizeList;

    private TextView time;
    private TextView desc;
    private String timet;
    private String descText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endurancel);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        exercizeList= TrainingMaker.getInstance().getNewExercizeList("E", 5);

        Toolbar toolbar = findViewById(R.id.toolbar31);
        buttonStart = findViewById(R.id.buttonStart1);
        buttonPauseResume = findViewById(R.id.buttonPauseResume1);
        buttonStop = findViewById(R.id.buttonStop1);
        timerTextView = findViewById(R.id.textView151);
        cardView = findViewById(R.id.dati1);
        chronometer = findViewById(R.id.cronometer1);
        txt1=findViewById(R.id.endu1);
        txt2=findViewById(R.id.endu2);
        txt3=findViewById(R.id.endu3);
        txt4=findViewById(R.id.endu4);
        txt5=findViewById(R.id.endu5);
        txt6=findViewById(R.id.endu6);
        txt7=findViewById(R.id.endu7);
        txt8=findViewById(R.id.endu8);
        txt9=findViewById(R.id.endu9);
        div1=findViewById(R.id.divider1);
        div2=findViewById(R.id.divider31);
        buttonSave =findViewById(R.id.buttonSave1);
        buttonDelete =findViewById(R.id.buttonDelete1);
        time=findViewById(R.id.textView41);
        desc=findViewById(R.id.textView51);
        txtsave=findViewById(R.id.saveText);
        txtDelete=findViewById(R.id.deleteText);
        info=findViewById(R.id.infoTot);
        info1=findViewById(R.id.infoTot1l);
        image=findViewById(R.id.exPicL);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Allenamento di resistenza");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepNum==0){
                    buttonStart.setVisibility(View.INVISIBLE);
                    nextStep();
                }else{
                    start=true;
                    nextStep();
                }
            }
        });

        buttonPauseResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning || cronoRunning){
                    buttonPauseResume.setText(R.string.Resume);
                    if(crono){
                        pauseCrono();
                        buttonStop.setText(R.string.stop);
                    }else{
                        pauseTimer();
                    }
                }else{
                    buttonPauseResume.setText(R.string.Pause);
                    if(crono){
                        buttonStop.setText(R.string.fatto);
                        startCrono();
                    }else{
                        resumeTimer();
                    }
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonStop.getText().equals("STOP")){
                    stopOption();
                }else{ //equals FATTO
                    pauseCrono();
                    totalTimeCount+=pauseOffset/1000;
                    timet="Tempo totale: "+totalTimeCount+" sec";
                    time.setText(timet);
                    startRest();
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveActivity();
                String toastMessage = "Allenamento salvato";
                Toast mToast = Toast.makeText(ctx, toastMessage, Toast.LENGTH_LONG);
                mToast.show();
                Intent i = new Intent(ctx, Home.class);
                startActivity(i);
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastMessage = "Allenamento eliminato";
                Toast mToast = Toast.makeText(ctx, toastMessage, Toast.LENGTH_LONG);
                mToast.show();
                Intent i = new Intent(ctx, Home.class);
                startActivity(i);
                finish();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exit){
                    txtsave.setVisibility(View.INVISIBLE);
                    txtDelete.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.INVISIBLE);
                    buttonSave.setVisibility(View.INVISIBLE);
                    buttonDelete.setVisibility(View.INVISIBLE);
                    //time.setVisibility(View.VISIBLE);
                    desc.setVisibility(View.VISIBLE);
                    info1.setVisibility(View.VISIBLE);
                    image.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                }else{
                    Intent i=new Intent(ctx, ExplainActivity.class);
                    startActivity(i);
                }
            }
        });

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cronoRunning){
                    pauseCrono();
                }else if(timerRunning){
                    pauseTimer();
                }
                Intent i=new Intent(ctx, ExplainActivity.class);
                startActivity(i);
                buttonPauseResume.setText(R.string.Resume);
            }
        });
    }

    public void pauseTimer(){
        countDownTimer.cancel();
        timerRunning=false;
    }

    public void resumeTimer(){
        timerRunning=true;
        countDownTimer= new CountDownTimer(mTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilliseconds=millisUntilFinished;
                updateTimerText();
            }
            @Override
            public void onFinish() {
                timerRunning=false;
                stepNum++;
                nextStep();
            }
        }.start();
    }

    private void updateTimerText(){
        int seconds= (int) (mTimeLeftInMilliseconds%60000)/1000;
        int minutes=(int) mTimeLeftInMilliseconds/60000;
        String timeLeftText="";
        if(timeComplete){
            if(minutes<10){
                timeLeftText += "0" +minutes+":";
            }else{
                timeLeftText += minutes+":";
            }
            if(seconds<10){
                timeLeftText +="0"+seconds;
            }else{
                timeLeftText += seconds;
            }
        }else{
            timeLeftText+=seconds;
        }

        timerTextView.setText(timeLeftText);
    }

    public void nextStep(){
        switch(stepNum){
            case 0: //inizia l'allenamento
                //time.setVisibility(View.VISIBLE);
                image.setImageResource(R.drawable.ic_start_foreground);
                desc.setVisibility(View.VISIBLE);
                timeComplete=false;
                cardView.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.VISIBLE);
                timerTextView.setTextColor(getResources().getColor(R.color.red));
                resumeTimer();
                break;
            case 1: //inizia il warm up
                image.setImageResource(exercizeList.get(0).getImageId());
                info1.setVisibility(View.VISIBLE);
                buttonPauseResume.setVisibility(View.VISIBLE);
                buttonStop.setVisibility(View.VISIBLE);
                descText="Riscaldamento";
                desc.setText(descText);
                timeComplete=true;
                mTimeLeftInMilliseconds=COUNTDOWN_WARMUP;
                timerTextView.setTextColor(getResources().getColor(R.color.nero));
                //todo mostra spiegazione esercizio
                resumeTimer();
                break;
            case 2:
                descText="Riposo";
                image.setImageResource(R.drawable.ic_rest_foreground);
                desc.setText(descText);
                mTimeLeftInMilliseconds=COUNTDOWN_REST;
                totalTimeCount+=(COUNTDOWN_WARMUP-1000)/1000; //aggiungo al totale il tempo del warm up
                timet="Tempo totale: "+totalTimeCount +" sec";
                time.setText(timet);
                resumeTimer();
                break;
            case 3: //inizia le ripetizioni
                if(start){
                    buttonStop.setText(R.string.fatto);
                    stepNum++;
                    start=false;
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setText(R.string.fatto);
                    buttonStop.setVisibility(View.VISIBLE);
                    crono=true;
                    startCrono(); //dentro il counter faccio ppi partire il countdown del riposo*/
                }else{
                    descText="Attività";
                    desc.setText(descText);
                    image.setImageResource(exercizeList.get(1).getImageId());
                    totalTimeCount+=(COUNTDOWN_REST-1000)/1000; // aggiungo al tempo totale dell'allenamento quello trascorso con il riposo
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    timerTextView.setVisibility(View.INVISIBLE);
                    chronometer.setVisibility(View.VISIBLE);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                    //todo mostra spiegazione esercizio
                }
                break;
            case 4: //cooldown
                if(start){
                    buttonStop.setText(R.string.stop);
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    mTimeLeftInMilliseconds=COUNTDOWN_COOLDOWN;
                    resumeTimer();
                    start=false;
                }else{
                    descText="Defaticamento";
                    desc.setText(descText);
                    image.setImageResource(exercizeList.get(2).getImageId());
                    totalTimeCount+=(COUNTDOWN_REST-1000)/1000;
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                }
                break;
            case 5:
                if(start){
                    buttonStop.setText(R.string.stop);
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    mTimeLeftInMilliseconds=COUNTDOWN_COOLDOWN;
                    resumeTimer();
                }else{
                    descText="Stretching";
                    desc.setText(descText);
                    image.setImageResource(exercizeList.get(3).getImageId());
                    totalTimeCount+=(COUNTDOWN_COOLDOWN-1000)/1000;
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                descText="Allenamento finito";
                desc.setText(descText);
                totalTimeCount+=(COUNTDOWN_COOLDOWN-1000)/1000;
                timet="Tempo totale: "+totalTimeCount +" sec";
                time.setText(timet);
                stopOption();
                break;
        }
    }

    public void startCrono(){
        if(!cronoRunning){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            cronoRunning=true;
        }
    }

    public void pauseCrono(){
        if(cronoRunning){
            chronometer.stop();
            pauseOffset= SystemClock.elapsedRealtime() - chronometer.getBase();
            cronoRunning=false;
        }
    }

    public void startRest(){
        descText="Riposo";
        image.setImageResource(R.drawable.ic_rest_foreground);
        desc.setText(descText);
        mTimeLeftInMilliseconds=COUNTDOWN_REST;
        buttonStop.setText(R.string.stop);
        crono=false;
        chronometer.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        stepNum--;
        resumeTimer();
    }

    public void stopOption(){
        if(timerRunning){
            pauseTimer();
            buttonPauseResume.setText(R.string.Resume);
        }
        exit=true;
        image.setVisibility(View.VISIBLE);
        time.setVisibility(View.INVISIBLE);
        desc.setVisibility(View.INVISIBLE);
        info1.setVisibility(View.INVISIBLE);
        info.setImageResource(R.drawable.ic_close_foreground);
        txt1.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        txt3.setVisibility(View.INVISIBLE);
        txt4.setVisibility(View.INVISIBLE);
        txt5.setVisibility(View.INVISIBLE);
        txt6.setVisibility(View.INVISIBLE);
        txt7.setVisibility(View.INVISIBLE);
        txt8.setVisibility(View.INVISIBLE);
        txt9.setVisibility(View.INVISIBLE);
        div1.setVisibility(View.INVISIBLE);
        div2.setVisibility(View.INVISIBLE);
        txtsave.setVisibility(View.VISIBLE);
        txtDelete.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
        buttonSave.setVisibility(View.VISIBLE);
        buttonDelete.setVisibility(View.VISIBLE);
        buttonPauseResume.setVisibility(View.INVISIBLE);
        buttonStop.setVisibility(View.INVISIBLE);
    }

    private void saveActivity(){
        DAO m=new DAO();

        if(timerRunning){
            if(stepNum==1||stepNum==5 || stepNum==4){
                totalTimeCount+=(COUNTDOWN_COOLDOWN-mTimeLeftInMilliseconds)/1000;
            }else{
                totalTimeCount+=(COUNTDOWN_REST-mTimeLeftInMilliseconds)/1000;
            }
        }else{
            totalTimeCount+=pauseOffset/1000;
        }

        String username= SaveSharedPreferences.getUser(ctx);
        String categoria="Allenamento";

        String id;
        id= AppManager.getInstance().getLastId();
        AppManager.getInstance().setLastId("00"+id.substring(2));

        String typeAct="Resistenza";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        Date dateobj = new Date();
        String data =df.format(dateobj);
        String titolo="Allenamento di resistenza del "+data.substring(0,2)+"/"+data.substring(3,5);
        String ore=""+totalTimeCount/3600;
        String min=""+(totalTimeCount/60)%60;
        if(ore.equals("0")){
            ore+="0";
        }
        if(min.length()==1){
            min="0"+min;
        }
        String durata= ore+":"+min;

        m.addActivity(username, categoria, id, typeAct, durata, data, titolo);
        AppManager.getInstance().addOnActivityList(new Activity(id, typeAct, titolo, data, durata, categoria), ctx);
    }

}
