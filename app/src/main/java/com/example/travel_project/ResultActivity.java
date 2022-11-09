package com.example.travel_project;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ListView list = findViewById(R.id.resultsList);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Event Result One");
        arrayList.add("Event Result Two");
        arrayList.add("Event Result Three");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, arrayList );


        list.setOnItemClickListener((adapterView, view, position, id) -> {
//            MapFragment mapFragment = new MapFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("Title", (String) list.getItemAtPosition(position));
//            mapFragment.setArguments(bundle);

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.ctnFragment, mapFragment).addToBackStack(null).commit();
        });

        list.setAdapter(arrayAdapter);
    }
}
