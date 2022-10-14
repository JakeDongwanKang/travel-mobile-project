package com.example.travel_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Button toActivityCountry = findViewById(R.id.btnToCountry);
        toActivityCountry.setOnClickListener(view -> {
            Intent intent = new Intent(this, CountryActivity.class);
            startActivity(intent);
        });
    }
}
