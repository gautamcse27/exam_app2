package com.example.exam_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etUserId, etPassword;
    private Button btnLogin;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserId = findViewById(R.id.etUserId);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvResult = findViewById(R.id.tvResult);

        btnLogin.setOnClickListener(v -> {
            String userId = etUserId.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            loginStudent(userId, password);
        });
    }

    private void loginStudent(String userId, String password) {
        LoginRequest loginRequest = new LoginRequest(userId, password);

        ApiService apiService = RetrofitClient.getApiService();
        Call<LoginResponse> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        // Navigate to StudentDetailsActivity with student data
                        Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                        intent.putExtra("student", loginResponse.getStudent());
                        startActivity(intent);
                        finish(); // Close login activity
                    } else {
                        tvResult.setText("Login failed: " + loginResponse.getMessage());
                    }
                } else {
                    tvResult.setText("Login failed: Server error");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                tvResult.setText("Error: " + t.getMessage());
            }
        });
    }
}