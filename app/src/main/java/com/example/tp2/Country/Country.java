package com.example.tp2.Country;

import java.io.Serializable;
// Classe pour stocker les données des pays
public class Country implements Serializable {
    private String name;
    private String capital;
    private String population;

    public Country(String name, String capital, String population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }
}