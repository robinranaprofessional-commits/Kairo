package com.example.kairo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etLastName = findViewById(R.id.etLastName);
        EditText etEmail = findViewById(R.id.etSignupEmail);
        EditText etPassword = findViewById(R.id.etSignupPassword);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        TextView tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnCreateAccount.setOnClickListener(v -> {

            if (etFirstName.getText().toString().isEmpty() ||
                etLastName.getText().toString().isEmpty() ||
                etEmail.getText().toString().isEmpty() ||
                etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else {
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