package com.example.tp2.Country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryList;
    private OnItemClickListener listener;

    public CountryAdapter(List<Country> countryList, OnItemClickListener listener) {
        this.countryList = countryList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Country selectedCountry);
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryNameTextView;
        TextView countryCapitalTextView;
        TextView countryPopulationTextView;

        public CountryViewHolder(View itemView) {
            super(itemView);
            countryNameTextView = itemView.findViewById(R.id.countryNameTextView);
        }
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country currentCountry = countryList.get(position);

        //Correct way to access the TextViews
        holder.countryNameTextView.setText(currentCountry.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(currentCountry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}