package com.example.tp2.Part_2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

import java.util.ArrayList;
import java.util.List;

public class Country_fragment extends Fragment {
    private OnCountrySelectedListener listener;

    // Interface for communication with the activity
    public interface OnCountrySelectedListener {
        void onCountrySelected(Country selectedCountry);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCountrySelectedListener) {
            listener = (OnCountrySelectedListener) context;
        } else {
            throw new ClassCastException(context
                    + " must implement OnCountrySelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.part_2_1, container, false);

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ((TextView) view.findViewById(R.id.title)).setText(R.string.Exo9_title);

        // Sample data (you'd likely load this from a database or network)
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country("France", "Paris", "67,000,000"));
        countryList.add(new Country("Germany", "Berlin", "83,000,000"));
        countryList.add(new Country("Spain", "Madrid", "47,000,000"));
        countryList.add(new Country("Italy", "Rome", "60,000,000"));
        countryList.add(new Country("United Kingdom", "London", "68,000,000"));
        countryList.add(new Country("Canada", "Ottawa", "38,000,000"));
        countryList.add(new Country("United States", "Washington, D.C.", "330,000,000"));
        countryList.add(new Country("Japan", "Tokyo", "126,000,000"));
        countryList.add(new Country("China", "Beijing", "1,400,000,000"));
        countryList.add(new Country("Brazil", "Brasília", "214,000,000"));
        countryList.add(new Country("India", "New Delhi", "1,380,000,000"));

        // Handle country click
        CountryAdapter countryAdapter = new CountryAdapter(countryList, selectedCountry -> {
            // Handle country click
            listener.onCountrySelected(selectedCountry);
        });

        recyclerView.setAdapter(countryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}
