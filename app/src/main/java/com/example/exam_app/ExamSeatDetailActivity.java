package com.example.exam_app;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExamSeatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_seat_detail);

        Student.SeatAssignment assignment = (Student.SeatAssignment) getIntent().getSerializableExtra("assignment");

        LinearLayout container = findViewById(R.id.examDetailContainer);

        if (assignment != null) {
            TextView title = new TextView(this);
            title.setText("Exam Seat Assignment Details");
            title.setTextSize(22);
            container.addView(title);

            TextView examSubject = new TextView(this);
            examSubject.setText("Subject: " + assignment.getExamSubject());
            container.addView(examSubject);

            TextView dateTime = new TextView(this);
            dateTime.setText("Date/Time: " + assignment.getFormattedExamDateTime());
            container.addView(dateTime);

            TextView venue = new TextView(this);
            venue.setText("Venue: " + assignment.getExamVenue());
            container.addView(venue);

            TextView room = new TextView(this);
            room.setText("Room: " + assignment.getRoomNumber() + " (Capacity: " + assignment.getCapacity() + ")");
            container.addView(room);

            TextView seat = new TextView(this);
            seat.setText("Seat Number: " + assignment.getSeatNumber());
            container.addView(seat);
        }
    }
}
