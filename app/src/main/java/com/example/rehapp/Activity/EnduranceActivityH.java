package com.example.rehapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class EnduranceActivityH extends AppCompatActivity {

    private static final long COUNTDOWN_TO_START = 4000;
    private static final long COUNTDOWN_WARMUP = 21000; //301000 5 min
    private static final long COUNTDOWN_REST = 11000; //61000 1 min
    private static final long COUNTDOWN_COOLDOWN = 21000; //301000 5 min
    private static final long COUNTDOWN_ACTIVITY = 21000; //181000 3 min

    Context ctx=this;

    private Button buttonStart;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private CardView cardView;
    private Button buttonPauseResume;
    private Button buttonStop;
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
    private boolean restToDO;
    private boolean timeComplete;
    private long mTimeLeftInMilliseconds= COUNTDOWN_TO_START;
    private int stepNum=0;
    private int totalTimeCount=0;
    private int repNum=0;
    private boolean start=false;
    private boolean exit=false;
    private List<TrainingMaker.Exercize> exercizeList;

    private TextView time;
    private TextView desc;
    private String timet;
    private String descText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enduranceh);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        exercizeList= TrainingMaker.getInstance().getNewExercizeList("E", 7);

        Toolbar toolbar = findViewById(R.id.toolbar31h);
        buttonStart = findViewById(R.id.buttonStart1h);
        buttonPauseResume = findViewById(R.id.buttonPauseResume1h);
        buttonStop = findViewById(R.id.buttonStop1h);
        timerTextView = findViewById(R.id.textView151h);
        cardView = findViewById(R.id.dati1h);
        txt1=findViewById(R.id.endu1h);
        txt2=findViewById(R.id.endu2h);
        txt3=findViewById(R.id.endu3h);
        txt4=findViewById(R.id.endu4h);
        txt5=findViewById(R.id.endu5h);
        txt6=findViewById(R.id.endu6h);
        txt7=findViewById(R.id.endu7h);
        txt8=findViewById(R.id.endu8h);
        txt9=findViewById(R.id.endu9h);
        div1=findViewById(R.id.divider1h);
        div2=findViewById(R.id.divider31h);
        buttonSave =findViewById(R.id.buttonSave1h);
        buttonDelete =findViewById(R.id.buttonDelete1h);
        time=findViewById(R.id.textView41h);
        desc=findViewById(R.id.textView51h);
        txtsave=findViewById(R.id.saveTexth);
        txtDelete=findViewById(R.id.deleteTexth);
        info=findViewById(R.id.infoTot);
        info1=findViewById(R.id.infoTot1h);
        image=findViewById(R.id.exPicH);

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
                if(timerRunning){
                    buttonPauseResume.setText(R.string.Resume);
                    pauseTimer();
                }else{
                    buttonPauseResume.setText(R.string.Pause);
                    resumeTimer();
                }
            }
        });

        buttonStop.setText(R.string.stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopOption();
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
                    buttonStop.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                }else{
                    Intent i=new Intent(ctx, ExplainActivity.class);
                    startActivity(i);
                }
            }
        });

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning){
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
                if(restToDO){
                    totalTimeCount+=(COUNTDOWN_ACTIVITY-1000)/1000;
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    restToDO =false;
                    startRest();
                }else{
                    nextStep();
                }
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

    public void startRest(){
        image.setImageResource(R.drawable.ic_rest_foreground);
        mTimeLeftInMilliseconds=COUNTDOWN_REST;
        descText="Riposo";
        desc.setText(descText);
        resumeTimer();
    }

    public void stopOption(){
        if(timerRunning){
            pauseTimer();
            buttonPauseResume.setText(R.string.Resume);
        }
        exit=true;
        image.setVisibility(View.INVISIBLE);
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

        if(stepNum==2||stepNum==5){
            totalTimeCount+=(COUNTDOWN_COOLDOWN-mTimeLeftInMilliseconds)/1000;
        }else if(restToDO) {
            totalTimeCount+=(COUNTDOWN_ACTIVITY-mTimeLeftInMilliseconds)/1000;
        }else{
            totalTimeCount+=(COUNTDOWN_REST-mTimeLeftInMilliseconds)/1000;
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
                stepNum++;
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
                stepNum++;
                resumeTimer();
                break;
            case 2:
                descText="Riposo";
                image.setImageResource(R.drawable.ic_rest_foreground);
                desc.setText(descText);
                mTimeLeftInMilliseconds=COUNTDOWN_REST;
                totalTimeCount+=(COUNTDOWN_WARMUP-1000)/1000;
                timet="Tempo totale: "+totalTimeCount +" sec";
                time.setText(timet);
                stepNum++;
                resumeTimer();
                break;
            case 3: //inizia le ripetizioni
                if(start){
                    repNum++;
                    start=false;
                    if(repNum==3){ //sono arrivato all'ultima ripetizione
                        stepNum++;
                    }
                    descText="Attività "+repNum;
                    desc.setText(descText);
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    timerTextView.setVisibility(View.VISIBLE);
                    mTimeLeftInMilliseconds=COUNTDOWN_ACTIVITY;
                    restToDO =true;
                    resumeTimer();
                }else{
                    descText="Prossima attività";
                    desc.setText(descText);
                    image.setImageResource(exercizeList.get(1).getImageId());
                    totalTimeCount+=(COUNTDOWN_REST-1000)/1000; // se sono alla prima ripetizione aggiungo al tempo totale dell'allenamento quello trascorso con il warmup
                    //totalTimeCount+=(COUNTDOWN_ACTIVITY-1000)/1000;
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonPauseResume.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                    //todo mostra spiegazione esercizio
                }
                break;
            case 4: //cooldown
                if(start){
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonPauseResume.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    mTimeLeftInMilliseconds=COUNTDOWN_COOLDOWN;
                    stepNum++;
                    totalTimeCount+=(COUNTDOWN_REST-1000)/1000;
                    timet="Tempo totale: "+totalTimeCount +" sec";
                    time.setText(timet);
                    resumeTimer();
                }else{
                    descText="Defaticamento";
                    desc.setText(descText);
                    image.setImageResource(exercizeList.get(2).getImageId());
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

}