package com.napier.coursework;
/**
 * Creating report for language related issues
 * Get the language data and population data to display them in report
 * @author Cham Myae Pyae Sone
 * @version 0.1.0.3
 * @since 0.1.0.3
 */

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LanguageReport {

    /**
     * private Connection to SQL database
     */
    private Connection conn;

    /**
     * Number of query for table titles
     */
    private int queryCount = 32;

    /**
     * private string method for generating table format for output display
     */
    private String tableFormat = "| %-30s | %-30s | %-30s |%n";

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
     * the main public method used to generate language report
     */
    public void generateLanguageReport() {
        ArrayList<Language> extractedLanguage = new ArrayList<>();
        displayLanguage(extractedLanguage);

    }

    /**
     * protected method to extract language data from SQL database using query
     *
     * @return the arraylist of extracted language data
     */
    protected ArrayList<Language> extractLanguage()
    {
        // Extract necessary language information
        ArrayList<Language> languages = new ArrayList<Language>();
        try
        {
            // define query
            String query = "SELECT " +
                    "    cl.language As Language, " +
                    "    ROUND(SUM(cl.percentage * c.population) / 100, 2) AS TotalNumberOfPeople, " +
                    "    CONCAT(ROUND(SUM(c.population * cl.percentage) / (SELECT SUM(population) FROM country), 2), ' %') AS PercentageOftheWorldPopulation " +
                    "FROM countrylanguage cl, country c " +
                    "WHERE cl.countrycode = c.code AND cl.language IN ('English', 'Arabic', 'Hindi', 'Chinese', 'Spanish') " +
                    "GROUP BY cl.language " +
                    "ORDER BY TotalNumberOfPeople DESC;";


            // Create an SQL statement
            Statement stmt = conn.createStatement();

            // Execute SQL statement
            ResultSet resultData = stmt.executeQuery(query);

            // loop until all the extracted data is added to language array list
            while (resultData.next())
            {
                // create new object to add to language array list
                Language lan = new Language();
                // add the extracted data to language object
                lan.setCountryLanguage(resultData.getString("Language"));
                lan.setPopulation(resultData.getLong("TotalNumberOfPeople"));
                lan.setPercentage(resultData.getString("PercentageOftheWorldPopulation"));

                // add the language object to language array list
                languages.add(lan);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
        }
        return languages;

    }

    /**
     * protected method to reformat population and
     * display the extracted language population data in a tabular format
     */
    protected void displayLanguage(ArrayList<Language> extractedLanguage)
    {

        // skip a line and make a table
        System.out.println();
        System.out.printf("----------------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "World percentage of people who speak Chinese, English, Hindi, Spanish, Arabic";

        // add numbering to the title
        title = queryCount + ". " + title;

        // increase the count by one
        queryCount += 1;
        // print out the title
        System.out.printf("| %-96s |%n", title);
        System.out.printf("----------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "Language", "Population", "Percentage");
        System.out.printf("----------------------------------------------------------------------------------------------------%n");

        if (extractedLanguage != null) {
            boolean nullCheck = true;
            while(nullCheck) {
                nullCheck = extractedLanguage.remove(null);
            }
        }

        if (extractedLanguage == null || extractedLanguage.isEmpty()) {
            // handles null records
            System.out.printf("| %-96s |%n", "No records");
        } else {
            // print out table records
            for (Language elan : extractedLanguage) {

                System.out.printf(tableFormat,
                        elan.getCountryLanguage(),
                        NumberFormat.getInstance(Locale.US).format(elan.getPopulation()),
                        elan.getPercentage());
            }
        }

        System.out.printf("----------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
