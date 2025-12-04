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

        SharedPreferences sharedPreferences = getSharedPreferences("kairo_prefs", MODE_PRIVATE);
        SharedPreferences.Editor sh_editor = sharedPreferences.edit();

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
                sh_editor.putBoolean("is_existing_user",true);
                sh_editor.apply();
                startActivity(new Intent(this, LoginScreen.class));
            }

            Intent intent = new Intent(SignupScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tvGoToLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginScreen.class)));
    }
}