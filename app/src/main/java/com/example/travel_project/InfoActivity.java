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
        Intent intent = new Intent(this, ResultActivity.class);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            intent.putExtras(bundle);
        }

        Button toResults = findViewById(R.id.submitButton);
        toResults.setOnClickListener(view -> {
            startActivity(intent);
        });
    }
}
