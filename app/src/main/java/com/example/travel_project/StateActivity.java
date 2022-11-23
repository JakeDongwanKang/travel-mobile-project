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

public class StateActivity extends AppCompatActivity {
    ArrayList<String> statesArrList = new ArrayList<>();
    HashMap<String, String> stateCode = new HashMap< String, String>();
    String userState;
    String stateId;
    String url;
    ListView statesList;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        statesList = (ListView) findViewById(R.id.statesList);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String country = bundle.getString("country");
        String id = bundle.getString("id");

        url = "http://api.geonames.org/childrenJSON?geonameId="+id+"&username=vta25";

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        statesArrList.add("Pick a State");

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute(url);
    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(StateActivity.this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonObjectMain = response.getJSONArray("geonames");
                        for(int i = 0; i < jsonObjectMain.length(); i++) {
                            String name = jsonObjectMain.getJSONObject(i).getString("name");
                            String code = jsonObjectMain.getJSONObject(i).getString("geonameId");
                            statesArrList.add(name);
                            stateCode.put(name, code);
                        }
                        ArrayAdapter adapter = new ArrayAdapter(StateActivity.this, android.R.layout.simple_list_item_1, statesArrList);
                        statesList.setAdapter(adapter);
                        statesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                                if(position != 0) {
                                    userState = (String) parent.getItemAtPosition(position);
                                    stateId = stateCode.get(userState);
                                    Bundle bundle = new Bundle();
                                    Intent intent = new Intent(StateActivity.this, CityActivity.class);
                                    bundle.putString("state", userState);
                                    bundle.putString("id", stateId);
                                    intent.putExtra("bundle", bundle);
                                    startActivity(intent);
                                    //Toast.makeText(MainActivity.this, userCountry + countryId, Toast.LENGTH_SHORT).show();
                                } else {

                                }

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
                    Toast.makeText(StateActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
            return null;
        }
    }
}
