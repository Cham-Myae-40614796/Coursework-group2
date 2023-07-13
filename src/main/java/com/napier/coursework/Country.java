package com.napier.coursework;
/**
 * Represents Country Data
 * @author Chan Myae Pyae Sone
 * @version 0.1.0.2
 * @since 0.1.0.2
 */
public class Country {

    /**
     * private string to store country code
     */
    private String CountryCode;

    /**
     * private string to store country name
     */
    private String CountryName;

    /**
     * private string to store continent
     */
    private String Continent;

    /**
     * private string to store region
     */
    private String Region;

    /**
     * private integer to store population
     */
    private int Population;

    /**
     * private string to store capital
     */
    private String Capital;

    /**
     * public method to set country code
     *
     * @param countryCode the code of the country
     */
    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
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
     * public method to set continent name
     *
     * @param continent the name of the continent
     */
    public void setContinent(String continent) {
        Continent = continent;
    }

    /**
     * public method to set region name
     *
     * @param region the name of the region
     */
    public void setRegion(String region) {
        Region = region;
    }

    /**
     * public method to set population
     *
     * @param population the number of population in the country
     */
    public void setPopulation(int population) {
        Population = population;
    }

    /**
     * public method to set capital name
     *
     * @param capital the name of the capital
     */
    public void setCapital(String capital) {
        Capital = capital;
    }

    /**
     * public method to return country code
     *
     * @return the code of the country
     */
    public String getCountryCode() {
        return CountryCode;
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
     * public method to return continent name
     *
     * @return the name of the continent
     */
    public String getContinent() {
        return Continent;
    }

    /**
     * public method to return region name
     *
     * @return the name of the region
     */
    public String getRegion() {
        return Region;
    }

    /**
     * public method to return population of the country
     *
     * @return the population of the country
     */
    public int getPopulation() {
        return Population;
    }

    /**
     * public method to return capital name
     *
     * @return the name of the capital
     */
    public String getCapital() {
        return Capital;
    }
}
