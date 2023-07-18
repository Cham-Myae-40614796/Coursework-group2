package com.napier.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTestApp {

    static DatabaseConnection dbConn;
    static CountryReport cotyr;

    static CityReport cr;

    static CapitalCityReport ccr;
    @BeforeAll
    static void init(){
        dbConn = new DatabaseConnection();
        dbConn.connect();
        cotyr = new CountryReport();
        cotyr.setConn(dbConn.getConn());
        cr = new CityReport();
        cr.setConn(dbConn.getConn());
        ccr = new CapitalCityReport();
        ccr.setConn(dbConn.getConn());
    }

    @Test
    void extractCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }

    @Test
    void extractCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }
    @Test
    void extractCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }

    @Test
    void extractTopCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }

    @Test
    void extractTopCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }
    @Test
    void extractTopCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(0).getCountryName(), "China");
        assertEquals(extractedCountries.get(0).getCountryCode(), "CHN");
    }




    @Test
    void extractCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(0).getCityName(), "Mumbai (Bombay)");
        assertEquals(extractedCities.get(0).getCountryName(), "India");
    }




    @Test
    void extractCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }

    @Test
    void extractCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }

    @Test
    void extractCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }

    @Test
    void extractTopCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(0).getCityName(), "");
        assertEquals(extractedCapitalCites.get(0).getCountryName(), "India");
    }




}
