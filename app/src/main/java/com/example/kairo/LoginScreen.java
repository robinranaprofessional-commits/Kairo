package com.example.kairo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        SharedPreferences loginPref = getSharedPreferences("UserData", MODE_PRIVATE);
        boolean isLoggedIn = loginPref.getBoolean("isLoggedIn", false);

        if (isLoggedIn)
        {
            startActivity(new Intent(LoginScreen.this, WeeklyProgressReport.class));
            finish();
            return;
        }

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = loginPref.edit();

                editor.putString("email", email);
                editor.putString("password", password);
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginScreen.this, DailyProgress.class);
                startActivity(intent);
                finish();
            }
        });
    }
}