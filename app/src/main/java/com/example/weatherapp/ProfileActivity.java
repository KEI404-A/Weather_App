package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvRole, tvEmail, tvPhone, tvDescription;
    private Button btnBack;
    private CardView profileCard, infoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setProfileData();
        setupAnimations();

        btnBack.setOnClickListener(v -> finish());

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileCard.setElevation(100f);
            }
        });

        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initializeViews() {
        tvName = findViewById(R.id.tvName);
        tvRole = findViewById(R.id.tvRole);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvDescription = findViewById(R.id.tvDescription);
        btnBack = findViewById(R.id.btnBack);
        profileCard = findViewById(R.id.profileCard);
        infoCard = findViewById(R.id.infoCard);
    }

    private void setProfileData() {
        tvName.setText("FADHIL ANNAUFAL MUSYAFA");
        tvRole.setText("[WEB DEVELOPER]");
        tvEmail.setText("Email: rynesaki66@gmail.com");
        tvPhone.setText("Phone: +62 878-1236-7840");
        tvDescription.setText("KING OF WEB DEVELOPER");
    }

    private void setupAnimations() {
        profileCard.setAlpha(0f);
        profileCard.setTranslationY(50f);
        profileCard.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(1000)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        infoCard.setAlpha(0f);
        infoCard.setTranslationY(50f);
        infoCard.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(1000)
                .setStartDelay(300)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}