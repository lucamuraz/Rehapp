package com.example.rehapp.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehapp.R;

public class NotificationMessage extends AppCompatActivity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);
        message = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        message.setText(bundle.getString("message"));
    }
}