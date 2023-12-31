package com.napier.coursework;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the App
 * @author Thar Htet Nyan
 * @version 0.1.0.3
 * @since 0.1.0.2
 */

public class App {

    /**
     * Version of the App
     */
    private static String version = "0.1.0.4";
    /**
     * the main method of Java App
     * @param args
     */
    public static void main(String[] args){

        System.out.println("Version: " + version);
        // Create a DatabaseConnection Object
        DatabaseConnection dbConn = new DatabaseConnection();
        // Make connection to database
        dbConn.connect("db", 3306);


        // Create a CountryReport Object
        CountryReport cotyr = new CountryReport();
        // Set connection for CountryReport Object
        cotyr.setConn(dbConn.getConn());
        // Generate the reports
        cotyr.generateCountryReport();

        // Create a CityReport Object
        CityReport cr = new CityReport();
        // Set connection for CityReport Object
        cr.setConn(dbConn.getConn());
        // Generate the reports
        cr.generateCityReport();
      
        //Create a CapitalCityReport Object
        CapitalCityReport ccr = new CapitalCityReport();
        // Set connection for CapitalCityReport Object
        ccr.setConn(dbConn.getConn());
        // Generate the reports
        ccr.generateCapitalCityReport();

        // Create a PopulationReport Object
        PopulationReport pr = new PopulationReport();
        // Set connection for PopulationReport Object
        pr.setConn(dbConn.getConn());
        // Generate the reports
        pr.generatePopulationReport();
      
        // Create a AdditionalPopulationReport Object
        AdditionalPopulationReport apr = new AdditionalPopulationReport();
        // Set connection for AdditionalPopulationReport Object
        apr.setConn(dbConn.getConn());
        // Generate the reports
        apr.generateAdditionalPopulationReport();
      
        // Create a CapitalCityReport Object
        LanguageReport lgr = new LanguageReport();
        // Set connection for CapitalCityReport Object
        lgr.setConn(dbConn.getConn());
        // Generate the reports
        lgr.generateLanguageReport();
      

        // Close the connection
        dbConn.disconnect();

    }

}
