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


import com.example.kairo.Habit;
import com.example.kairo.IntroScreen;
import com.example.kairo.MainActivity;
import com.example.kairo.MyHabitsActivity;
import com.example.kairo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class DailyProgress_1 extends Fragment {

    private Button btnAddHabit;
    private Button btnStartSession;
    private Button btnViewStats;
    private Button btnLogout;
    private TextView tvName, habitText1, habitText2, habitText3;

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
        habitText1 = view.findViewById(R.id.habitText1);
        habitText2 = view.findViewById(R.id.habitText2);
        habitText3 = view.findViewById(R.id.habitText3);

        loadHabits();

        SharedPreferences prefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
        String first = prefs.getString("signup_first", "");
        tvName.setText(first);

        btnLogout.setOnClickListener(v ->{
            Intent intent = new Intent(requireActivity(), IntroScreen.class);
            startActivity(intent);
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

    private void loadHabits()
    {
        SharedPreferences habitPrefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);

        String json = habitPrefs.getString("saved_habits", "");

        habitText1.setText("");
        habitText2.setText("");
        habitText3.setText("");

        habitText1.setVisibility(View.GONE);
        habitText2.setVisibility(View.GONE);
        habitText3.setVisibility(View.GONE);

        if (!json.isEmpty())
        {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Habit>>(){}.getType();
            ArrayList<Habit> habits = gson.fromJson(json, type);

            if (habits.size() > 0)
            {
                habitText1.setText("" + habits.get(0).getName());
                habitText1.setVisibility(View.VISIBLE);
            }

            if (habits.size() > 1) {
                habitText2.setText("" + habits.get(1).getName());
                habitText2.setVisibility(View.VISIBLE);
            }

            if (habits.size() > 2)
            {
                habitText3.setText("" + habits.get(2).getName());
                habitText3.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onResume()
    {

        super.onResume();
        loadHabits();
    }
}
