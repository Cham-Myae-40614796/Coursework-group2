package com.napier.coursework;

/**
 * Creating reports for population related issues
 * Get the population data to display them in report
 * @author Thar Htet Nyan
 * @version 0.1.0.3
 * @since 0.1.0.3
 */

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PopulationReport {
    /**
     * Connecting to SQL database
     */
    private Connection conn = null;

    /**
     * Number of query for table titles
     */
    private int queryCount = 23;

    /**
     * private string method for generating table format for output display
     */
    private String tableFormat = "| %-20s | %-20s | %-20s | %-10s | %-21s | %-10s |%n";

    /**
     * public method to set SQL database Connection
     * conn has a default value of null.
     * This can be set to use existing database connection.
     *
     * @param conn the SQL database connection
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * the main public method used to generate different reports of  population
     */
    public void generatePopulationReport() {

        String type = "Continent";
        ArrayList<Population> extractedPopulation = extractPopulation(type);
        displayPopulation(extractedPopulation, type);

        type = "Region";
        extractedPopulation = extractPopulation(type);
        displayPopulation(extractedPopulation,type);

        type = "Country";
        extractedPopulation = extractPopulation("Name");
        displayPopulation(extractedPopulation, type);

    }

    /**
     * protected method to extract population data from SQL database using query
     *
     * @return the arraylist of extracted population data
     */
    protected ArrayList<Population> extractPopulation(String type)
    {
        ArrayList<Population> population = new ArrayList<Population>();
        try
        {
            Statement stmt = conn.createStatement();

            String query = "SELECT cnty." + type + ", SUM(cnty.Population) AS TotalContinentPopulation, cty.TotalCityPopulation," +
                    " CONCAT(((cty.TotalCityPopulation/SUM(cnty.Population)) * 100), ' %') AS CityPopulationPercentage," +
                    " (SUM(cnty.Population) - cty.TotalCityPopulation) AS TotalNonCityPopulation, " +
                    "CONCAT((100 - (cty.TotalCityPopulation/SUM(cnty.Population)) * 100), ' %') AS NonCityPopulationPercentage " +
                    "FROM country AS cnty " +
                    "INNER JOIN (" +
                    "SELECT country." + type + ", SUM(city.Population) AS TotalCityPopulation " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON CountryCode = country.Code " +
                    "GROUP BY country." + type + " "+
                    ") AS cty " +
                    "ON cty." + type + " = cnty." + type +" " +
                    "GROUP BY cnty." + type + " " +
                    "ORDER BY cnty." + type + ";";

            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()){
                Population p = new Population();
                p.setName(rset.getString("cnty." + type));
                p.setTotalPopulation(rset.getLong("TotalContinentPopulation"));
                p.setPopulationInCities(rset.getLong("TotalCityPopulation"));
                p.setCityPopulationPercentage(rset.getString("CityPopulationPercentage"));
                p.setPopulationNotInCities(rset.getLong("TotalNonCityPopulation"));
                p.setNonCityPopulationPercentage(rset.getString("NonCityPopulationPercentage"));
                population.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
        return population;
    }
    /**
     * protected method to reformat population and
     * display the extracted population data in a tabular format
     */
    protected void displayPopulation(ArrayList<Population> extractedPopulation, String type) {
        // create new arraylist to store the arraylist of extracted population data
        System.out.println();
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");

        String title = "Number of People Living in the Cities and Not in the Cities in each " + type;

        title = queryCount + ". " + title;

        queryCount += 1;

        System.out.printf("| %-116s |%n", title);
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, type + " Name", "Total Population", "Total Population in", "Percentage", "Total Population not", "Percentage");
        System.out.printf(tableFormat, "", "", "Cities", "", "in Cities", "");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");

        if (extractedPopulation != null) {
            while(extractedPopulation.remove(null)){

            }
        }

        if (extractedPopulation == null || extractedPopulation.isEmpty()) {
            // handles null records
            System.out.printf("| %-116s |%n", "No records");
        } else {
            // print out table records
            for (Population pop : extractedPopulation){
                String eNameText = pop.getName();
                String extraENameText = "";
                if (eNameText.length() > 20){
                    extraENameText = eNameText.substring(20);
                    eNameText = eNameText.substring(0, 20);
                }
                System.out.printf(tableFormat,
                        eNameText,
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationInCities()),
                        pop.getCityPopulationPercentage(),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationNotInCities()),
                        pop.getNonCityPopulationPercentage());
                if (extraENameText != ""){
                    System.out.printf(tableFormat,
                            extraENameText,
                            "",
                            "",
                            "",
                            "",
                            "");
                }
            }
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
