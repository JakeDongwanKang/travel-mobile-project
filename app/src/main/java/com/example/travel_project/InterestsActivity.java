package com.example.travel_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InterestsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }

    Button toActivityInfo = findViewById(R.id.btnToInfo);
    toActivityInfo.setOnClickListener(view -> {
        Intent intent = new Intent(this, InfoPage.class);
        startActivity(intent);
    });
}
