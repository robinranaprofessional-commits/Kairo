package com.example.kairo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kairo.IntroScreen;
import com.example.kairo.R;

public class WeeklyProgress_1 extends Fragment {

    Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weekly_progress, container, false);

        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            // Use the SAME shared prefs file your entire app uses
            SharedPreferences logoutPrefs = requireActivity()
                    .getSharedPreferences("KairoPrefs", Context.MODE_PRIVATE);

            logoutPrefs.edit().clear().apply();

            // Go to intro screen
            Intent intent = new Intent(requireActivity(), IntroScreen.class);
            startActivity(intent);

            // Close MainActivity and all fragments
            requireActivity().finish();
        });

        return view;
    }
}
