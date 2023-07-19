package com.napier.coursework;
/**
 *  Represents Population Data
 * @author Kyi Phyu Khin
 * @version 0.1.0.3
 * @since 0.1.0.3
 */
public class Population {

    /**
     * private string to store name
     */
    private String Name;

    /**
     * private string to store continent
     */
    private String Continent;

    /**
     * private string to store region
     */
    private String Region;

    /**
     * private string to store country
     */
    private String Country;

    /**
     * private string to store district
     */
    private String District;

    /**
     * private string to store city
     */
    private String City;

    /**
     * private string to store name of the country
     */
    private String CountryName;

    /**
     * private long to store total population
     */
    private long TotalPopulation;

    /**
     * private long to store people living in the city
     */
    private long PopulationInCities;

    /**
     * private long to store people who are not living in the city
     */
    private long PopulationNotInCities;

    /**
     * public method to set name
     *
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * public method to set continent
     *
     * @param continent
     */
    public void setContinent(String continent) {
        Continent = continent;
    }

    /**
     * public method to set region
     *
     * @param region
     */
    public void setRegion(String region) {
        Region = region;
    }

    /**
     * public method to set country
     *
     * @param country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * public method to set district
     *
     * @param district
     */
    public void setDistrict(String district) {
        District = district;
    }

    /**
     * public method to set city
     *
     * @param city
     */
    public void setCity(String city) {
        District = city;
    }

    /**
     * public method to set country name
     *
     * @param countryName
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    /**
     * public method to set total population
     *
     * @param totalPopulation
     */
    public void setTotalPopulation(long totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    /**
     * public method to set people living in the cities
     *
     * @param populationInCities
     */
    public void setPopulationInCities(long populationInCities) {
        PopulationInCities = populationInCities;
    }

    /**
     * public method to set people who are not living in the cities
     *
     * @param populationNotInCities
     */
    public void setPopulationNotInCities(long populationNotInCities) {
        PopulationNotInCities = populationNotInCities;
    }

    /**
     * public method to return name
     *
     * @return name
     */
    public String getName() {
        return Name;
    }

    /**
     * public method to return continent
     *
     * @return continent
     */
    public String getContinent() {
        return Continent;
    }

    /**
     * public method to return region
     *
     * @return region
     */
    public String getRegion() {
        return Region;
    }

    /**
     * public method to return country
     *
     * @return country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * public method to return district
     *
     * @return district
     */
    public String getDistrict() {
        return District;
    }

    /**
     * public method to return city
     *
     * @return city
     */
    public String getCity() {
        return City;
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
     * public method to return total population
     *
     * @return the total population
     */
    public long getTotalPopulation() {
        return TotalPopulation;
    }

    /**
     * public method to return population in cities
     *
     * @return people living in the cities
     */
    public long getPopulationInCities() {
        return PopulationInCities;
    }

    /**
     * public method to return population not in cities
     *
     * @return people not living in the cities
     */
    public long getPopulationNotInCities() {
        return PopulationNotInCities;
    }
}