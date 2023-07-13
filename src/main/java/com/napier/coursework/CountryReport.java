package com.napier.coursework;

/**
 * Get the country data and population data to display them in report
 * @author Cham Myae Pyae Sone
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CountryReport {

    private Connection conn = null;

    private int topLimit = 5;
    private String continent = "Asia";
    private String region = "Eastern Asia";

    private String tableFormat = "| %-5s | %-20s | %-15s | %-20s | %-15s | %-15s |%n";

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generateCountryReport(){

        String whereClause = "";
        String whereClause1 = "AND country.Continent = '" + continent + "' ";
        String whereClause2 = "AND country.Region = '" + region + "' ";

        displayCountries(whereClause,"World", "", false);

        displayCountries(whereClause1,"Continent", continent, false);

        displayCountries(whereClause2,"Region", region, false);



//        System.out.println(getCountries());
    }

//    public void displayCountries(){
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


