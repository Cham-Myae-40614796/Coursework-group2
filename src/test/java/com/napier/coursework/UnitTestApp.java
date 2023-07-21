package com.napier.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTestApp {

    static CountryReport cotyr;

    static CityReport cr;

    static CapitalCityReport ccr;

    static PopulationReport popr;

    static Country country;

    static City city;

    static Population population;


    static LanguageReport lgr;

    static Language language;

    @BeforeAll
    static void init(){
        cotyr = new CountryReport();
        cr = new CityReport();
        ccr = new CapitalCityReport();
        popr = new PopulationReport();
        lgr = new LanguageReport();

        country = new Country();
        country.setCountryCode("IND");
        country.setCountryName("India");
        country.setContinent("Asia");
        country.setRegion("Southern and Central Asia");
        country.setPopulation(3287263);
        country.setCapital("New Delhi");

        city = new City();
        city.setCityName("Seoul");
        city.setCountryName("South Korea");
        city.setDistrict("Seoul");
        city.setPopulation(9981619);

        language = new Language();
        language.setCountryLanguage("Chinese");
        language.setPopulation(1191843539);
        language.setPercentage("19.61");

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

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    @Test
    void displayCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", false);
    }

    @Test
    void displayCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", false);
    }

    @Test
    void displayTopCountryInWorldTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", true);
    }

    @Test
    void displayTopCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", true);
    }

    @Test
    void displayTopCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", true);
    }






    @Test
    void displayCityNullTest(){

        cr.displayCities(null, "World", "", false);
    }

    @Test
    void displayCityEmptyTest(){
        ArrayList<City> extractedCities = new ArrayList<>();
        cr.displayCities(extractedCities, "World", "", false);
    }

    @Test
    void displayCityContainsNullTest(){
        ArrayList<City> extractedCities = new ArrayList<>();
        extractedCities.add(null);
        extractedCities.add(null);

        cr.displayCities(extractedCities, "World", "", false);
    }

    @Test
    void displayCityInWorldTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "World", "", false);
    }

    @Test
    void displayCityInContinentTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Continent", "Europe", false);
    }

    @Test
    void displayCityInRegionTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Region", "Eastern Africa", false);
    }

    @Test
    void displayCityInCountryTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Country", "Eastern Africa", false);
    }

    @Test
    void displayCityInDistrictTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "District", "Eastern Africa", false);
    }

    @Test
    void displayTopCityInWorldTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "World", "", true);
    }

    @Test
    void displayTopCityInContinentTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Continent", "Europe", true);
    }

    @Test
    void displayTopCityInRegionTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Region", "Eastern Africa", true);
    }

    @Test
    void displayTopCityInCountryTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Country", "Eastern Africa", true);
    }

    @Test
    void displayTopCityInDistrictTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "District", "Eastern Africa", true);
    }








    @Test
    void displayCapitalCityNullTest(){

        ccr.displayCapitalCities(null, "World", "", false);
    }

    @Test
    void displayCapitalCityEmptyTest(){
        ArrayList<City> extractedCapitalCities = new ArrayList<>();
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    @Test
    void displayCapitalCityContainsNullTest(){
        ArrayList<City> extractedCapitalCities = new ArrayList<>();
        extractedCapitalCities.add(null);
        extractedCapitalCities.add(null);

        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    @Test
    void displayCapitalCityInWorldTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    @Test
    void displayCapitalCityInContinentTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Continent", "Europe", false);
    }

    @Test
    void displayCapitalCityInRegionTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Region", "Eastern Africa", false);
    }

    @Test
    void displayTopCapitalCityInWorldTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", true);
    }

    @Test
    void displayTopCapitalCityInContinentTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Continent", "Europe", true);
    }

    @Test
    void displayTopCapitalCityInRegionTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Region", "Eastern Africa", true);
    }













    @Test
    void displayPopulationNullTest(){

        popr.displayPopulation(null, "Continent");
    }

    @Test
    void displayPopulationEmptyTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        popr.displayPopulation(extractedPopulation, "Continent");
    }

    @Test
    void displayPopulationContainsNullTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        extractedPopulation.add(null);
        extractedPopulation.add(null);

        popr.displayPopulation(extractedPopulation, "Continent");
    }

    @Test
    void displayPopulationInContinentTest() {
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Asia");
        population.setTotalPopulation(3705025700L);
        population.setPopulationInCities(697604103L);
        population.setCityPopulationPercentage("18.8286 %");
        population.setPopulationNotInCities(3007421597L);
        population.setNonCityPopulationPercentage("81.1714 %");

        extractedPopulation.add(population);
        popr.displayPopulation(extractedPopulation, "Continent");
    }

    @Test
    void displayPopulationInRegionTest() {
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Eastern Europe");
        population.setTotalPopulation(307026000L);
        population.setPopulationInCities(123384516L);
        population.setCityPopulationPercentage("40.1870 %");
        population.setPopulationNotInCities(183641484L);
        population.setNonCityPopulationPercentage("59.8130 %");

        extractedPopulation.add(population);
        popr.displayPopulation(extractedPopulation, "Region");
    }

    @Test
    void displayPopulationInCountryTest() {
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Denmark");
        population.setTotalPopulation(5330000L);
        population.setPopulationInCities(1215945L);
        population.setCityPopulationPercentage("22.8132 %");
        population.setPopulationNotInCities(4114055L);
        population.setNonCityPopulationPercentage("77.1868 %");

        extractedPopulation.add(population);
        popr.displayPopulation(extractedPopulation, "Country");
    }
  
  
  
  
  
  
  
  
    @Test
    void displayLanguageNullTest(){

        lgr.displayLanguage(null);
    }

    @Test
    void displayLanguageEmptyTest(){
        ArrayList<Language> extractedLanguage = new ArrayList<>();
        lgr.displayLanguage(extractedLanguage);
    }

    @Test
    void displayLanguageContainsNullTest(){
        ArrayList<Language> extractedLanguage = new ArrayList<>();
        extractedLanguage.add(null);
        extractedLanguage.add(null);

        lgr.displayLanguage(extractedLanguage);
    }

    @Test
    void displayLanguageTest() {
        ArrayList<Language> extractedLanguage = new ArrayList<>();

        extractedLanguage.add(language);
        lgr.displayLanguage(extractedLanguage);
    }

}

