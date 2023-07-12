package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CapitalCityReport {

    private Connection conn = null;

    private int topLimit = 5;
    private String continent = "Asia";
    private String region = "Eastern Asia";

    private String tableFormat = "| %-20s | %-30s | %-15s |%n";


    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generateCapitalCityReport() {

        String whereClause = "";
        String whereClause1 = "WHERE country.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE country.Region = '" + region + "' ";

        displayCapitalCities(whereClause,"World", "", false);
        displayCapitalCities(whereClause1, "Continent", continent, false);
        displayCapitalCities(whereClause2, "Region", region, false);
//
    }

    private ArrayList<City> extractCapitalCities(String whereClause, boolean isTop)
    {
        try
        {
            // SELECT city.Name, country.Name, city.Population FROM country LEFT JOIN city
            // ON country.Capital = city.ID ORDER BY city.Population DESC
            // define query
            String query = "SELECT city.Name, country.Name, city.Population " +
                    "FROM country " +
                    "LEFT JOIN city " +
                    "ON country.Capital = city.ID " +
                    whereClause +
                    "ORDER BY city.Population DESC ";

            if (isTop == true){
                query += "LIMIT " + Integer.toString(topLimit);
            }

            // Create an SQL statement
            Statement stmt = conn.createStatement();
            // Execute SQL statement
            ResultSet resultData = stmt.executeQuery(query);
            // Extract employee information
            ArrayList<City> capitalCities = new ArrayList<City>();
            while (resultData.next())
            {
                City cty = new City();
                cty.setCityName(resultData.getString("city.Name"));
                cty.setCountryName(resultData.getString("country.Name"));
                cty.setPopulation(resultData.getInt("city.Population"));
                capitalCities.add(cty);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    private void displayCapitalCities(String whereClause, String type, String name, boolean isTop)
    {
        ArrayList<City> extractedCities = extractCapitalCities(whereClause, isTop);
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------%n");
        String title = "Populated Capital Cities in the " + type;
        if (isTop == true){
            title = "Top " + topLimit + " " + title;
        }

        if (type != "World") {
            title +=  " (" + name + ")";
        }
        System.out.printf("| %-71s |%n", title);
        System.out.printf("---------------------------------------------------------------------------%n");
        System.out.printf(tableFormat, "City Name", "Country Name", "Population");
        System.out.printf("---------------------------------------------------------------------------%n");
        for (int i = 0; i < extractedCities.size();i++)
        {
            System.out.printf(tableFormat,
                    extractedCities.get(i).getCityName(),
                    extractedCities.get(i).getCountryName(),
                    extractedCities.get(i).getPopulation());
        }
        System.out.printf("---------------------------------------------------------------------------%n");
        System.out.println();
    }

}
