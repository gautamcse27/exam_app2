package com.example.exam_app;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private int id;
    private String name;
    private String roll_number;
    private String department;
    private List<String> subjects;
    private String year;
    private String userid;
    private List<SeatAssignment> seatAssignments;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return roll_number; }
    public void setRollNumber(String rollNumber) { this.roll_number = rollNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

    public List<SeatAssignment> getSeatAssignments() { return seatAssignments; }
    public void setSeatAssignments(List<SeatAssignment> seatAssignments) {
        this.seatAssignments = seatAssignments;
    }

    public String getSubjectsAsString() {
        if (subjects == null || subjects.isEmpty()) {
            return "No subjects";
        }
        return String.join(", ", subjects);
    }

    public static class SeatAssignment implements Serializable {
        private int id;
        private int seat_number;
        private String room_number;
        private int capacity;
        private String exam_subject;
        private String exam_date;
        private String exam_time;
        private String exam_venue;
        private VenueCoordinates venue_coordinates;

        // Getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getSeatNumber() { return seat_number; }
        public void setSeatNumber(int seatNumber) { this.seat_number = seatNumber; }

        public String getRoomNumber() { return room_number; }
        public void setRoomNumber(String roomNumber) { this.room_number = roomNumber; }

        public int getCapacity() { return capacity; }
        public void setCapacity(int capacity) { this.capacity = capacity; }

        public String getExamSubject() { return exam_subject; }
        public void setExamSubject(String examSubject) { this.exam_subject = examSubject; }

        public String getExamDate() { return exam_date; }
        public void setExamDate(String examDate) { this.exam_date = examDate; }

        public String getExamTime() { return exam_time; }
        public void setExamTime(String examTime) { this.exam_time = examTime; }

        public String getExamVenue() { return exam_venue; }
        public void setExamVenue(String examVenue) { this.exam_venue = examVenue; }

        public VenueCoordinates getVenueCoordinates() { return venue_coordinates; }
        public void setVenueCoordinates(VenueCoordinates venueCoordinates) {
            this.venue_coordinates = venueCoordinates;
        }

        public String getFormattedExamDateTime() {
            return exam_date + " at " + exam_time;
        }

        // ✅ New helper methods to fix "Cannot resolve method 'getLatitude'"
        public double getLatitude() {
            return venue_coordinates != null ? venue_coordinates.getLatitude() : 0.0;
        }

        public double getLongitude() {
            return venue_coordinates != null ? venue_coordinates.getLongitude() : 0.0;
        }
    }

    public static class VenueCoordinates implements Serializable {
        private double latitude;
        private double longitude;

        public VenueCoordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }

        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }

        @Override
        public String toString() {
            return latitude + "," + longitude;
        }
    }
}
