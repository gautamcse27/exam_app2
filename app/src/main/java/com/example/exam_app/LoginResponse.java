package com.example.exam_app;
// LoginResponse.java
public class LoginResponse {
    private boolean success;
    private String message;
    private Student student;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Student getStudent() { return student; }
}