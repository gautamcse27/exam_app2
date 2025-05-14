package com.example.exam_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btnStudentLogin = findViewById(R.id.btnStudentLogin);
        MaterialButton btnAbout = findViewById(R.id.btnAbout);

        // Add animation to the login button
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        btnStudentLogin.startAnimation(pulse);

        btnStudentLogin.setOnClickListener(v -> {
            // Add click animation
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));

            // Navigate to StudentLoginActivity with a slight delay
            v.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, StudentLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 200);
        });

        btnAbout.setOnClickListener(v -> {
            // Show about dialog
            new AboutDialog().show(getSupportFragmentManager(), "AboutDialog");
        });
    }
}