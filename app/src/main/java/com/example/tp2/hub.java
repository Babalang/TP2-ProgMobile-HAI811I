package com.example.tp2;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;

import com.example.tp2.Country.Country;
import com.example.tp2.Country.Country_Detail_Fragment;
import com.example.tp2.Country.Country_fragment;

public class hub extends FragmentActivity implements Country_fragment.OnCountrySelectedListener {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub);
        Button exo1_button = findViewById(R.id.exo1_button);
        exo1_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, ListCaptor.class);
            startActivity(intent);
        });
        Button exo2_button = findViewById(R.id.exo2_button);
        exo2_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, detect_captor.class);
            startActivity(intent);
        });
        Button exo3_button = findViewById(R.id.exo3_button);
        exo3_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, accelerometer.class);
            startActivity(intent);
        });
        Button exo4_button = findViewById(R.id.exo4_button);
        exo4_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, direction.class);
            startActivity(intent);
        });
        Button exo5_button = findViewById(R.id.exo5_button);
        exo5_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, lampe.class);
            startActivity(intent);
        });
        Button exo6_button = findViewById(R.id.exo6_button);
        exo6_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, proximity.class);
            startActivity(intent);
        });
        Button exo7_button = findViewById(R.id.exo7_button);
        exo7_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, location.class);
            startActivity(intent);
        });
        Button exo8_button = findViewById(R.id.exo8_button);
        exo8_button.setOnClickListener(v -> {
            Intent intent = new Intent(hub.this, liste_pays_activite.class);
            startActivity(intent);
        });
        Button exo9_button = findViewById(R.id.exo9_button);
        exo9_button.setOnClickListener(v -> {
            loadCountryListFragment();
        });

    }
    @Override
    public void onCountrySelected(Country selectedCountry) {
        Country_Detail_Fragment countryDetailFragment = Country_Detail_Fragment.newInstance(selectedCountry);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout, countryDetailFragment);
        transaction.addToBackStack(null); // Add to back stack so you can navigate back
        transaction.commit();
    }

    private void loadCountryListFragment() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        Country_fragment countryListFragment = new Country_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout, countryListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}
