package com.example.exam_app;


import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private int id;
    private String name;
    private String roll_number;
    private String department;
    private List<String> subjects;  // Changed from String to List<String>
    private String year;
    private String userid;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return roll_number; }
    public void setRollNumber(String rollNumber) { this.roll_number = rollNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    // Updated getters and setters for subjects
    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

    // Helper method to get subjects as comma-separated string
    public String getSubjectsAsString() {
        if (subjects == null || subjects.isEmpty()) {
            return "No subjects";
        }
        return String.join(", ", subjects);
    }
}