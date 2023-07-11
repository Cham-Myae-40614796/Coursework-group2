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

        displayTopCitiesInWorld();
        displayTopCitiesInContinent();
        displayTopCitiesInRegion();
        displayTopCitiesInCountry();
        displayTopCitiesInDistrict();
    }

    private ArrayList<City> extractTopCitiesInWorld()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population " +
                    "DESC LIMIT " + Integer.toString(topLimit);
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

    private void displayTopCitiesInWorld()
    {
        ArrayList<City> extractedCities = extractTopCitiesInWorld();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the World");
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

    private ArrayList<City> extractTopCitiesInContinent()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "WHERE country.Continent = '" + continent + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + Integer.toString(topLimit);
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

    private void displayTopCitiesInContinent()
    {
        ArrayList<City> extractedCities = extractTopCitiesInContinent();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the Continent (" + continent + ")");
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

    private ArrayList<City> extractTopCitiesInRegion()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "WHERE country.Region = '" + region + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + Integer.toString(topLimit);
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

    private void displayTopCitiesInRegion()
    {
        ArrayList<City> extractedCities = extractTopCitiesInRegion();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the Region (" + region + ")");
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

    private ArrayList<City> extractTopCitiesInCountry()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "WHERE country.Name = '" + country + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + Integer.toString(topLimit);
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

    private void displayTopCitiesInCountry()
    {
        ArrayList<City> extractedCities = extractTopCitiesInCountry();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the Country (" + country + ")");
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

    private ArrayList<City> extractTopCitiesInDistrict()
    {
        try
        {
            // define query
            String query = "SELECT city.Name, country.Name, city.District, city.Population " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON city.CountryCode = country.Code " +
                    "WHERE city.District = '" + district + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + Integer.toString(topLimit);
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

    private void displayTopCitiesInDistrict()
    {
        ArrayList<City> extractedCities = extractTopCitiesInDistrict();
        System.out.println();
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-89s |%n", "Top " + topLimit + " Populated Cities in the District (" + district + ")");
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
