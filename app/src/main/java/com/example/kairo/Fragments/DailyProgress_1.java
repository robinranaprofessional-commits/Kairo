package com.example.kairo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kairo.FocusTimerActivity;
import com.example.kairo.MainActivity;
import com.example.kairo.MyHabitsActivity;
import com.example.kairo.R;
import com.example.kairo.WeeklyProgressReport;

public class DailyProgress_1 extends Fragment {

    private Button btnAddHabit;
    private Button btnStartSession;
    private Button btnViewStats;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout and store view
        View view = inflater.inflate(R.layout.fragment_daily_progress_1, container, false);

        // Initialize buttons
        btnAddHabit = view.findViewById(R.id.btnAddHabit);
        btnStartSession = view.findViewById(R.id.btnStartSession);
        btnViewStats = view.findViewById(R.id.btnViewStats);

        btnAddHabit.setOnClickListener(v -> {
            // Only this one goes to a new Activity
            Intent intent = new Intent(requireActivity(), MyHabitsActivity.class);
            startActivity(intent);
        });

        // Go to Weekly Progress fragment (tab 1)
        btnViewStats.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).goToPage(1);
        });

        // Go to Focus Timer fragment (tab 2)
        btnStartSession.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).goToPage(2);
        });


        return view;
    }
}
