package com.napier.coursework;
/**
 * Creates reports related to city
 * Extract data from database and display reports for each user story
 * @author Thar Htet Nyan
 * @version 0.1.0.2
 * @since 0.1.0.2
 */

import java.sql.*;
import java.util.ArrayList;

public class CityReport {
    private Connection conn = null;

    private int topLimit = 5;
    private String continent = "Asia";
    private String region = "Eastern Asia";
    private String country = "Algeria";
    private String district = "Adana";

    private String tableFormat = "| %-20s | %-20s | %-20s | %-20s |%n";


    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generateCityReport() {
        // define query
        String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC ";
        displayCities(query, "World", "");

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC ";
        displayCities(query, "Continent", continent);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC ";
        displayCities(query, "Region", region);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC ";
        displayCities(query, "Country", country);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC ";
        displayCities(query, "District", district);

         query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + Integer.toString(topLimit);
        System.out.println(query);
        displayCities(query, "World", "");

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + Integer.toString(topLimit);
        displayCities(query, "Continent", continent);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + Integer.toString(topLimit);
        displayCities(query, "Region", region);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + Integer.toString(topLimit);
        displayCities(query, "Country", country);

        query = "SELECT city.Name, country.Name, city.District, city.Population " +
                "FROM city " +
                "INNER JOIN country " +
                "ON city.CountryCode = country.Code " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + Integer.toString(topLimit);
        displayCities(query, "District", district);
    }

    private ArrayList<City> extractCities(String query)
    {

        try
        {
            // Create an SQL statement
            Statement stmt = conn.createStatement();
            // Execute SQL statement
            ResultSet resultData = stmt.executeQuery(query);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultData.next())
            {
                City cty = new City();
                cty.setCityName(resultData.getString("city.Name"));
                cty.setCountryName(resultData.getString("country.Name"));
                cty.setDistrict(resultData.getString("city.District"));
                cty.setPopulation(resultData.getInt("city.Population"));
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

    private void displayCities(String query, String type, String name)
    {
        ArrayList<City> extractedCities = extractCities(query);
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        if (type != "World") {
            System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the " + type + " (" + name + ")");
        }else {
            System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the " + type);
        }
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf(tableFormat, "City Name", "Country Name", "District", "Population");
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        for (int i = 0; i < extractedCities.size();i++)
        {
            System.out.printf(tableFormat,
                    extractedCities.get(i).getCityName(),
                    extractedCities.get(i).getCountryName(),
                    extractedCities.get(i).getDistrict(),
                    extractedCities.get(i).getPopulation());
        }
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}