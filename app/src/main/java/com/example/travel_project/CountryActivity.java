package com.example.travel_project;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CountryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Fragment country = new CityFragment();
        Fragment city = new CityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ctnFragment, country);
        fragmentTransaction.commit();

        Button btnToCountry = findViewById(R.id.btnCountryFragment);
        btnToCountry.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.replace(R.id.ctnFragment, country);
            fragmentTransaction1.commit();
        });

        Button btnToCity = findViewById(R.id.btnCityFragment);
        btnToCity.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
            fragmentTransaction2.replace(R.id.ctnFragment, city);
            fragmentTransaction2.commit();
        });
    }
}
