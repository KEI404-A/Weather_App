package com.example.weatherapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.util.Patterns;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister, btnBackToLogin;
    private ProgressBar progressBar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(v -> registerUser());
        btnBackToLogin.setOnClickListener(v -> finish());
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (!validateInputs(username, email, password, confirmPassword)) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnRegister.setEnabled(false);

        // Check if email already exists
        if (databaseHelper.checkEmail(email)) {
            etEmail.setError("Email already exists");
            etEmail.requestFocus();
            progressBar.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }

        // Add user to database
        long result = databaseHelper.addUser(username, email, password);

        if (result > 0) {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.GONE);
        btnRegister.setEnabled(true);
    }

    private boolean validateInputs(String username, String email, String password, String confirmPassword) {
        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email");
            etEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password should be at least 6 characters");
            etPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password confirmation doesn't match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
}