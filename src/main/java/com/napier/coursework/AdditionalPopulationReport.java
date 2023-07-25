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
    private String tableFormat2 = "| %-20s | %-20s | %-20s | %-10s | %-21s | %-10s |%n";
    private String tableFormat3 = "| %-40s | %-20s |%n";

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
    public void generateAdditionalPopulationReport() {

        // define WHERE part of query
        String whereClause1 = "WHERE cnty.Continent = '" + continent + "' ";
        String whereClause2 = "WHERE cnty.Region = '" + region + "' ";
        String whereClause3 = "WHERE cnty.Name = '" + country + "' ";
        String whereClause4 = "WHERE city.District = '" + district + "' ";
        String whereClause5 = "WHERE city.Name = '" + city + "' ";

        // create new arraylist to store the arraylist of extracted world population data
        ArrayList<Population> extractedWorldPopulation = extractWorldPopulation();
        // display total world population
        displayWorldPopulation(extractedWorldPopulation);

        // create new arraylist to store the arraylist of extracted cities and non cities population data
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = extractCitiesAndNonCitiesPopulation("Continent", whereClause1);
        // display cities and non cities population in a continent
        displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Continent", continent);

        // store the arraylist of extracted cities and non cities population data
        extractedCitiesAndNonCitiesPopulation = extractCitiesAndNonCitiesPopulation("Region", whereClause2);
        // display cities and non cities population in a region
        displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Region", region);

        // store the arraylist of extracted cities and non cities population data
        extractedCitiesAndNonCitiesPopulation = extractCitiesAndNonCitiesPopulation("Name", whereClause3);
        // display cities and non cities population in a country
        displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Country", country);

        // create new arraylist to store the arraylist of extracted population data
        ArrayList<Population> extractedPopulation = extractPopulation("District", whereClause4);
        // display population in a district
        displayPopulation(extractedPopulation, "District", district);

        // store the arraylist of extracted population data
        extractedPopulation = extractPopulation ("Name", whereClause5);
        // display population in a city
        displayPopulation(extractedPopulation, "City", city);
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
    protected void displayWorldPopulation(ArrayList<Population> extractedWorldPopulation) {

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
            while (extractedWorldPopulation.remove(null)){

            }
        }
        if (extractedWorldPopulation == null || extractedWorldPopulation.isEmpty()){
            //handles null records
            System.out.printf("| %-54s |%n", "No records");
        }
        else {
            for (Population pop : extractedWorldPopulation) {
                System.out.printf(tableFormat1,
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()));
            }
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

            String query = "SELECT cnty." + type + ", SUM(cnty.Population) AS TotalPopulation, cty.TotalCityPopulation, " +
                    "CONCAT(((cty.TotalCityPopulation/SUM(cnty.Population)) * 100), ' %') AS CityPopulationPercentage, " +
                    "(SUM(cnty.Population) - cty.TotalCityPopulation) AS TotalNonCityPopulation, " +
                    "CONCAT((100 - (cty.TotalCityPopulation/SUM(cnty.Population)) * 100), ' %') AS NonCityPopulationPercentage " +
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
                p.setCityPopulationPercentage(rset.getString("CityPopulationPercentage"));
                p.setPopulationNotInCities(rset.getLong("TotalNonCityPopulation"));
                p.setNonCityPopulationPercentage(rset.getString("NonCityPopulationPercentage"));
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
    protected void displayCitiesAndNonCitiesPopulation(ArrayList<Population> extractedCitiesAndNonCitiesPopulation, String type, String name) {

        System.out.println();
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");

        String title = "Number of People Living in the Cities and Not in the Cities in a " + type;
        // if the type is not world, add some more text in title
        if (type != "World") {
            title +=  " (" + name + ")";
        }
        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-116s |%n", title);
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");

        System.out.printf(tableFormat2, type + " Name", "Total Population", "Total Population in", "Percentage", "Total Population not", "Percentage");
        System.out.printf(tableFormat2, "", "", "Cities", "", "in Cities", "");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");

        if (extractedCitiesAndNonCitiesPopulation != null) {
            while(extractedCitiesAndNonCitiesPopulation.remove(null)){

            }
        }

        if (extractedCitiesAndNonCitiesPopulation == null || extractedCitiesAndNonCitiesPopulation.isEmpty()) {
            // handles null records
            System.out.printf("| %-116s |%n", "No records");
        } else {
            // print out table records
            for (Population pop : extractedCitiesAndNonCitiesPopulation){
                String eNameText = pop.getName();
                String extraENameText = "";
                if (eNameText.length() > 20){
                    extraENameText = eNameText.substring(20);
                    eNameText = eNameText.substring(0, 20);
                }
                System.out.printf(tableFormat2,
                        eNameText,
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationInCities()),
                        pop.getCityPopulationPercentage(),
                        NumberFormat.getInstance(Locale.US).format(pop.getPopulationNotInCities()),
                        pop.getNonCityPopulationPercentage());
                if (extraENameText != ""){
                    System.out.printf(tableFormat2,
                            extraENameText,
                            "",
                            "",
                            "",
                            "",
                            "");
                }
            }
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

    /**
     * protected method to extract population data from SQL database using query
     *
     * @return the arraylist of extracted population data
     */
    protected ArrayList<Population> extractPopulation(String type, String whereClause)
    {
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
    protected void displayPopulation(ArrayList<Population> extractedPopulation, String type, String name) {

        System.out.println();
        System.out.printf("-------------------------------------------------------------------%n");

        String title = "Total Number of People Living in a " + type + " (" + name + ")";

        title = query_count + ". " + title;

        query_count += 1;

        System.out.printf("| %-63s |%n", title);
        System.out.printf("-------------------------------------------------------------------%n");

        System.out.printf(tableFormat3, type + " Name", "Total Population");
        System.out.printf("-------------------------------------------------------------------%n");

        if (extractedPopulation != null) {
            while (extractedPopulation.remove(null)) {

            }
        }
        if (extractedPopulation == null || extractedPopulation.isEmpty()) {
            //handle null records
            System.out.printf("| %-63s |%n", "No records");
        }
        else {
            for (Population pop : extractedPopulation) {
                System.out.printf(tableFormat3,
                        pop.getName(),
                        NumberFormat.getInstance(Locale.US).format(pop.getTotalPopulation()));
            }
        }
        System.out.printf("-------------------------------------------------------------------%n");
        System.out.println();
    }
}
