package com.napier.coursework;

/**
 * Represents Language Data
 * @author Chan Myae Pyae Sone
 * @version 0.1.0.3
 * @since 0.1.0.3
 */
public class Language {
    
    /**
     * private string to store country code
     */
    private String countryLanguage;

    /**
     * private string to store country name
     */
    private String percentage;

    /**
     * private string to store country name
     */
    private long population;

    /**
     * public method to set language
     *
     * @param countryLanguage the language
     */
    public void setCountryLanguage(String countryLanguage) {
        this.countryLanguage = countryLanguage;
    }

    /**
     * public method to set percentage of the total population
     *
     * @param percentage the percentage of the total population
     */
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    /**
     * public method to set the total population
     *
     * @param population the total population
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * public method to return language
     *
     * @return the language
     */
    public String getCountryLanguage() {
        return countryLanguage;
    }

    /**
     * public method to return population percentage
     *
     * @return the population percentage
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     * public method to return the total population
     *
     * @return the total population
     */
    public long getPopulation() {
        return population;
    }

}
