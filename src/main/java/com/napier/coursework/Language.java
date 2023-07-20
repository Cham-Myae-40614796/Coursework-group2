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
    private String Language;

    /**
     * private string to store country name
     */
    private String Percentage;

    /**
     * private string to store country name
     */
    private long Population;


    public void setLanguage(String Language) {
        Language = Language;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    public String getLanguage() {
        return Language;
    }

    public String getPercentage() {
        return Percentage;
    }

    public long getPopulation() {
        return Population;
    }

}
