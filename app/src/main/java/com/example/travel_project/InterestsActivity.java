package com.example.travel_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class InterestsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        CheckBox box = findViewById(R.id.checkBox);
        CheckBox box2 = findViewById(R.id.checkBox2);
        CheckBox box3 = findViewById(R.id.checkBox3);
        CheckBox box4 = findViewById(R.id.checkBox4);
        CheckBox box5 = findViewById(R.id.checkBox5);
        CheckBox box6 = findViewById(R.id.checkBox6);

        Button toActivityInfo = findViewById(R.id.btnToInfo);
        toActivityInfo.setOnClickListener(view -> {
            Bundle bundle = getIntent().getExtras();
            Intent intent = new Intent(this, ResultActivity.class);
            String boxResults = "";

            if (box.isChecked()) {
                boxResults += "|restaurant";
            }
            if (box2.isChecked()) {
                boxResults += "|tourist_attraction";
            }
            if (box3.isChecked()) {
                boxResults += "|amusement_park";
            }
            if (box4.isChecked()) {
                boxResults += "|spa";
            }
            if (box5.isChecked()) {
                boxResults += "|museum";
            }
            if (box6.isChecked()) {
                boxResults += "|stadium";
            }

            if (!boxResults.isEmpty()) {
                boxResults = boxResults.substring(1);
            }
            if (bundle == null) {
                bundle = getIntent().getExtras();
            }
            bundle.putString("category", boxResults);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
