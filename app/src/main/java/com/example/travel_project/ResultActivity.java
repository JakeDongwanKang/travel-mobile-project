package com.example.travel_project;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    String baseURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=+&region=";
    String category = "&type=";
    String key = "&key=AIzaSyDvPs0AMFg6GwgLBG3yk7res3FLrJWk0Ps";
    String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle bundle = getIntent().getExtras();
        query += baseURL + getCountryCode(bundle.getString("country"))
                + category + bundle.getString("category") + key;

        // for testing if no country input
//        query += baseURL + "ca"
//                + category + bundle.getString("category") + key;

//        list.setOnItemClickListener((adapterView, view, position, id) -> {
//            MapFragment mapFragment = new MapFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("Title", (String) list.getItemAtPosition(position));
//            mapFragment.setArguments(bundle);
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.ctnFragment, mapFragment).addToBackStack(null).commit();
//        });

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute(query);
    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(ResultActivity.this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray results = response.getJSONArray("results");
                        String[] name = new String[results.length()];
                        String[] rating = new String[results.length()];
                        String[] category = new String[results.length()];

                        for (int i = 0; i < results.length(); i++) {
                            name[i] = results.getJSONObject(i).getString("name");
                            rating[i] = "Rating: " + results.getJSONObject(i).getDouble("rating");
                            String types = "";
                            for (int j = 0; j < results.getJSONObject(i).getJSONArray("types").length(); j++) {
                                types += results.getJSONObject(i).getJSONArray("types").getString(j) + ", ";
                            }
                            types = types.replace("_", " ");
                            category[i] = types;
                        }

                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), name, rating, category);

                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ResultActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
            return null;
        }
    }

    String getCountryCode(String country) {
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        return countries.get(country);
    }
}
