
package com.example.kairo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;

public class DailyProgress extends Activity {

    private Button btnAddHabit;
    private Button btnStartSession;
    private Button btnViewStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_progress); // your XML file

        // 1) Get buttons by ID
        btnAddHabit = findViewById(R.id.btnAddHabit);
        btnStartSession = findViewById(R.id.btnStartSession);
        btnViewStats = findViewById(R.id.btnViewStats);

        // 2) Add New Habit → MyHabitsActivity
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyProgress.this, MyHabitsActivity.class);
                startActivity(intent);
            }
        });

        // 3) Start Session → FocusTimerActivity
        btnStartSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyProgress.this, FocusTimerActivity.class);
                startActivity(intent);
            }
        });

        // 4) View Stats → WeeklyProgressReport
        btnViewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyProgress.this, WeeklyProgressReport.class);
                startActivity(intent);
            }
        });
    }
}












