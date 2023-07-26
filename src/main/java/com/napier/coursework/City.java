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
    private String cityName;

    /**
     * private string to store country name
     */
    private String countryName;

    /**
     * private string to store district
     */
    private String district;

    /**
     * private integer to store city population
     */
    private int population;

    /**
     * public method to set city name
     *
     * @param cityName the name of the city
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * public method to set country name
     *
     * @param countryName the name of the country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * public method to set district
     *
     * @param district the name of the district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * public method to set population
     *
     * @param population the number of population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * public method to return city name
     *
     * @return the name of the city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * public method to return country name
     *
     * @return the name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * public method to return district
     *
     * @return the name of the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * public method to return population
     *
     * @return the number of population
     */
    public int getPopulation() {
        return population;
    }
}
