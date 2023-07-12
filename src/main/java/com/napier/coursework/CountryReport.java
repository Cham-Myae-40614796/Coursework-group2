package com.napier.coursework;

/**
 * Get the country data and population data to display them in report
 * @author Cham Myae Pyae Sone
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

import java.sql.*;
import java.util.ArrayList;

public class CountryReport {

    private Connection conn = null;

//    private int topLimit = 5;
    private String continent = "Asia";
    private String region = "Eastern Asia";

    private String tableFormat = "| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n";

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generateCountryReport(){
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                + "FROM country, city" +
                "WHERE country.Capital = city.ID " +
                "ORDER BY country.Population DESC";
        displayCountries(query,"World", "");

        query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                + "FROM country, city" +
                "WHERE country.Capital = city.ID," +
                "AND country.Continent = " + continent + " " +
                "ORDER BY country.Population DESC";
        displayCountries(query,"Continent", "");

        query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                + "FROM country, city" +
                "WHERE country.Capital = city.ID," +
                "AND country.Region = " + region + " " +
                "ORDER BY country.Population DESC";
        displayCountries(query,"Region", "");

//        System.out.println(getCountries());
    }

//    public void displayCountries(){
    public ArrayList<Country> extractCountries(String query){

        try
        {
            // Create an SQL statement
            Statement stmt = conn.createStatement();

            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                    + "FROM country, city" +
                    "WHERE country.Capital = city.ID " +
                    "ORDER BY country.Population DESC";
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
//            System.out.println(country);
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountries(String query, String type, String name){
        ArrayList<Country> extractedCountries = extractCountries(query);
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        String title = "Populated Countries in the " + type;

        if (type != "World") {
            title +=  " (" + name + ")";
        }
        System.out.printf("| %-89s |%n", title);
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf(tableFormat, "Code", "Country Name", "Continent", "Region", "Population", "Capital");
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        for (int i = 0; i < extractedCountries.size();i++)
        {
            System.out.printf(tableFormat,
                    extractedCountries.get(i).getCountryCode(),
                    extractedCountries.get(i).getCountryName(),
                    extractedCountries.get(i).getContinent(),
                    extractedCountries.get(i).getRegion(),
                    extractedCountries.get(i).getPopulation(),
                    extractedCountries.get(i).getCapital());
        }
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

    }


