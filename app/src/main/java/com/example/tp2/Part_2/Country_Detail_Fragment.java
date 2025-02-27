package com.example.tp2.Part_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tp2.R;

public class Country_Detail_Fragment extends Fragment {

    private static final String ARG_COUNTRY = "country";

    private Country country;

    public static Country_Detail_Fragment newInstance(Country country) {
        Country_Detail_Fragment fragment = new Country_Detail_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = (Country) getArguments().getSerializable(ARG_COUNTRY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.part_2_2, container, false);
        TextView detailNameTextView = view.findViewById(R.id.title);
        TextView detailCapitalTextView = view.findViewById(R.id.pays);
        TextView detailPopulationTextView = view.findViewById(R.id.population);

        if (country != null) {
            detailNameTextView.setText(country.getName());
            detailCapitalTextView.setText(country.getCapital());
            detailPopulationTextView.setText(country.getPopulation());
        }
        return view;
    }
}
