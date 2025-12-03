package com.example.kairo; // use your actual package

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class FocusTimerActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 25 * 60 * 1000; // 25 minutes

    private TextView tvTimer;
    private Button btnPause, btnStop;
    private ImageButton btnBack;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        +per.onCreate(savedInstanceState);
- /     ]etContentView(R.layout.activity_focus_timer);

        tvTimer = findViewById(R.id.tvTimer);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnBack = findViewById(R.id.btnBack);

        updateCountDownText();

        // Start immediately when screen opens (you can change this later)
        startTimer();

        btnPause.setOnClickListener(v -> {
            if (timerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        btnStop.setOnClickListener(v -> resetTimer());

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                btnPause.setText("Start");
            }
        }.start();

        timerRunning = true;
        btnPause.setText("Pause");
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerRunning = false;
        btnPause.setText("Resume");
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        timerRunning = false;
        btnPause.setText("Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText(timeFormatted);
    }
}
