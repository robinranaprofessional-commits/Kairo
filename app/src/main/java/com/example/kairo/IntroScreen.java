package com.example.kairo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroScreen extends AppCompatActivity {

    Button btnCreateAccount, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnCreateAccount.setOnClickListener(v ->
                startActivity(new Intent(this, SignupScreen.class)));

        btnSignIn.setOnClickListener(v ->
                startActivity(new Intent(this, LoginScreen.class)));

    }
}
