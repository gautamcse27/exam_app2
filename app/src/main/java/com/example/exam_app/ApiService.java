package com.example.exam_app;
// ApiService.java
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}