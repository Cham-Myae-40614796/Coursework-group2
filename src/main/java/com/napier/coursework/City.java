package com.napier.coursework;
/**
 * Represents City Data
 * @author Thar Htet Nyan
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

public class City {

    /**
     * private string to store city name
     */
    private String CityName;

    /**
     * private string to store country name
     */
    private String CountryName;

    /**
     * private string to store district
     */
    private String District;

    /**
     * private string to store city population
     */
    private int Population;

    /**
     * public method to set city name
     */
    public void setCityName(String cityName) {
        CityName = cityName;
    }

    /**
     * public method to set country name
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    /**
     * public method to set district
     */
    public void setDistrict(String district) {
        District = district;
    }

    /**
     * public method to set population
     */
    public void setPopulation(int population) {
        Population = population;
    }

    /**
     * public method to return city name
     */
    public String getCityName() {
        return CityName;
    }

    /**
     * public method to return country name
     */
    public String getCountryName() {
        return CountryName;
    }

    /**
     * public method to return district
     */
    public String getDistrict() {
        return District;
    }

    /**
     * public method to return population
     */
    public int getPopulation() {
        return Population;
    }
}
