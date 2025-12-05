package com.example.kairo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignIn;
    TextView tvGoSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        SharedPreferences loginPref = getSharedPreferences("kairo_prefs", MODE_PRIVATE);
        boolean isLoggedIn = loginPref.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(LoginScreen.this, MainActivity.class));
            finish();
            return;
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvGoSignUp = findViewById(R.id.tvGoSignUp);

        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String savedEmail = loginPref.getString("signup_email", null);
            String savedPassword = loginPref.getString("signup_password", null);

            if (savedEmail == null || savedPassword == null) {
                Toast.makeText(this, "No account found. Please create an account.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.equals(savedEmail) && password.equals(savedPassword)) {
                loginPref.edit().putBoolean("isLoggedIn", true).apply();

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(LoginScreen.this, MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "Incorrect email/password", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoSignUp.setOnClickListener(v ->{
            startActivity(new Intent(LoginScreen.this, SignupScreen.class));
        });

    }
}