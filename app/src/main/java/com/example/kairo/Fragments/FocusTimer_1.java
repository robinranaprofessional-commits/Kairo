package com.example.kairo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.kairo.IntroScreen;
import com.example.kairo.R;


public class FocusTimer_1 extends Fragment {

    Button btnNewAccount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus_timer_1, container, false);

        btnNewAccount = view.findViewById(R.id.btnNewAccount);

        btnNewAccount.setOnClickListener(v -> {
            SharedPreferences logoutPrefs = requireActivity().getSharedPreferences("kairo_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = logoutPrefs.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(requireActivity(), IntroScreen.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }
}