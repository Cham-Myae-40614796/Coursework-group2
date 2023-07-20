package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LanguageReport {

    private Connection conn = null;

    private int query_count = 32;

    private String tableFormat = "| %-35s | %-37s | %-37s |%n";


    public void setConn(Connection conn) {
        this.conn = conn;
    }


    public void generateLanguageReport() {
        displayLanguage();

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
                lan.setCountryLanguage(resultData.getString("cl.Language"));
                lan.setPercentage(resultData.getString("TotalNumberOfPeople"));
                lan.setCountryPopulation(resultData.getString("PercentageOftheWorldPopulation"));
//                lan.setCountryName(resultData.getString("c.Name"));

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

    protected void displayLanguage()
    {
        ArrayList<Language> extractedLanguage = extractLanguage();
        // create new arraylist to store the arraylist of extracted cities data
        // ArrayList<Language> extractedLanguage = extractLanguage(whereClause);

        // skip a line and make a table
        System.out.println();
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");

        // define title of table
        String title = "World percentage of people who speak Chinese, English, Hindi, Spanish, Arabic";

        // add numbering to the title
        title = query_count + ". " + title;

        // increase the count by one
        query_count += 1;
        // print out the title
        System.out.printf("| %-118s |%n", title);
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");
        // print out table headings
        System.out.printf(tableFormat, "Language", "Percentage", "Population");
        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");



//         print out table records
//        if (extractedLanguage != null) {
//            for (int i = 0; i < extractedLanguage.size(); i++) {
//                System.out.printf(tableFormat,
//                        extractedLanguage.get(i).getCountryLanguage(),
//                        extractedLanguage.get(i).getPercentage(),
//                        extractedLanguage.get(i).getCountryPopulation(),
//                        extractedLanguage.get(i).getCountryName());
//            }
//        } else {
//            // handles null records
//            System.out.printf("| %-118s |%n", "No records");
//        }



        if (extractedLanguage != null) {
            while(extractedLanguage.remove(null)){

            }
        }

        if (extractedLanguage == null || extractedLanguage.size() == 0) {
            // handles null records
            System.out.printf("| %-118s |%n", "No records");
        } else {
            // print out table records
            for (Language elan : extractedLanguage) {

                if (elan == null) {
                    continue;
                }

                System.out.printf(tableFormat,
                        elan.getCountryLanguage(),
                        elan.getPercentage(),
                        elan.getCountryPopulation(),
                        elan.getCountryName());
            }
        }




        System.out.printf("--------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println();
    }

}
