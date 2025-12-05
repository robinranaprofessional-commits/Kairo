package com.example.kairo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupScreen extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail, etPassword;
    Button btnCreateAccount;
    TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        SharedPreferences pref = getSharedPreferences("kairo_prefs", MODE_PRIVATE);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etSignupEmail);
        etPassword = findViewById(R.id.etSignupPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnCreateAccount.setOnClickListener(v -> {
            String first = etFirstName.getText().toString();
            String last = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (first.isEmpty() || last.isEmpty() || email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor edit = pref.edit();
            edit.putString("signup_email", email);
            edit.putString("signup_password", password);
            edit.apply();

            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT);

            startActivity(new Intent(this, LoginScreen.class));
            finish();
        });

        tvGoToLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginScreen.class)));
    }
}