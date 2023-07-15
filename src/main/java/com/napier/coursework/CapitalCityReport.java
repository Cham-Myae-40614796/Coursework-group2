package com.napier.coursework;
/**
 * Creates reports related to city
 * Extracts data from database and display reports for each user story
 * @author Thar Htet Nyan, Cham Myae Pyae Sone
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CapitalCityReport {

    /**
     * private Connection to SQL database
     */
    private Connection conn = null;

    /**
     * Number of query for table titles
     */
    private int query_count = 17;

    /**
     * private integer to set top limit of query
     */
    private int topLimit = 5;

    /**
     * private string to set continent name of query
     */
    private String continent = "Asia";

    /**
     * private string to set region of query
     */
    private String region = "Eastern Asia";

    /**
     * private string to format table
     */
    private String tableFormat = "| %-35s | %-37s | %-15s |%n";


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
     * the main public method used to generate different reports of
     * capital city population
     */
    public void generateCapitalCityReport() {

        // define WHERE part of query
        String whereClause = "";
        String whereClause1 = "WHERE country.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE country.Region = '" + region + "' ";

        // display a table of information on capital cities in the world organized by their population in descending order
        displayCapitalCities(whereClause,"World", "", false);
        // display a table of information on capital cities in a continent organized by their population in descending order
        displayCapitalCities(whereClause1, "Continent", continent, false);
        // display a table of information on capital cities in a region organized by their population in descending order
        displayCapitalCities(whereClause2, "Region", region, false);

        // display a table of information on top populated capital cities in the world
        displayCapitalCities(whereClause,"World", "", true);
        // display a table of information on top populated capital cities in a continent
        displayCapitalCities(whereClause1, "Continent", continent, true);
        // display a table of information on top populated capital cities in a region
        displayCapitalCities(whereClause2, "Region", region, true);

    }

    /**
     * private method to extract capital cities data from SQL database using query
     *
     * @return the arraylist of extracted capital cities data
     */
    private ArrayList<City> extractCapitalCities(String whereClause, boolean isTop)
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.Population " +
                    "FROM country " +
                    "LEFT JOIN city " +
                    "ON country.Capital = city.ID " +
                    whereClause +
                    "ORDER BY city.Population DESC ";

            // if the query is top limited type, add limit to query
            if (isTop == true){
                query += "LIMIT " + Integer.toString(topLimit);
            }

            // Create an SQL statement
            Statement stmt = conn.createStatement();

            // Execute SQL statement
            ResultSet resultData = stmt.executeQuery(query);

            // Extract necessary capital cities information
            ArrayList<City> capitalCities = new ArrayList<City>();

            // loop until all the extracted data is added to capitalCities array list
            while (resultData.next())
            {
                // create new object to add to capitalCities array list
                City cty = new City();
                // add the extracted data to city object
                cty.setCityName(resultData.getString("city.Name"));
                cty.setCountryName(resultData.getString("country.Name"));
                cty.setPopulation(resultData.getInt("city.Population"));

                // add the city object to capitalCities array list
                capitalCities.add(cty);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }

    /**
     * private method to reformat population and
     * display the extracted capital cities data in a tabular format
     */
    private void displayCapitalCities(String whereClause, String type, String name, boolean isTop)
    {
        // create new arraylist to store the arraylist of extracted capital cities data
        ArrayList<City> extractedCapitalCities = extractCapitalCities(whereClause, isTop);

        // skip a line and make a table
        System.out.println();
        System.out.printf("-------------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "Populated Capital Cities in the " + type;
        // if the data is top limited type, add some more text in title
        if (isTop == true){
            title = "Top " + topLimit + " " + title;
        }

        // if the type is not world, add some more text in title
        if (type != "World") {
            title +=  " (" + name + ")";
        }
        // add numbering to the title
        title = query_count + ". " + title;
        // increase the count by one
        query_count += 1;
        // print out the title
        System.out.printf("| %-93s |%n", title);
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "City Name", "Country Name", "Population");
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        // print out table records
        if (extractedCapitalCities != null) {
            for (int i = 0; i < extractedCapitalCities.size(); i++) {
                String cityNameText = extractedCapitalCities.get(i).getCityName();
                if (cityNameText == null) {
                    cityNameText = "-";
                }
                System.out.printf(tableFormat,
                        cityNameText,
                        extractedCapitalCities.get(i).getCountryName(),
                        NumberFormat.getInstance(Locale.US).format(extractedCapitalCities.get(i).getPopulation()));
            }
        } else {
            // handles null records
            System.out.printf("| %-93s |%n", "No records");
        }
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
