package com.napier.coursework;
/**
 * Creates reports related to city
 * Extracts data from database and display reports for each user story
 * @author Thar Htet Nyan, Kyi Phyu Khin
 * @version 0.1.0.3
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
     * Number of query for table titles
     */
    private int query_count = 7;

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
    private String tableFormat = "| %-35s | %-37s | %-22s | %-15s |%n";


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
        String whereClause3 = "WHERE country.Name = '" + country + "' ";
        String whereClause4 = "WHERE city.District = '" + district + "' ";


        // create new arraylist to store the arraylist of extracted cities data
        ArrayList<City> extractedCities = extractCities(whereClause, false);
        // display a table of information on cities in the world organized by their population in descending order
        displayCities(extractedCities,"World", "", false);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause1, false);
        // display a table of information on cities in a continent organized by their population in descending order
        displayCities(extractedCities,"Continent", continent, false);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause2, false);
        // display a table of information on cities in a region organized by their population in descending order
        displayCities(extractedCities,"Region", region, false);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause3, false);
        // display a table of information on cities in a country organized by their population in descending order
        displayCities(extractedCities,"Country", country, false);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause4, false);
        // display a table of information on cities in a district organized by their population in descending order
        displayCities(extractedCities,"District", district, false);



        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause, true);
        // display a table of information on top populated cities in the world
        displayCities(extractedCities,"World", "", true);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause1, true);
        // display a table of information on top populated cities in a continent
        displayCities(extractedCities,"Continent", continent, true);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause2, true);
        // display a table of information on top populated cities in a region
        displayCities(extractedCities,"Region", region, true);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause3, true);
        // display a table of information on top populated cities in a country
        displayCities(extractedCities,"Country", country, true);

        // store the arraylist of extracted cities data
        extractedCities = extractCities(whereClause4, true);
        // display a table of information on top populated cities in a district
        displayCities(extractedCities,"District", district, true);

    }

    /**
     * protected method to extract cities data from SQL database using query
     *
     * @return the arraylist of extracted cities data
     */
    protected ArrayList<City> extractCities(String whereClause, boolean isTop)
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
     * protected method to reformat population and
     * display the extracted cities data in a tabular format
     */
    protected void displayCities(ArrayList<City> extractedCities, String type, String name, boolean isTop)
    {
        // create new arraylist to store the arraylist of extracted cities data
//        ArrayList<City> extractedCities = extractCities(whereClause, isTop);

        // skip a line and make a table
        System.out.println();
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");

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
        // add numbering to the title
        title = query_count + ". " + title;
        // increase the count by one
        query_count += 1;
        // print out the title
        System.out.printf("| %-118s |%n", title);
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "City Name", "Country Name", "District", "Population");
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");
        if (extractedCities != null) {
            while(extractedCities.remove(null)){

            }
        }

        if (extractedCities == null || extractedCities.size() == 0) {
            // handles null records
            System.out.printf("| %-118s |%n", "No records");
        } else {
            // print out table records
            for (City eCity : extractedCities) {

                if (eCity == null) {
                    continue;
                }

                System.out.printf(tableFormat,
                        eCity.getCityName(),
                        eCity.getCountryName(),
                        eCity.getDistrict(),
                        NumberFormat.getInstance(Locale.US).format(eCity.getPopulation()));
            }
        }
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}