package com.example.travel_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import java.util.ArrayList;

public class CountryActivity2 extends AppCompatActivity {

    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country2);

        ArrayList<String> countries = new ArrayList<>();
        WebService.setGeoNamesServer("api.geonames.org");
        WebService.setUserName("vta25");
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ("country");
        ToponymSearchResult searchResult = null;
        try {
            searchResult = WebService.search(searchCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Toponym toponym : searchResult.getToponyms()) {
            countries.add(toponym.getName());
        }

        Spinner spinner = (Spinner) findViewById(R.id.country_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            // called when an item is selected
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
            }

            @Override
            // called when on item is selected
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });
    }
}
