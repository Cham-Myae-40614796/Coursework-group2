package com.napier.coursework;
/**
 * Creates reports related to city
 * Extracts data from database and display reports for each user story
 * @author Thar Htet Nyan, Cham Myae Pyae Sone
 * @version 0.1.0.3
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
    private Connection conn;

    /**
     * Number of query for table titles
     */
    private int queryCount = 17;

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


        // create new arraylist to store the arraylist of extracted cities data
        ArrayList<City> extractedCapitalCities = extractCapitalCities(whereClause, false);
        // display a table of information on cities in the world organized by their population in descending order
        displayCapitalCities(extractedCapitalCities,"World", "", false);

        // store the arraylist of extracted cities data
        extractedCapitalCities = extractCapitalCities(whereClause1, false);
        // display a table of information on cities in a continent organized by their population in descending order
        displayCapitalCities(extractedCapitalCities,"Continent", continent, false);

        // store the arraylist of extracted cities data
        extractedCapitalCities = extractCapitalCities(whereClause2, false);
        // display a table of information on cities in a region organized by their population in descending order
        displayCapitalCities(extractedCapitalCities,"Region", region, false);




        // store the arraylist of extracted cities data
        extractedCapitalCities = extractCapitalCities(whereClause, true);
        // display a table of information on top populated capital cities in the world
        displayCapitalCities(extractedCapitalCities,"World", "", true);

        // store the arraylist of extracted cities data
        extractedCapitalCities = extractCapitalCities(whereClause1, false);
        // display a table of information on top populated capital cities in a continent
        displayCapitalCities(extractedCapitalCities,"Continent", continent, true);

        // store the arraylist of extracted cities data
        extractedCapitalCities = extractCapitalCities(whereClause2, true);
        // display a table of information on top populated capital cities in a region
        displayCapitalCities(extractedCapitalCities,"Region", region, true);

    }

    /**
     * protected method to extract capital cities data from SQL database using query
     *
     * @return the arraylist of extracted capital cities data
     */
    protected ArrayList<City> extractCapitalCities(String whereClause, boolean isTop)
    {
        ArrayList<City> capitalCities = new ArrayList<City>();

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
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
        }
        return capitalCities;

    }

    /**
     * protected method to reformat population and
     * display the extracted capital cities data in a tabular format
     */
    protected void displayCapitalCities(ArrayList<City> extractedCapitalCities, String type, String name, boolean isTop)
    {
        // create new arraylist to store the arraylist of extracted capital cities data
//        ArrayList<City> extractedCapitalCities = extractCapitalCities(whereClause, isTop);

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
        if (!type.equals("World")) {
            title +=  " (" + name + ")";
        }
        // add numbering to the title
        title = queryCount + ". " + title;
        // increase the count by one
        queryCount += 1;
        // print out the title
        System.out.printf("| %-93s |%n", title);
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "City Name", "Country Name", "Population");
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        if (extractedCapitalCities != null) {
            boolean nullCheck = true;
            while(nullCheck){
                nullCheck = extractedCapitalCities.remove(null);
            }
        }

        if (extractedCapitalCities == null || extractedCapitalCities.isEmpty()) {
            // handles null records
            System.out.printf("| %-93s |%n", "No records");
        }
        else {
            // print out table records
            for (City eCaptial : extractedCapitalCities) {

                String cityNameText = eCaptial.getCityName();
                if (cityNameText == null) {
                    cityNameText = "-";
                }
                System.out.printf(tableFormat,
                        cityNameText,
                        eCaptial.getCountryName(),
                        NumberFormat.getInstance(Locale.US).format(eCaptial.getPopulation()));
            }
        }
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
