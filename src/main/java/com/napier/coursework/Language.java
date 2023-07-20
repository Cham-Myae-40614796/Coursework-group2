package com.napier.coursework;

/**
 * Represents Language Data
 * @author Chan Myae Pyae Sone
 * @version 0.1.0.2
 * @since 0.1.0.2
 */
public class Language {
    
    /**
     * private string to store country code
     */
    private String CountryLanguage;

    /**
     * private string to store country name
     */
    private String Percentage;

    /**
     * private string to store country name
     */
    private String CountryPopulation;

    /**
     * private string to store country name
     */
    private String CountryName;


    public void setCountryLanguage(String countryLanguage) {
        CountryLanguage = countryLanguage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public void setCountryPopulation(String countryPopulation) {
        CountryPopulation = countryPopulation;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryLanguage() {
        return CountryLanguage;
    }

    public String getPercentage() {
        return Percentage;
    }

    public String getCountryPopulation() {
        return CountryPopulation;
    }

    public String getCountryName() {
        return CountryName;
    }
}
