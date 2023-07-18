package com.napier.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTestApp {

    static CountryReport cotyr;

    @BeforeAll
    static void init(){
        cotyr = new CountryReport();
    }
    @Test
    void displayCountryNullTest(){
        cotyr.displayCountries(null, "World", "", false);
    }

    @Test
    void displayCountryEmptyTest(){
        ArrayList<Country> extractedCountries = new ArrayList<>();
        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    @Test
    void displayCountryContainsNullTest(){
        ArrayList<Country> extractedCountries = new ArrayList<>();
        extractedCountries.add(null);
        extractedCountries.add(null);

        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    @Test
    void displayCountryInWorldTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    @Test
    void displayCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", false);
    }

    @Test
    void displayCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", false);
    }

    @Test
    void displayTopCountryInWorldTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", true);
    }

    @Test
    void displayTopCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", true);
    }

    @Test
    void displayTopCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();
        Country country = new Country();
        country.setCountryCode("ABW");
        country.setCountryName("Aruba");
        country.setContinent("North America");
        country.setRegion("Caribbean");
        country.setPopulation(103000);
        country.setCapital("Oranjestad");

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", true);
    }


}

