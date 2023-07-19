package com.napier.coursework;

/**
 * Creates reports related to total population of the world, a continent, a region, a country, a district, and a city
 * Extracts data from database and display reports for each user story
 * @author Kyi Phyu Khin, Htet Myat Thiri
 * @version 0.1.0.3
 * @since 0.1.0.3
 */
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdditionalPopulationReport {

    /**
     * private Connection to SQL database
     */
    private Connection conn = null;

    /**
     * Number of query for table titles
     */
    private int query_count = 26;

    /**
     * private string to set continent name of query
     */
    private String continent = "Europe";

    /**
     * private string to set region name of query
     */
    private String region = "Southern Europe";

    /**
     * private string to set country name of query
     */
    private String country = "Austria";

    /**
     * private string to set district name of query
     */
    private String district = "Gelderland";

    /**
     * private string to set city name of query
     */
    private String city = "Resistencia";

    /**
     * private string method for generating table format for output display
     */
    private String tableFormat1 = "| %-54s |%n";
    private String tableFormat2 = "| %-30s | %-20s | %-20s | %-21s |%n";
    private String tableFormat3 = "| %-30s | %-20s |%n";

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
     * additional population reports
     */
    /**
     * the main public method used to generate different reports of  population
     */
    public void generateAdditionalPopulationReport() {
        String whereClause1 = "WHERE cnty.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE cnty.Region = '" + region + "' ";
        String whereClause3 = "WHERE cnty.Name = '" + country + "' ";
        String whereClause4 = "WHERE city.District = '" + district + "' ";
        String whereClause5 = "WHERE city.Name = '" + city + "' ";

        displayWorldPopulation();
        displayCitiesAndNonCitiesPopulation("Continent", continent, whereClause1);
        displayCitiesAndNonCitiesPopulation("Region", region, whereClause2);
        displayCitiesAndNonCitiesPopulation("Country", country, whereClause3);
        displayPopulation("District", district, whereClause4);
        displayPopulation("City", city, whereClause5);
    }

    /**
     * protected method to extract world population data from SQL database using query
     *
     * @return the arraylist of extracted world population data
     */
    protected ArrayList<Population> extractWorldPopulation() {
        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT SUM(country.Population) AS TotalPopulation FROM country";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population p = new Population();
                p.setTotalPopulation(rset.getLong("TotalPopulation"));
                population.add(p);
            }

            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * protected method to reformat population and
     * display the extracted world population data in a tabular format
     */
    protected void displayWorldPopulation() {
        ArrayList<Population> extractedWorldPopulation = extractWorldPopulation();

        System.out.println();
        System.out.printf("----------------------------------------------------------%n");

        String title = "Total Number of People Living in the world";

        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-54s |%n", title);
        System.out.printf("----------------------------------------------------------%n");

        System.out.printf(tableFormat1, "Total Population");
        System.out.printf("----------------------------------------------------------%n");

        if (extractedWorldPopulation != null) {
            for (Population pop : extractedWorldPopulation) {
                System.out.printf(tableFormat1,
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()));
            }
        } else {
            System.out.printf("| %-54s |%n", "No records");
        }
        System.out.printf("----------------------------------------------------------%n");
        System.out.println();
    }

    /**
     * protected method to extract population data from SQL database using query
     *
     * @return the arraylist of extracted population data
     */
    protected ArrayList<Population> extractCitiesAndNonCitiesPopulation(String type, String whereClause) {
        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT cnty." + type + ", SUM(cnty.Population) AS TotalPopulation, cty.TotalCityPopulation, (SUM(cnty.Population) - cty.TotalCityPopulation) AS TotalNonCityPopulation " +
                    "FROM country AS cnty " +
                    "INNER JOIN (" +
                    "SELECT country." + type + ", SUM(city.Population) AS TotalCityPopulation " +
                    "FROM city " +
                    "INNER JOIN country " +
                    "ON CountryCode = country.Code " +
                    "GROUP BY country." + type + " "+
                    ") AS cty " +
                    "ON cty." + type + " = cnty." + type +" " +
                    whereClause +
                    "GROUP BY cnty." + type + ";";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population p = new Population();
                p.setName(rset.getString("cnty." + type));
                p.setTotalPopulation(rset.getLong("TotalPopulation"));
                p.setPopulationInCities(rset.getLong("TotalCityPopulation"));
                p.setPopulationNotInCities(rset.getLong("TotalNonCityPopulation"));
                population.add(p);
            }

            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * protected method to reformat population and
     * display the extracted population data in a tabular format
     */
    protected void displayCitiesAndNonCitiesPopulation(String type, String name, String whereClause) {
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation;
        if (type == "Country") {
            extractedCitiesAndNonCitiesPopulation = extractCitiesAndNonCitiesPopulation("Name", whereClause);
        }else {
            extractedCitiesAndNonCitiesPopulation = extractCitiesAndNonCitiesPopulation(type, whereClause);
        }
        System.out.println();
        System.out.printf("--------------------------------------------------------------------------------------------------------%n");

        String title = "Number of People Living in the Cities and Not in the Cities in a " + type;
        // if the type is not world, add some more text in title
        if (type != "World") {
            title +=  " (" + name + ")";
        }
        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-100s |%n", title);
        System.out.printf("--------------------------------------------------------------------------------------------------------%n");

        System.out.printf(tableFormat2, type + " Name", "Total Population", "Total Population in", "Total Population not");
        System.out.printf(tableFormat2, "", "", "Cities", "in Cities");
        System.out.printf("--------------------------------------------------------------------------------------------------------%n");

        if (extractedCitiesAndNonCitiesPopulation != null) {
            for (Population pop : extractedCitiesAndNonCitiesPopulation) {
                System.out.printf(tableFormat2,
                        pop.getName(),
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationInCities()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationNotInCities()));
            }
        } else {
            System.out.printf("| %-100s |%n", "No records");
        }
        System.out.printf("--------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }
    
    /**
     * protected method to extract population data from SQL database using query
     *
     * @return the arraylist of extracted population data
     */
    protected ArrayList<Population> extractPopulation(String type, String whereClause) {
        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT city." + type + ", SUM(city.Population) AS TotalPopulation " +
                    "FROM city " +
                    whereClause +
                    "GROUP BY city." + type + ";";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population p = new Population();
                p.setName(rset.getString("city." + type));
                p.setTotalPopulation(rset.getLong("TotalPopulation"));
                population.add(p);
            }

            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * protected method to reformat population and
     * display the extracted population data in a tabular format
     */
    protected void displayPopulation(String type, String name, String whereClause) {
        ArrayList<Population> extractedPopulation;
        if (type == "City") {
            extractedPopulation = extractPopulation("Name", whereClause);
        }else {
            extractedPopulation = extractPopulation(type, whereClause);
        }
        System.out.println();
        System.out.printf("---------------------------------------------------------%n");

        String title = "Total Number of People Living in a " + type;

        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-53s |%n", title);
        System.out.printf("---------------------------------------------------------%n");

        System.out.printf(tableFormat3, type + " Name", "Total Population");
        System.out.printf("---------------------------------------------------------%n");

        if (extractedPopulation != null) {
            for (Population pop : extractedPopulation) {
                System.out.printf(tableFormat3,
                        pop.getName(),
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()));
            }
        } else {
            System.out.printf("| %-54s |%n", "No records");
        }
        System.out.printf("---------------------------------------------------------%n");
        System.out.println();
    }
}
