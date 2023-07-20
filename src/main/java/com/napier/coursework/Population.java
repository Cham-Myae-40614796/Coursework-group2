package com.napier.coursework;

public class Population {

    private String Name;

    private String Continent;

    private String Region;

    private String CountryName;

    private long TotalPopulation;

    private long PopulationInCities;

    private long PopulationNotInCities;

    private long CityPopulationPercentage;

    private long NonCityPopulationPercentage;

    public void setName(String name) {
        Name = name;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public void setTotalPopulation(long totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    public void setPopulationInCities(long populationInCities) {
        PopulationInCities = populationInCities;
    }

    public void setPopulationNotInCities(long populationNotInCities) {
        PopulationNotInCities = populationNotInCities;
    }

    public void setCityPopulationPercentage(long cityPopulationPercentage) {
        CityPopulationPercentage = cityPopulationPercentage;
    }

    public void setNonCityPopulationPercentage(long nonCityPopulationPercentage) {
        NonCityPopulationPercentage = nonCityPopulationPercentage;
    }

    public String getName() {
        return Name;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }

    public String getCountryName() {
        return CountryName;
    }

    public long getTotalPopulation() {
        return TotalPopulation;
    }

    public long getPopulationInCities() {
        return PopulationInCities;
    }

    public long getPopulationNotInCities() {
        return PopulationNotInCities;
    }

    public long getCityPopulationPercentage() {
        return CityPopulationPercentage;
    }

    public long getNonCityPopulationPercentage() {
        return NonCityPopulationPercentage;
    }
}

