package com.napier.coursework;

public class City {

    private String CityName;

    private String CountryName;

    private String District;

    private int Population;

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setPopulation(double population) {
        Population = population;
    }

    public String getCityName() {
        return CityName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getDistrict() {
        return District;
    }

    public double getPopulation() {
        return Population;
    }
}
