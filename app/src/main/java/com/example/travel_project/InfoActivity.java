package com.example.travel_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button toResults = findViewById(R.id.submitButton);
        toResults.setOnClickListener(view -> {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        });
    }
}
