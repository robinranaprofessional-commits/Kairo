package com.example.kairo;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DailyProgress extends AppCompatActivity {

    Button btnStartSession, btnViewStats;
    TextView tvAddHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_progress);

        btnStartSession = findViewById(R.id.btnStartSession);
        btnViewStats = findViewById(R.id.btnViewStats);
        tvAddHabit = findViewById(R.id.tvAddHabit);

    }
}



