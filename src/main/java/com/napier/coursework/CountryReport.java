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

public class CountryReport {

    /**
     * Connecting to SQL database
     */
    private Connection conn = null;

    /**
     * Number of query for table titles
     */
    private int query_count = 1;

    /**
     * private integer method limiting printed output for top 5 only
     */
    private int topLimit = 5;

    /**
     * private string method for default value set to "Asia" continent
     */
    private String continent = "Asia";

    /**
     * private string method for default value set to "Eastern Asia" region
     */
    private String region = "Eastern Asia";

    /**
     * private string method for generating table format for output display
     */
    private String tableFormat = "| %-5s | %-20s | %-15s | %-20s | %-15s | %-20s |%n";


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
     * the main public method used to generate different reports of country population
     */
    public void generateCountryReport(){

        String whereClause = "";
        String whereClause1 = "WHERE country.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE country.Region = '" + region + "' ";

        // create new arraylist to store the arraylist of extracted countries data
        ArrayList<Country> extractedCountries = extractCountries(whereClause, false);
        //display the countries in the world needed to be sorted by their number of the population in descending order
        displayCountries(extractedCountries,"World", "", false);

        // create new arraylist to store the arraylist of extracted countries data
        extractedCountries = extractCountries(whereClause1, false);
        //display the countries in the continent needed to be sorted by their number of the population in descending order
        displayCountries(extractedCountries,"Continent", continent, false);

        // create new arraylist to store the arraylist of extracted countries data
        extractedCountries = extractCountries(whereClause2, false);
        //display the countries in the region needed to be sorted by their number of the population in descending order
        displayCountries(extractedCountries,"Region", region, false);



        // create new arraylist to store the arraylist of extracted countries data
        extractedCountries = extractCountries(whereClause, false);
        // display the number of top populated countries in the world that the user provided will be listed
        displayCountries(extractedCountries,"World", "", false);

        // create new arraylist to store the arraylist of extracted countries data
        extractedCountries = extractCountries(whereClause1, false);
        // display the number of top populated countries in the continent that the user provided will be listed
        displayCountries(extractedCountries,"Continent", continent, true);

        // create new arraylist to store the arraylist of extracted countries data
        extractedCountries = extractCountries(whereClause2, true);
        // display the number of top populated countries in the region that the user provided will be listed
        displayCountries(extractedCountries,"Region", region, true);

    }

    /**
     * private method to extract countries data from SQL database using query
     *
     * @return the arraylist of extracted countries data
     */
    protected ArrayList<Country> extractCountries(String whereClause, boolean isTop){

        try
        {
            // Create an SQL statement
            Statement stmt = conn.createStatement();

            // Create string for SQL statement
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                    "FROM country " +
                    "INNER JOIN city " +
                    "ON country.Capital = city.ID " +
                    whereClause +
                    "ORDER BY country.Population DESC ";

            if (isTop == true){
                query += "LIMIT " + Integer.toString(topLimit);
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country coty = new Country();
                coty.setCountryCode(rset.getString("country.Code"));
                coty.setCountryName(rset.getString("country.Name"));
                coty.setContinent(rset.getString("country.Continent"));
                coty.setRegion(rset.getString("country.Region"));
                coty.setPopulation(rset.getInt("country.Population"));
                coty.setCapital(rset.getString("city.Name"));
                country.add(coty);
            }

            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * private method to reformat population and
     * display the extracted countries data in a tabular format
     */
    protected void displayCountries(ArrayList<Country> extractedCountries, String type, String name, boolean isTop){
//        // create new arraylist to store the arraylist of extracted countries data
//        ArrayList<Country> extractedCountries = extractCountries(whereClause, isTop);

        // skip a line and make a table
        System.out.println();
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "Populated Countries in the " + type;
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
        System.out.printf("| %-110s |%n", title);
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "Code", "Country Name", "Continent", "Region", "Population", "Capital");
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        if (extractedCountries != null) {
            while(extractedCountries.remove(null)){

            }
        }
        if (extractedCountries == null || extractedCountries.size() == 0) {
            // handles null records
            System.out.printf("| %-110s |%n", "No records");
        } else {
            for (Country eCountry : extractedCountries) {

                if (eCountry == null) {
                    continue;
                }

                String countryNameText = eCountry.getCountryName();
                String regionText = eCountry.getRegion();
                String capitalText = eCountry.getCapital();

                String extraCountryNameText = "";
                String extraRegionText = "";
                String extraCapitalText = "";

                // checks for any exceeding letter limit
                if (countryNameText.length() > 20) {
                    extraCountryNameText = countryNameText.substring(20);
                    countryNameText = countryNameText.substring(0, 20);
                }
                if (regionText.length() > 20) {
                    extraRegionText = regionText.substring(20);
                    regionText = regionText.substring(0, 20);

                }
                if (capitalText.length() > 20) {
                    extraCapitalText = capitalText.substring(20);
                    capitalText = capitalText.substring(0, 20);
                }

                // print the record
                System.out.printf(tableFormat,
                        eCountry.getCountryCode(),
                        countryNameText,
                        eCountry.getContinent(),
                        regionText,
                        NumberFormat.getInstance(Locale.US).format(eCountry.getPopulation()),
                        capitalText);
                // print an extra row if needed
                if (extraCountryNameText != "" || extraRegionText != "" || extraCapitalText != "") {
                    System.out.printf(tableFormat,
                            "",
                            extraCountryNameText,
                            "",
                            extraRegionText,
                            "",
                            extraCapitalText);
                }
            }
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}


