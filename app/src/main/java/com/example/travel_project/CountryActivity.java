package com.example.travel_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.HashMap;

public class CountryActivity extends AppCompatActivity {
    ArrayList<String> countriesArrList = new ArrayList<>();
    HashMap<String, String> countryCode = new HashMap< String, String>();
    String userCountry;
    String countryId;
    String url = "http://api.geonames.org/countryInfoJSON?username=vta25";
    ListView countriesList;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countriesList = (ListView)findViewById(R.id.countriesList);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        countriesArrList.add("Pick a country");

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute(url);
    }


        private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(CountryActivity.this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonObjectMain = response.getJSONArray("geonames");
                        for(int i = 0; i < jsonObjectMain.length(); i++) {
                            String name = jsonObjectMain.getJSONObject(i).getString("countryName");
                            String code = jsonObjectMain.getJSONObject(i).getString("geonameId");
                            countriesArrList.add(name);
                            countryCode.put(name, code);
                        }
                        ArrayAdapter adapter = new ArrayAdapter(CountryActivity.this, android.R.layout.simple_list_item_1, countriesArrList);
                        countriesList.setAdapter(adapter);
                        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                                if(position != 0) {
                                    userCountry = (String) parent.getItemAtPosition(position);
                                    countryId = countryCode.get(userCountry);
                                    Bundle bundle = new Bundle();
                                    Intent intent = new Intent(CountryActivity.this, StateActivity.class);
                                    bundle.putString("country", userCountry);
                                    bundle.putString("id", countryId);
                                    intent.putExtra("bundle", bundle);
                                    startActivity(intent);
                                } else {

                                }

                                //Toast.makeText(MainActivity.this, userCountry + countryId, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CountryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
            return null;
        }
    }
}
