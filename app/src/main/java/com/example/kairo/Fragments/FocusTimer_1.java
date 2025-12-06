package com.example.kairo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.kairo.IntroScreen;
import com.example.kairo.R;

public class FocusTimer_1 extends Fragment {

    private CountDownTimer timer;
    private long totalTime = 25 * 60 * 1000;       // 25 minutes default
    private long timeRemaining = totalTime;
    private boolean isPaused = false;

    Button btnNewAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_focus_timer_1, container, false);

        //UI ELEMENTS
        btnNewAccount = view.findViewById(R.id.btnNewAccount);
        ImageView studyGif = view.findViewById(R.id.studyGif);
        TextView tvTimer = view.findViewById(R.id.tvTimer);
        Button btnStart = view.findViewById(R.id.btnStart);
        Button btnPause = view.findViewById(R.id.btnPause);
        Button btnStop = view.findViewById(R.id.btnStop);

        // Default GIF when app loads
        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.study)
                .into(studyGif);

        //LOGOUT BUTTON
        btnNewAccount.setOnClickListener(v -> {
            SharedPreferences logoutPrefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
            logoutPrefs.edit().clear().apply();

            Intent intent = new Intent(requireActivity(), IntroScreen.class);
            startActivity(intent);
            requireActivity().finish();
        });

        // START BUTTON
        btnStart.setOnClickListener(v -> {
            startTimer(tvTimer, studyGif);
        });

        // PAUSE BUTTON
        btnPause.setOnClickListener(v -> {
            if (!isPaused) {
                pauseTimer();
                btnPause.setText("Resume");
            } else {
                startTimer(tvTimer, studyGif);
                btnPause.setText("Pause");
            }
            isPaused = !isPaused;
        });

        // STOP BUTTON
        btnStop.setOnClickListener(v -> {
            stopTimer(tvTimer, studyGif);
        });

        return view;
    }

    // TIMER FUNCTIONS

    private void startTimer(TextView tvTimer, ImageView studyGif) {

        if (timer != null) timer.cancel();

        timer = new CountDownTimer(timeRemaining, 1000) {

            @Override
            public void onTick(long millisRemaining) {
                timeRemaining = millisRemaining;

                int minutes = (int) (millisRemaining / 1000 / 60);
                int seconds = (int) (millisRemaining / 1000 % 60);

                tvTimer.setText(String.format("%02d:%02d", minutes, seconds));

                updateGifBasedOnProgress(millisRemaining, studyGif);
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00");

                Glide.with(requireContext())
                        .asGif()
                        .load(R.drawable.yippeecat)
                        .into(studyGif);
            }
        };

        timer.start();
    }

    private void pauseTimer() {
        if (timer != null) timer.cancel();
    }

    private void stopTimer(TextView tvTimer, ImageView studyGif) {
        if (timer != null) timer.cancel();

        timeRemaining = totalTime;

        tvTimer.setText("25:00");

        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.quater)
                .into(studyGif);
    }
    private void updateGifBasedOnProgress(long millisRemaining, ImageView studyGif) {

        float progress = 1 - (float) millisRemaining / totalTime;

        int gifRes;

        if (progress < 0.25f) {
            gifRes = R.drawable.quater;
        } else if (progress < 0.50f) {
            gifRes = R.drawable.half;
        } else if (progress < 0.75f) {
            gifRes = R.drawable.almost;
        } else {
            gifRes = R.drawable.yippeecat;
        }

        Glide.with(requireContext())
                .asGif()
                .load(gifRes)
                .into(studyGif);
    }
}


