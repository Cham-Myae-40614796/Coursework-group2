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
    private String tableFormat = "| %-5s | %-20s | %-15s | %-20s | %-15s | %-15s |%n";

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
        String whereClause1 = "AND country.Continent = '" + continent + "' ";
        String whereClause2 = "AND country.Region = '" + region + "' ";

        //display the countries in the world needed to be sorted by their number of the population in descending order
        displayCountries(whereClause,"World", "", false);

        //display the countries in the continent needed to be sorted by their number of the population in descending order
        displayCountries(whereClause1,"Continent", continent, false);

        //display the countries in the region needed to be sorted by their number of the population in descending order
        displayCountries(whereClause2,"Region", region, false);

        //display the number of top populated countries in the world that the user provided will be listed
        displayCountries(whereClause,"World", "", true);

        //display the number of top populated countries in the continent that the user provided will be listed
        displayCountries(whereClause1,"Continent", continent, true);

        //display the number of top populated countries in the region that the user provided will be listed
        displayCountries(whereClause2,"Region", region, true);

    }

    /**
     * private method to extract countries data from SQL database using query
     *
     * @return the arraylist of extracted countries data
     */
    public ArrayList<Country> extractCountries(String whereClause, boolean isTop){

        try
        {
            // Create an SQL statement
            Statement stmt = conn.createStatement();

            // Create string for SQL statement

            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                    + "FROM country, city " +
                    "WHERE country.Capital = city.ID " +
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
    public void displayCountries(String whereClause, String type, String name, boolean isTop){
        ArrayList<Country> extractedCountries = extractCountries(whereClause, isTop);
        System.out.println();
        System.out.printf("-------------------------------------------------------------------------------------------------------------%n");
        String title = "Populated Countries in the " + type;

        if (isTop == true){
            title = "Top " + topLimit + " " + title;
        }
        if (type != "World") {
            title +=  " (" + name + ")";
        }
        System.out.printf("| %-105s |%n", title);
        System.out.printf("-------------------------------------------------------------------------------------------------------------%n");
        System.out.printf(tableFormat, "Code", "Country Name", "Continent", "Region", "Population", "Capital");
        System.out.printf("-------------------------------------------------------------------------------------------------------------%n");
        if (extractedCountries != null) {
            for (int i = 0; i < extractedCountries.size(); i++) {
                System.out.printf(tableFormat,
                        extractedCountries.get(i).getCountryCode(),
                        extractedCountries.get(i).getCountryName(),
                        extractedCountries.get(i).getContinent(),
                        extractedCountries.get(i).getRegion(),
                        extractedCountries.get(i).getPopulation(),
                        extractedCountries.get(i).getCapital());
            }
        } else {
            // handles null records
            System.out.printf("| %-105s |%n", "No records");
        }
        System.out.printf("-------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}


