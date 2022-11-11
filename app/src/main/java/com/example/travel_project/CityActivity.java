package com.example.travel_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {
    ArrayList<String> citiesArrList = new ArrayList<>();
    SparseBooleanArray sparseBooleanArray;
    String url;
    ListView citiesList;
    String valueHolder;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String id = bundle.getString("id");

        citiesList = (ListView) findViewById(R.id.citiesList);

        url = "http://api.geonames.org/childrenJSON?geonameId="+id+"&username=vta25";

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute(url);

        Button toInfo = findViewById(R.id.btnToInfo);
        toInfo.setOnClickListener(view -> {
            Bundle bundle2 = new Bundle();
            Intent intent2 = new Intent(this, InfoActivity.class);
            bundle2.putString("userCities", valueHolder);
            intent2.putExtra("bundle", bundle);
            startActivity(intent2);
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(CityActivity.this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonObjectMain = response.getJSONArray("geonames");
                        for(int i = 0; i < jsonObjectMain.length(); i++) {
                            String name = jsonObjectMain.getJSONObject(i).getString("name");
                            citiesArrList.add(name);
                        }
                        ArrayAdapter adapter = new ArrayAdapter(CityActivity.this, android.R.layout.simple_list_item_multiple_choice, citiesArrList);
                        citiesList.setAdapter(adapter);
                        citiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                                sparseBooleanArray = citiesList.getCheckedItemPositions();
                                valueHolder = "" ;
                                int i = 0 ;
                                while (i < sparseBooleanArray.size()) {
                                    if (sparseBooleanArray.valueAt(i)) {
                                        valueHolder += citiesArrList.get(sparseBooleanArray.keyAt(i)) + ",";
                                    }
                                    i++ ;
                                }
                                valueHolder = valueHolder.replaceAll("(,)*$", "");
//                                for(int x = 0; x < userCities.length; ++x) {
//                                    Log.d("arr", userCities[x]);
//                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CityActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
            return null;
        }
    }
}
