package com.napier.coursework;
/**
 * Represents the App
 * @author Thar Htet Nyan
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

public class App {

    /**
     * the main method of Java App
     * @param args
     */
    public static void main(String[] args){

        // Create a DatabaseConnection Object
        DatabaseConnection dbConn = new DatabaseConnection();
        // Make connection to database
        dbConn.connect();


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

        PopulationReport pr = new PopulationReport();
        pr.setConn(dbConn.getConn());
        pr.generatePopulationReport();
      
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
