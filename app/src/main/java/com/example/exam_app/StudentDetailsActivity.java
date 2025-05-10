package com.example.exam_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // Initialize views
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvRollNumber = findViewById(R.id.tvRollNumber);
        TextView tvDepartment = findViewById(R.id.tvDepartment);
        LinearLayout subjectsContainer = findViewById(R.id.subjectsContainer);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvUserId = findViewById(R.id.tvUserId);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Get student data from intent
        Student student = (Student) getIntent().getSerializableExtra("student");

        if (student != null) {
            // Set basic student information
            tvWelcome.setText("Welcome, " + student.getName() + "!");
            tvName.setText("Name: " + student.getName());
            tvRollNumber.setText("Roll Number: " + student.getRollNumber());
            tvDepartment.setText("Department: " + student.getDepartment());
            tvYear.setText("Year: " + student.getYear());
            tvUserId.setText("User ID: " + student.getUserid());

            // Handle subjects display
            displaySubjects(student.getSubjects(), subjectsContainer);
        }

        // Logout button click handler
        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(StudentDetailsActivity.this, MainActivity.class));
            finish();
        });
    }

    private void displaySubjects(List<String> subjects, LinearLayout container) {
        // Clear any existing views
        container.removeAllViews();

        if (subjects == null || subjects.isEmpty()) {
            TextView noSubjects = new TextView(this);
            noSubjects.setText("No subjects registered");
            container.addView(noSubjects);
            return;
        }

        // Add each subject as a new TextView
        for (String subject : subjects) {
            TextView subjectView = new TextView(this);
            subjectView.setText("• " + subject);
            subjectView.setTextSize(16);

            // Set layout params with some margin
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 0);
            subjectView.setLayoutParams(params);

            container.addView(subjectView);
        }
    }
}