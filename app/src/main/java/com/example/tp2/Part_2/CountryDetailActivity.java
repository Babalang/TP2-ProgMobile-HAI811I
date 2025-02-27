package com.example.tp2.Part_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tp2.R;

public class CountryDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_2_2);

        // Get data from the Intent
        String countryName = getIntent().getStringExtra("COUNTRY_NAME");
        String countryCapital = getIntent().getStringExtra("COUNTRY_CAPITAL");
        String countryPopulation = getIntent().getStringExtra("COUNTRY_POPULATION");

        // Update UI with data
        ((TextView) findViewById(R.id.title)).setText(countryName);
        ((TextView) findViewById(R.id.pays)).setText(countryCapital);
        ((TextView) findViewById(R.id.population)).setText(countryPopulation);
    }
}