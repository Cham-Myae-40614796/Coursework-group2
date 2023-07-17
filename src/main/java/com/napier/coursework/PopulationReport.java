package com.napier.coursework;

/**
 * Creating reports for countries related issues
 * Get the country data and population data to display them in report
 * @author Cham Myae Pyae Sone, Htet Myat Thiri
 * @version 0.1.0.2
 * @since 0.1.0.2
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
    private int query_count = 23;

    /**
     * private string method for generating table format for output display
     */
    private String tableFormat = "| %-40s | %-20s | %-20s | %-21s |%n";

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

        displayPopulation("Continent");
        displayPopulation("Region");
        displayPopulation("Country");

    }

    /**
     * protected method to extract population data from SQL database using query
     *
     * @return the arraylist of extracted population data
     */
    protected ArrayList<Population> extractPopulation(String type)
    {
        try
        {
            Statement stmt = conn.createStatement();

            String query = "SELECT cnty." + type + ", SUM(cnty.Population) AS TotalContinentPopulation, cty.TotalCityPopulation, (SUM(cnty.Population) - cty.TotalCityPopulation) AS TotalNonCityPopulation " +
                    "FROM country AS cnty " +
                    "INNER JOIN (" +
                    "SELECT country." + type + ", SUM(city.Population) AS TotalCityPopulation " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON CountryCode = country.Code " +
                    "GROUP BY country." + type + " "+
                    ") AS cty " +
                    "ON cty." + type + " = cnty." + type +" " +
                    "GROUP BY cnty." + type + ";";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()){
                Population p = new Population();
                p.setName(rset.getString("cnty." + type));
                p.setTotalPopulation(rset.getLong("TotalContinentPopulation"));
                p.setPopulationInCities(rset.getLong("TotalCityPopulation"));
                p.setPopulationNotInCities(rset.getLong("TotalNonCityPopulation"));
                population.add(p);
            }

            return population;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
        /**
         * protected method to reformat population and
         * display the extracted population data in a tabular format
         */
    protected void displayPopulation(String type) {
        // create new arraylist to store the arraylist of extracted population data
        ArrayList<Population> extractedPopulation;
        if (type == "Country") {
            extractedPopulation = extractPopulation("Name");
        }else {
            extractedPopulation = extractPopulation(type);
        }
        System.out.println();
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");

        String title = "Number of People Living in the Cities and Not in the Cities in each " + type;

        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-110s |%n", title);
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        // print out table headings

        System.out.printf(tableFormat, type + " Name", "Total Population", "Total Population in", "Total Population not");
        System.out.printf(tableFormat, "", "", "Cities", "in Cities");
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        if(extractedPopulation != null){
            for (Population pop : extractedPopulation){
                System.out.printf(tableFormat,
                        pop.getName(),
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationInCities()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationNotInCities()));
            }
        } else {
            System.out.printf("| %-110s |%n", "No records");
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
