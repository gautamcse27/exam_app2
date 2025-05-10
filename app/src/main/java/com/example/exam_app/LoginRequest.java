package com.example.exam_app;
// LoginRequest.java
public class LoginRequest {
    private String userid;
    private String password;

    public LoginRequest(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    // Getters and setters
    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}