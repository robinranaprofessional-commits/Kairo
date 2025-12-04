package com.example.kairo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroScreen extends AppCompatActivity {

    Button btnCreateAccount, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnCreateAccount.setOnClickListener(v -> startActivity(new Intent(this, SignupScreen.class)));

        btnSignIn.setOnClickListener(v -> startActivity(new Intent(this, LoginScreen.class)));

    }
}
