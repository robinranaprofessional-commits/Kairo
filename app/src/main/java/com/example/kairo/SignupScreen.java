package com.example.kairo;

import android.content.Intent;
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

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etSignupEmail);
        etPassword = findViewById(R.id.etSignupPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnCreateAccount.setOnClickListener(v -> {

            if (etFirstName.getText().toString().isEmpty() ||
                    etLastName.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty() ||
                    etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginScreen.class));
            }

            Intent intent = new Intent(SignupScreen.this, WeeklyProgressReport.class);
            startActivity(intent);
            finish();
        });

        tvGoToLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginScreen.class)));
    }
}