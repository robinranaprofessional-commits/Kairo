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
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.kairo.IntroScreen;
import com.example.kairo.R;

public class FocusTimer_1 extends Fragment {

    // Timer state
    private CountDownTimer timer;
    private long totalTime = 25 * 60 * 1000;   // default timer 25 min
    private long timeRemaining = totalTime;
    private boolean isPaused = false;

    // Fixed durations for users
    private final int[] focusMinutes = {15, 25, 45, 90};

    Button btnNewAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_focus_timer_1, container, false);


        btnNewAccount = view.findViewById(R.id.btnNewAccount);
        ImageView studyGif = view.findViewById(R.id.studyGif);
        TextView tvTimer = view.findViewById(R.id.tvTimer);

        Button btnStart = view.findViewById(R.id.btnStart);
        Button btnPause = view.findViewById(R.id.btnPause);
        Button btnStop = view.findViewById(R.id.btnStop);
        SeekBar seekBarTime = view.findViewById(R.id.seekBarTime);


        tvTimer.setText("25:00");

        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.quater)
                .into(studyGif);

        // ---------------- SEEK BAR ----------------
        seekBarTime.setMax(3);
        seekBarTime.setProgress(1); // 25 minutes

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes = focusMinutes[progress];

                totalTime = minutes * 60 * 1000;
                timeRemaining = totalTime;

                // Update the SAME timer TextView
                tvTimer.setText(String.format("%02d:00", minutes));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //Logout
        btnNewAccount.setOnClickListener(v -> {
            SharedPreferences prefs =
                    requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
            prefs.edit().clear().apply();

            startActivity(new Intent(requireActivity(), IntroScreen.class));
            requireActivity().finish();
        });

        //Start button
        btnStart.setOnClickListener(v -> {
            seekBarTime.setEnabled(false);
            startTimer(tvTimer, studyGif, seekBarTime);
        });

        //pause or resume
        btnPause.setOnClickListener(v -> {
            if (!isPaused) {
                pauseTimer();
                btnPause.setText("Resume");
            } else {
                startTimer(tvTimer, studyGif, seekBarTime);
                btnPause.setText("Pause");
            }
            isPaused = !isPaused;
        });

        //Stop
        btnStop.setOnClickListener(v -> {
            stopTimer(tvTimer, studyGif, seekBarTime);
            btnPause.setText("Pause");
            isPaused = false;
        });

        return view;
    }

    //Timer logic

    private void startTimer(TextView tvTimer, ImageView studyGif, SeekBar seekBarTime) {

        if (timer != null) timer.cancel();

        timer = new CountDownTimer(timeRemaining, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;

                int minutes = (int) (millisUntilFinished / 1000 / 60);
                int seconds = (int) (millisUntilFinished / 1000 % 60);

                tvTimer.setText(String.format("%02d:%02d", minutes, seconds));

                updateGifBasedOnProgress(millisUntilFinished, studyGif);
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00");
                seekBarTime.setEnabled(true);

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

    private void stopTimer(TextView tvTimer, ImageView studyGif, SeekBar seekBarTime) {

        if (timer != null) timer.cancel();

        timeRemaining = totalTime;
        seekBarTime.setEnabled(true);

        int minutes = (int) (totalTime / 1000 / 60);
        tvTimer.setText(String.format("%02d:00", minutes));

        Glide.with(requireContext())
                .asGif()
                .load(R.drawable.quater)
                .into(studyGif);
    }

    // GIF progression, we have different GIFs at different progression of timer

    private void updateGifBasedOnProgress(long millisRemaining, ImageView studyGif) {

        float progress = 1f - (float) millisRemaining / totalTime;

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
