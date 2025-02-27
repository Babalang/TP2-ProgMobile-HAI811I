package com.example.tp2.Part_2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tp2.R;

import java.util.ArrayList;
import java.util.List;

public class liste_pays_activite extends Activity {

    // Sample data (you'd likely load this from a database or network)
    private final List<Country> countryList = new ArrayList<>() {{
        add(new Country("France", "Paris", "67,000,000"));
        add(new Country("Germany", "Berlin", "83,000,000"));
        add(new Country("Spain", "Madrid", "47,000,000"));
        add(new Country("Italy", "Rome", "60,000,000"));
        add(new Country("United Kingdom", "London", "68,000,000"));
        add(new Country("Canada", "Ottawa", "38,000,000"));
        add(new Country("United States", "Washington, D.C.", "330,000,000"));
        add(new Country("Japan", "Tokyo", "126,000,000"));
        add(new Country("China", "Beijing", "1,400,000,000"));
        add(new Country("Brazil", "Brasília", "214,000,000"));
        add(new Country("India", "New Delhi", "1,380,000,000"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_2_1);
        ((TextView) findViewById(R.id.title)).setText("Liste des pays");

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // Handle country click
        CountryAdapter countryAdapter = new CountryAdapter(countryList, selectedCountry -> {
            // Handle country click
            Log.i("MainActivity", "Click on " + selectedCountry.getName());
            Intent intent = new Intent(liste_pays_activite.this, CountryDetailActivity.class);
            intent.putExtra("COUNTRY_NAME", selectedCountry.getName());
            intent.putExtra("COUNTRY_CAPITAL", selectedCountry.getCapital());
            intent.putExtra("COUNTRY_POPULATION", selectedCountry.getPopulation());
            startActivity(intent);
        });

        recyclerView.setAdapter(countryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}