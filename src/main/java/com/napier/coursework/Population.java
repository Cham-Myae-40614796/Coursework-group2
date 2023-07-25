package com.napier.coursework;
/**
 * Represents Population Data
 * @author Thar Htet Nyan, Kyi Phyu Khin
 * @version 0.1.0.3
 * @since 0.1.0.3
 */

public class Population {

    /**
     * private string to store country and city name
     */
    private String name;

    /**
     * private long to store total population
     */
    private long totalPopulation;

    /**
     * private long to store people living in the city
     */
    private long populationInCities;

    /**
     * private string to store people living in the city percentage
     */
    private String cityPopulationPercentage;

    /**
     * private long to store people who are not living in the city
     */
    private long populationNotInCities;

    /**
     * private string to store people not living in the city percentage
     */
    private String nonCityPopulationPercentage;

    /**
     * public method to set name of the country and city
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * public method to set total population
     *
     * @param totalPopulation
     */
    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    /**
     * public method to set people living in the cities
     *
     * @param populationInCities
     */
    public void setPopulationInCities(long populationInCities) {
        this.populationInCities = populationInCities;
    }

    /**
     * public method to set people living in the cities with percentage
     *
     * @param cityPopulationPercentage
     */
    public void setCityPopulationPercentage(String cityPopulationPercentage) {
        this.cityPopulationPercentage = cityPopulationPercentage;
    }

    /**
     * public method to set people who are not living in the cities
     *
     * @param populationNotInCities
    */
    public void setPopulationNotInCities(long populationNotInCities) {
        this.populationNotInCities = populationNotInCities;
    }
  
    /**
     * public method to set people not living in the cities with percentage
     *
     * @param nonCityPopulationPercentage
     */
    public void setNonCityPopulationPercentage(String nonCityPopulationPercentage) {
        this.nonCityPopulationPercentage = nonCityPopulationPercentage;
    }

    /**
     * public method to return name of the country and city
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * public method to return total population
     *
     * @return the total population
     */
    public long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * public method to return population in cities
     *
     * @return people living in the cities
     */
    public long getPopulationInCities() {
        return populationInCities;
    }

    /**
     * public method to return population of people living in cities with percentage
     *
     * @return people living in the cities with percentage
     */
    public String getCityPopulationPercentage() {
        return cityPopulationPercentage;
    }

    /**
     * public method to return population not in cities
     *
     * @return people not living in the cities
     */
    public long getPopulationNotInCities() {
        return populationNotInCities;
    }

    /**
     * public method to return population not in cities with percentage
     *
     * @return people not living in the cities with percentage
     */
    public String getNonCityPopulationPercentage() {
        return nonCityPopulationPercentage;
    }
}

