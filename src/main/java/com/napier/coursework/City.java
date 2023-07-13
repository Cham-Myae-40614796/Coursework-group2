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
     *
     * @param cityName the name of the city
     */
    public void setCityName(String cityName) {
        CityName = cityName;
    }

    /**
     * public method to set country name
     *
     * @param countryName the name of the country
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    /**
     * public method to set district
     *
     * @param district the name of the district
     */
    public void setDistrict(String district) {
        District = district;
    }

    /**
     * public method to set population
     *
     * @param population the number of population
     */
    public void setPopulation(int population) {
        Population = population;
    }

    /**
     * public method to return city name
     *
     * @return the name of the city
     */
    public String getCityName() {
        return CityName;
    }

    /**
     * public method to return country name
     *
     * @return the name of the country
     */
    public String getCountryName() {
        return CountryName;
    }

    /**
     * public method to return district
     *
     * @return the name of the district
     */
    public String getDistrict() {
        return District;
    }

    /**
     * public method to return population
     *
     * @return the number of population
     */
    public int getPopulation() {
        return Population;
    }
}
