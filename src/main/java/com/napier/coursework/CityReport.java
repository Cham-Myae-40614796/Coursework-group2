package com.napier.coursework;
/**
 * Creates reports related to city
 * Extracts data from database and display reports for each user story
 * @author Thar Htet Nyan, Kyi Phyu Khin
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CityReport {

    /**
     * private Connection to SQL database
     */
    private Connection conn = null;

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
     * private string to set country of query
     */
    private String country = "Algeria";

    /**
     * private string to set district of query
     */
    private String district = "Adana";

    /**
     * private string to format table
     */
    private String tableFormat = "| %-20s | %-25s | %-20s | %-15s |%n";


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
     * city population
     */
    public void generateCityReport() {

        // define WHERE part of query
        String whereClause = "";
        String whereClause1 = "WHERE country.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE country.Region = '" + region + "' ";
        String whereClause3 = "WHERE country.Name = '" + region + "' ";
        String whereClause4 = "WHERE city.District = '" + region + "' ";

        // display a table of information on cities in the world organized by their population in descending order
        displayCities(whereClause, "World", "", false);
        // display a table of information on cities in a continent organized by their population in descending order
        displayCities(whereClause1, "Continent", continent, false);
        // display a table of information on cities in a region organized by their population in descending order
        displayCities(whereClause2, "Region", region, false);
        // display a table of information on cities in a country organized by their population in descending order
        displayCities(whereClause3, "Country", country, false);
        // display a table of information on cities in a district organized by their population in descending order
        displayCities(whereClause4, "District", district, false);

        // display a table of information on top populated cities in the world
        displayCities(whereClause, "World", "", true);
        // display a table of information on top populated cities in a continent
        displayCities(whereClause1, "Continent", continent, true);
        // display a table of information on top populated cities in a region
        displayCities(whereClause2, "Region", region, true);
        // display a table of information on top populated cities in a country
        displayCities(whereClause3, "Country", country, true);
        // display a table of information on top populated cities in a district
        displayCities(whereClause4, "District", district, true);
    }

    /**
     * private method to extract cities data from SQL database using query
     *
     * @return the arraylist of extracted cities data
     */
    private ArrayList<City> extractCities(String whereClause, boolean isTop)
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
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

            // Extract necessary cities information
            ArrayList<City> cities = new ArrayList<City>();

            // loop until all the extracted data is added to cities array list
            while (resultData.next())
            {
                // create new object to add to cities array list
                City cty = new City();
                // add the extracted data to city object
                cty.setCityName(resultData.getString("city.Name"));
                cty.setCountryName(resultData.getString("country.Name"));
                cty.setDistrict(resultData.getString("city.District"));
                cty.setPopulation(resultData.getInt("city.Population"));

                // add the city object to cities array list
                cities.add(cty);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * private method to reformat population and
     * display the extracted cities data in a tabular format
     */
    private void displayCities(String whereClause, String type, String name, boolean isTop)
    {
        // create new arraylist to store the arraylist of extracted cities data
        ArrayList<City> extractedCities = extractCities(whereClause, isTop);

        // skip a line and make a table
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "Populated Cities in the " + type;
        // if the data is top limited type, add some more text in title
        if (isTop == true){
            title = "Top " + topLimit + " " + title;
        }

        // if the type is not world, add some more text in title
        if (type != "World") {
            title +=  " (" + name + ")";
        }
        // print out the title
        System.out.printf("| %-89s |%n", title);
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "City Name", "Country Name", "District", "Population");
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        // print out table records
        if (extractedCities != null) {
            for (int i = 0; i < extractedCities.size(); i++) {
                System.out.printf(tableFormat,
                        extractedCities.get(i).getCityName(),
                        extractedCities.get(i).getCountryName(),
                        extractedCities.get(i).getDistrict(),
                        NumberFormat.getInstance(Locale.US).format(extractedCities.get(i).getPopulation()));
            }
        } else {
          // handles null records
          System.out.printf("| %-89s |%n", "No records");
        }
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}