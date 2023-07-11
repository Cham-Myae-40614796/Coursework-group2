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

    private int top_limit = 5;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generateCityReport() {
        displayTopCitiesInContinent();
    }

    public ArrayList<City> extractTopCitiesInContinent()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population " +
                    "DESC LIMIT " +
                    Integer.toString(top_limit);
            // Create an SQL statement
            Statement stmt = conn.createStatement();
            // Execute SQL statement
            ResultSet result_data = stmt.executeQuery(query);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (result_data.next())
            {
                City cty = new City();
                cty.setCityName(result_data.getString("city.Name"));
                cty.setCountryName(result_data.getString("country.Name"));
                cty.setDistrict(result_data.getString("city.District"));
                cty.setPopulation(result_data.getDouble("city.Population"));
                cities.add(cty);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayTopCitiesInContinent(){
        ArrayList<City> extracted_cities = extractTopCitiesInContinent();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + top_limit + " Populated Cities in the World");
        String format = "| %-20s | %-20s | %-20s | %-20s |%n";
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf(format, "City Name", "Country Name", "District", "Population");
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        for (int i = 0; i < extracted_cities.size();i++)
        {
            System.out.printf(format,
                    extracted_cities.get(i).getCityName(),
                    extracted_cities.get(i).getCountryName(),
                    extracted_cities.get(i).getDistrict(),
                    extracted_cities.get(i).getPopulation());
//            System.out.println("Code: " + extracted_cities.get(i).Code + "Capital: " + lol.get(i).Capital);
        }
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.println();
    }
}
