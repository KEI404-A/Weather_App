package com.example.weatherapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private DatabaseHelper databaseHelper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is already logged in
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        // Initialize components
        databaseHelper = new DatabaseHelper(this);
        initializeViews();
        setupListeners();

        // Initialize progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
    }

    private void initializeViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> loginUser());
        btnRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!validateInputs(email, password)) {
            return;
        }

        progressDialog.show();

        // Simulate network delay
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                runOnUiThread(() -> performLogin(email, password));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void performLogin(String email, String password) {
        if (databaseHelper.checkUser(email, password)) {
            SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            preferences.edit()
                    .putBoolean("isLoggedIn", true)
                    .putString("email", email)
                    .apply();

            progressDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}