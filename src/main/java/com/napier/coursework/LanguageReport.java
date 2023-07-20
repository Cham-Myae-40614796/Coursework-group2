package com.napier.coursework;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LanguageReport {

    private Connection conn = null;

    private int query_count = 32;

    private String tableFormat = "| %-30s | %-30s | %-30s |%n";


    public void setConn(Connection conn) {
        this.conn = conn;
    }


    public void generateLanguageReport() {
        ArrayList<Language> extractedLanguage = extractLanguage();
        displayLanguage(extractedLanguage);

    }

    protected ArrayList<Language> extractLanguage()
    {
        try
        {
            // define query
            String query = "SELECT " +
                    "    cl.language, " +
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

            // Extract necessary cities information
            ArrayList<Language> languages = new ArrayList<Language>();

            // loop until all the extracted data is added to language array list
            while (resultData.next())
            {
                // create new object to add to language array list
                Language lan = new Language();
                // add the extracted data to language object
                lan.setLanguage(resultData.getString("cl.Language"));
                lan.setPopulation(resultData.getLong("TotalNumberOfPeople"));
                lan.setPercentage(resultData.getString("PercentageOftheWorldPopulation"));

                // add the language object to language array list
                languages.add(lan);
            }
            return languages;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }

    protected void displayLanguage(ArrayList<Language> extractedLanguage)
    {
        // create new arraylist to store the arraylist of extracted cities data
        // ArrayList<Language> extractedLanguage = extractLanguage(whereClause);

        // skip a line and make a table
        System.out.println();
        System.out.printf("----------------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "World percentage of people who speak Chinese, English, Hindi, Spanish, Arabic";

        // add numbering to the title
        title = query_count + ". " + title;

        // increase the count by one
        query_count += 1;
        // print out the title
        System.out.printf("| %-96s |%n", title);
        System.out.printf("----------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "Language", "Population", "Percentage");
        System.out.printf("----------------------------------------------------------------------------------------------------%n");

        if (extractedLanguage != null) {
            while(extractedLanguage.remove(null)){

            }
        }

        if (extractedLanguage == null || extractedLanguage.size() == 0) {
            // handles null records
            System.out.printf("| %-107s |%n", "No records");
        } else {
            // print out table records
            for (Language elan : extractedLanguage) {

                if (elan == null) {
                    continue;
                }

                System.out.printf(tableFormat,
                        elan.getLanguage(),
                        NumberFormat.getInstance(Locale.US).format(elan.getPopulation()),
                        elan.getPercentage());
            }
        }
        System.out.printf("----------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
