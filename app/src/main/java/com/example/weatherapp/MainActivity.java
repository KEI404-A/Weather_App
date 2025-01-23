package com.example.weatherapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 101;

    private Button btnWeather, btnEarthquake, btnProfile, btnLogout;
    private ImageButton btnCamera, btnGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();

        // Periksa izin kamera saat aplikasi dimulai
        checkAndRequestCameraPermission();
    }

    private void initializeViews() {
        btnWeather = findViewById(R.id.btnWeather);
        btnEarthquake = findViewById(R.id.btnEarthquake);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
    }

    private void setupClickListeners() {
        btnWeather.setOnClickListener(v -> openBMKGWebPage("https://www.bmkg.go.id/cuaca"));
        btnEarthquake.setOnClickListener(v -> openBMKGWebPage("https://www.bmkg.go.id/gempabumi"));
        btnProfile.setOnClickListener(v -> openProfileActivity());
        btnLogout.setOnClickListener(v -> logoutUser());
        btnCamera.setOnClickListener(v -> openCamera());
        btnGallery.setOnClickListener(v -> openGallery());
    }

    private void openBMKGWebPage(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Tidak dapat membuka browser", Toast.LENGTH_SHORT).show();
        }
    }

    private void openProfileActivity() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void logoutUser() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    preferences.edit().clear().apply();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(cameraIntent);
            } else {
                Toast.makeText(this, "Kamera tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        } else {
            checkAndRequestCameraPermission();
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(galleryIntent);
    }

    private void checkAndRequestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setTitle("Izin Kamera Dibutuhkan")
                        .setMessage("Aplikasi ini memerlukan akses ke kamera untuk menggunakan fitur kamera.")
                        .setPositiveButton("OK", (dialog, which) ->
                                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE))
                        .setNegativeButton("Batal", (dialog, which) ->
                                Toast.makeText(this, "Fitur kamera tidak dapat digunakan tanpa izin.", Toast.LENGTH_SHORT).show())
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin kamera diberikan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Izin kamera ditolak. Fitur tidak dapat digunakan.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle("Keluar Aplikasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya", (dialog, which) -> finish())
                .setNegativeButton("Tidak", null)
                .show();
    }
}