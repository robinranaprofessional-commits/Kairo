package com.example.kairo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kairo.FocusTimerActivity;
import com.example.kairo.IntroScreen;
import com.example.kairo.MainActivity;
import com.example.kairo.MyHabitsActivity;
import com.example.kairo.R;
import com.example.kairo.WeeklyProgressReport;

import org.w3c.dom.Text;

public class DailyProgress_1 extends Fragment {

    private Button btnAddHabit;
    private Button btnStartSession;
    private Button btnViewStats;
    private Button btnLogout;
    private TextView tvName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout and store view
        View view = inflater.inflate(R.layout.fragment_daily_progress_1, container, false);

        // Initialize buttons
        btnLogout = view.findViewById(R.id.btnLogout);
        btnAddHabit = view.findViewById(R.id.btnAddHabit);
        btnStartSession = view.findViewById(R.id.btnStartSession);
        btnViewStats = view.findViewById(R.id.btnViewStats);
        tvName = view.findViewById(R.id.tvName);

        SharedPreferences prefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
        String first = prefs.getString("signup_first", "");
        tvName.setText(first);

        btnLogout.setOnClickListener(v -> {
            SharedPreferences logoutPrefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = logoutPrefs.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(requireActivity(), IntroScreen.class);
            startActivity(intent);
            requireActivity().finish();
        });

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
