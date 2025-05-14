package com.example.exam_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    private LinearLayout seatAssignmentsContainer;
    private LinearLayout subjectsContainer;
    private Spinner subjectSpinner;
    private List<Student.SeatAssignment> allAssignments = new ArrayList<>();
    private static final String PROMPT_ITEM = "Please select a subject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // Initialize views
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvRollNumber = findViewById(R.id.tvRollNumber);
        TextView tvDepartment = findViewById(R.id.tvDepartment);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvUserId = findViewById(R.id.tvUserId);
        MaterialButton btnLogout = findViewById(R.id.btnLogout);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        seatAssignmentsContainer = findViewById(R.id.seatAssignmentsContainer);
        subjectsContainer = findViewById(R.id.subjectsContainer);

        Student student = (Student) getIntent().getSerializableExtra("student");

        if (student != null) {
            tvWelcome.setText("Welcome, " + student.getName() + "!");
            tvName.setText("Name: " + student.getName());
            tvRollNumber.setText("Roll Number: " + student.getRollNumber());
            tvDepartment.setText("Department: " + student.getDepartment());
            tvYear.setText("Year: " + student.getYear());
            tvUserId.setText("User ID: " + student.getUserid());

            allAssignments = student.getSeatAssignments();

            setupSubjectDropdown(student.getSubjects());
            displaySubjects(student.getSubjects(), subjectsContainer);
            subjectsContainer.setAlpha(0);
            subjectsContainer.animate().alpha(1).setDuration(300).start();
        }

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(StudentDetailsActivity.this, MainActivity.class));
            finish();
        });
    }

    private void setupSubjectDropdown(List<String> subjects) {
        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add(PROMPT_ITEM);
        if (subjects != null && !subjects.isEmpty()) {
            spinnerItems.addAll(subjects);
        } else {
            subjects = new ArrayList<>();
            subjects.add("No subjects available");
            spinnerItems.add("No subjects available");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                String item = spinnerItems.get(position);
                tv.setTextColor(getResources().getColor(R.color.text_primary));
                if (item.equals(PROMPT_ITEM)) {
                    tv.setTextColor(getResources().getColor(R.color.text_hint));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                String item = spinnerItems.get(position);
                tv.setTextColor(getResources().getColor(R.color.text_primary));
                if (item.equals(PROMPT_ITEM)) {
                    tv.setTextColor(getResources().getColor(R.color.text_hint));
                }
                return view;
            }
        };
        subjectSpinner.setAdapter(adapter);

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = spinnerItems.get(position);
                seatAssignmentsContainer.animate().alpha(0).setDuration(200).withEndAction(() -> {
                    if (selectedItem.equals(PROMPT_ITEM)) {
                        displayPromptMessage();
                    } else {
                        filterAndDisplaySeatAssignments(selectedItem);
                    }
                    seatAssignmentsContainer.animate().alpha(1).setDuration(200).start();
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                displayPromptMessage();
            }
        });
    }

    private void displayPromptMessage() {
        seatAssignmentsContainer.removeAllViews();
        TextView prompt = new TextView(this);
        prompt.setText("Please select a subject to see seat assignments");
        prompt.setTextSize(16);
        prompt.setTextColor(getResources().getColor(R.color.text_hint));
        prompt.setPadding(16, 16, 16, 16);
        seatAssignmentsContainer.addView(prompt);
    }

    private void filterAndDisplaySeatAssignments(String selectedSubject) {
        List<Student.SeatAssignment> filtered = new ArrayList<>();
        for (Student.SeatAssignment assignment : allAssignments) {
            if (assignment.getExamSubject().equalsIgnoreCase(selectedSubject)) {
                filtered.add(assignment);
            }
        }
        displaySeatAssignments(filtered, seatAssignmentsContainer);
    }

    private void displaySeatAssignments(List<Student.SeatAssignment> assignments, LinearLayout container) {
        container.removeAllViews();

        if (assignments == null || assignments.isEmpty()) {
            TextView noAssignments = new TextView(this);
            noAssignments.setText("No seat assignments found for this subject");
            noAssignments.setTextSize(16);
            noAssignments.setTextColor(getResources().getColor(R.color.text_hint));
            noAssignments.setPadding(16, 16, 16, 16);
            container.addView(noAssignments);
            return;
        }

        for (Student.SeatAssignment assignment : assignments) {
            MaterialCardView cardView = new MaterialCardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(16, 8, 16, 8);
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(4);
            cardView.setRadius(12);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.card_math)); // Example

            LinearLayout assignmentLayout = new LinearLayout(this);
            assignmentLayout.setOrientation(LinearLayout.VERTICAL);
            assignmentLayout.setPadding(16, 16, 16, 16);

            TextView examSubject = new TextView(this);
            examSubject.setText("Exam: " + assignment.getExamSubject());
            examSubject.setTextSize(16);
            examSubject.setTextColor(getResources().getColor(R.color.text_primary));

            TextView examDateTime = new TextView(this);
            examDateTime.setText("Date/Time: " + assignment.getFormattedExamDateTime());
            examDateTime.setTextSize(14);
            examDateTime.setTextColor(getResources().getColor(R.color.text_secondary));

            TextView roomInfo = new TextView(this);
            roomInfo.setText("Room: " + assignment.getRoomNumber() + " (Capacity: " + assignment.getCapacity() + ")");
            roomInfo.setTextSize(14);
            roomInfo.setTextColor(getResources().getColor(R.color.text_secondary));

            TextView venueInfo = new TextView(this);
            venueInfo.setText("Venue: " + assignment.getExamVenue());
            venueInfo.setTextSize(14);
            venueInfo.setTextColor(getResources().getColor(R.color.text_secondary));

            TextView seatInfo = new TextView(this);
            seatInfo.setText("Seat Number: " + assignment.getSeatNumber());
            seatInfo.setTextSize(14);
            seatInfo.setTextColor(getResources().getColor(R.color.text_secondary));

            MaterialButton mapButton = new MaterialButton(this);
            mapButton.setText("View on Map");
            mapButton.setIconResource(R.drawable.ic_map);
            mapButton.setOnClickListener(v -> {
                double lat = assignment.getLatitude();
                double lon = assignment.getLongitude();
                Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?q=" + lat + "," + lon + "(" + assignment.getExamVenue() + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });

            assignmentLayout.addView(examSubject);
            assignmentLayout.addView(examDateTime);
            assignmentLayout.addView(roomInfo);
            assignmentLayout.addView(venueInfo);
            assignmentLayout.addView(seatInfo);
            assignmentLayout.addView(mapButton);

            cardView.addView(assignmentLayout);
            container.addView(cardView);
        }
    }

    private void displaySubjects(List<String> subjects, LinearLayout container) {
        container.removeAllViews();

        if (subjects == null || subjects.isEmpty()) {
            TextView noSubjects = new TextView(this);
            noSubjects.setText("No subjects registered");
            noSubjects.setTextSize(16);
            noSubjects.setTextColor(getResources().getColor(R.color.text_hint));
            noSubjects.setPadding(16, 16, 16, 16);
            container.addView(noSubjects);
            return;
        }

        TextView header = new TextView(this);
        header.setText("Registered Subjects");
        header.setTextSize(18);
        header.setTextColor(getResources().getColor(R.color.text_primary));
        header.setPadding(0, 0, 0, 8);
        container.addView(header);

        for (String subject : subjects) {
            MaterialCardView cardView = new MaterialCardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 4, 0, 4);
            cardView.setLayoutParams(cardParams);
            cardView.setRadius(8);
            cardView.setCardElevation(2);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.card_math));

            TextView subjectView = new TextView(this);
            subjectView.setText("• " + subject);
            subjectView.setTextSize(16);
            subjectView.setTextColor(getResources().getColor(R.color.text_primary));
            subjectView.setPadding(12, 12, 12, 12);

            cardView.addView(subjectView);
            container.addView(cardView);
        }
    }
}
