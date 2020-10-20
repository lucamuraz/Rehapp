package com.example.rehapp.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rehapp.R;

public class EnduranceActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;

    private Toolbar toolbar;
    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;

    private long mTimeLeftInMilliseconds= START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endurance);
        toolbar = findViewById(R.id.toolbar2);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView15);
        progressBar = findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Attività endurance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                startEnduActivity();
            }
        });
    }

    private void startEnduActivity() {
        textView.setVisibility(View.VISIBLE);
        countDownTimer= new CountDownTimer(mTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilliseconds=millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                button.setText(R.string.avanti);
                button.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void updateTimerText(){
        int seconds= (int) mTimeLeftInMilliseconds/1000;
        String timeLeftText=""+seconds;
        textView.setText(timeLeftText);
    }
}
