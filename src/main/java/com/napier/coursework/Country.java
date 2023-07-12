package com.napier.coursework;

public class Country {

    private String CountryCode;

    private String CountryName;

    private String Continent;

    private String Region;

    private String Population;

    private String Capital;

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }

    public String getPopulation() {
        return Population;
    }

    public String getCapital() {
        return Capital;
    }
}
