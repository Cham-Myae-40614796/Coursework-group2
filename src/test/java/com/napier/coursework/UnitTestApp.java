package com.napier.coursework;
/**
 * for Unit Test where null tests, empty tests, contains null tests are included
 * mainly test display methods of queries from reports
 * @author Thar Htet Nyan, Kyi Phyu khin, Htet Myat Thiri, Cham Myae Pyae Sone
 * @version 0.1.0.3
 * @since 0.1.0.3
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * declaring variable for respective necessary report files
 */
public class UnitTestApp {

    static CountryReport cotyr;

    static CityReport cr;

    static CapitalCityReport ccr;

    static PopulationReport popr;

    static AdditionalPopulationReport apr;

    static LanguageReport lgr;

    static Country country;

    static City city;

    static Population population;

    static Language language;

    /**
     * sample data to test
     */
    @BeforeAll
    static void init(){

        /**
         * creating objects for reports
         */
        cotyr = new CountryReport();
        cr = new CityReport();
        ccr = new CapitalCityReport();
        popr = new PopulationReport();
        apr = new AdditionalPopulationReport();
        lgr = new LanguageReport();

        /**
         * creating new country object, setting fixed instances
         */
        country = new Country();
        country.setCountryCode("IND");
        country.setCountryName("India");
        country.setContinent("Asia");
        country.setRegion("Southern and Central Asia");
        country.setPopulation(3287263);
        country.setCapital("New Delhi");

        /**
         * creating new city object, setting fixed instances
         */
        city = new City();
        city.setCityName("Seoul");
        city.setCountryName("South Korea");
        city.setDistrict("Seoul");
        city.setPopulation(9981619);

        /**
         * creating new language object, setting fixed instances
         */
        language = new Language();
        language.setCountryLanguage("Chinese");
        language.setPopulation(1191843539);
        language.setPercentage("19.61");

    }

    /**
     * Unit test for country report related queries and display methods
     * @author Thar Htet Nyan
     */

    /**
     * null test for country display
     */
    @Test
    void displayCountryNullTest(){

        cotyr.displayCountries(null, "World", "", false);
    }

    /**
     * empty test for country display
     */
    @Test
    void displayCountryEmptyTest(){
        ArrayList<Country> extractedCountries = new ArrayList<>();
        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    /**
     * adding null value to test for country display
     */
    @Test
    void displayCountryContainsNullTest(){
        ArrayList<Country> extractedCountries = new ArrayList<>();
        extractedCountries.add(null);
        extractedCountries.add(null);

        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    /**
     * test for country in the world display
     */
    @Test
    void displayCountryInWorldTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", false);
    }

    /**
     * test for country in the continent display
     */
    @Test
    void displayCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", false);
    }

    /**
     * test for country in the region display
     */
    @Test
    void displayCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", false);
    }

    /**
     * test for top country in the world display
     */
    @Test
    void displayTopCountryInWorldTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "World", "", true);
    }

    /**
     * test for top country in the continent display
     */
    @Test
    void displayTopCountryInContinentTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Continent", "Europe", true);
    }

    /**
     * test for top country in the region display
     */
    @Test
    void displayTopCountryInRegionTest() {
        ArrayList<Country> extractedCountries = new ArrayList<>();

        extractedCountries.add(country);
        cotyr.displayCountries(extractedCountries, "Region", "Eastern Africa", true);
    }




    /**
     * Unit test for city report related queries and display methods
     * @author Thar Htet Nyan
     */

    /**
     * null test for city display
     */
    @Test
    void displayCityNullTest(){

        cr.displayCities(null, "World", "", false);
    }

    /**
     * empty test for city display
     */
    @Test
    void displayCityEmptyTest(){
        ArrayList<City> extractedCities = new ArrayList<>();
        cr.displayCities(extractedCities, "World", "", false);
    }

    /**
     * adding null value to test for city display
     */
    @Test
    void displayCityContainsNullTest(){
        ArrayList<City> extractedCities = new ArrayList<>();
        extractedCities.add(null);
        extractedCities.add(null);

        cr.displayCities(extractedCities, "World", "", false);
    }

    /**
     * test for city in the world display
     */
    @Test
    void displayCityInWorldTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "World", "", false);
    }

    /**
     * test for city in the continent display
     */
    @Test
    void displayCityInContinentTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Continent", "Europe", false);
    }

    /**
     * test for city in the region display
     */
    @Test
    void displayCityInRegionTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Region", "Eastern Africa", false);
    }

    /**
     * test for city in the country display
     */
    @Test
    void displayCityInCountryTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Country", "Eastern Africa", false);
    }

    /**
     * test for city in the district display
     */
    @Test
    void displayCityInDistrictTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "District", "Eastern Africa", false);
    }

    /**
     * test for top city in the world display
     */
    @Test
    void displayTopCityInWorldTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "World", "", true);
    }

    /**
     * test for top city in the continent display
     */
    @Test
    void displayTopCityInContinentTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Continent", "Europe", true);
    }

    /**
     * test for top city in the region display
     */
    @Test
    void displayTopCityInRegionTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Region", "Eastern Africa", true);
    }

    /**
     * test for top city in the country display
     */
    @Test
    void displayTopCityInCountryTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "Country", "Eastern Africa", true);
    }

    /**
     * test for top city in the district display
     */
    @Test
    void displayTopCityInDistrictTest() {
        ArrayList<City> extractedCities = new ArrayList<>();

        extractedCities.add(city);
        cr.displayCities(extractedCities, "District", "Eastern Africa", true);
    }



    /**
     * Unit tests for capital city report related queries and display methods
     * @author Thar Htet Nyan
     */

    /**
     * null test for capital city display
     */
    @Test
    void displayCapitalCityNullTest(){

        ccr.displayCapitalCities(null, "World", "", false);
    }

    /**
     * empty test for capital city display
     */
    @Test
    void displayCapitalCityEmptyTest(){
        ArrayList<City> extractedCapitalCities = new ArrayList<>();
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    /**
     * adding null value to test for capital city display
     */
    @Test
    void displayCapitalCityContainsNullTest(){
        ArrayList<City> extractedCapitalCities = new ArrayList<>();
        extractedCapitalCities.add(null);
        extractedCapitalCities.add(null);

        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    /**
     * test for capital city in the world display
     */
    @Test
    void displayCapitalCityInWorldTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", false);
    }

    /**
     * test for capital city in the continent display
     */
    @Test
    void displayCapitalCityInContinentTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Continent", "Europe", false);
    }

    /**
     * test for capital city in the region display
     */
    @Test
    void displayCapitalCityInRegionTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Region", "Eastern Africa", false);
    }

    /**
     * test for top capital city in the world display
     */
    @Test
    void displayTopCapitalCityInWorldTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "World", "", true);
    }

    /**
     * test for top capital city in the continent display
     */
    @Test
    void displayTopCapitalCityInContinentTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Continent", "Europe", true);
    }

    /**
     * test for top capital city in the region display
     */
    @Test
    void displayTopCapitalCityInRegionTest() {
        ArrayList<City> extractedCapitalCities = new ArrayList<>();

        extractedCapitalCities.add(city);
        ccr.displayCapitalCities(extractedCapitalCities, "Region", "Eastern Africa", true);
    }






    /**
     * Unit test for population report related queries and display methods
     * @author Kyi Phyu Khin
     */

    /**
     * null test for population display
     */
    @Test
    void displayPopulationNullTest(){

        popr.displayPopulation(null, "Continent");
    }

    /**
     * empty test for population display
     */
    @Test
    void displayPopulationEmptyTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        popr.displayPopulation(extractedPopulation, "Continent");
    }

    /**
     * adding null value to test for population display
     */
    @Test
    void displayPopulationContainsNullTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        extractedPopulation.add(null);
        extractedPopulation.add(null);

        popr.displayPopulation(extractedPopulation, "Continent");
    }

    /**
     * test for population in the continent display
     */
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

    /**
     * test for population in the region display
     */
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

    /**
     * test for population in the country display
     */
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





    /**
     * Unit test for additional population report related queries and display methods
     * @author Htet Myat Thiri
     */

    /**
     * empty test for total world population display
     */
    @Test
    void displayTotalWorldPopulationEmptyTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        apr.displayWorldPopulation(extractedWorldPopulation);
    }

    /**
     * empty test for city population and population not in the city display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationEmptyTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "for Total", "Queries");
    }

    /**
     * empty test for population display
     */
    @Test
    void displayAdditionalPopulationEmptyTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        apr.displayPopulation(extractedPopulation, "for Total", "Queries" );
    }

    /**
     * null test for total world population display
     */
    @Test
    void displayTotalWorldPopulationNullTest(){
        apr.displayWorldPopulation(null);
    }

    /**
     * null test for city population and population not in the city display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationNullTest(){
        apr.displayCitiesAndNonCitiesPopulation(null, "Continent", "Europe");
    }

    /**
     * null test for population display
     */
    @Test
    void displayAdditionalPopulationNullTest(){
        apr.displayPopulation(null, "District", "Gederland");
    }

    /**
     * adding null value to test for total world population display
     */
    @Test
    void displayTotalWorldPopulationContainsNullTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        extractedWorldPopulation.add(null);

        apr.displayWorldPopulation(extractedWorldPopulation);
    }

    /**
     * adding null value to test for city population and population not in the city display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationContainsNullTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        extractedCitiesAndNonCitiesPopulation.add(null);
        extractedCitiesAndNonCitiesPopulation.add(null);

        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Continent", "Europe");
    }

    /**
     * adding null value to test for population display
     */
    @Test
    void displayAdditionalPopulationContainsNullTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        extractedPopulation.add(null);
        extractedPopulation.add(null);

        apr.displayPopulation(extractedPopulation, "District", "Gederland");
    }

    /**
     * test for total world population display
     */
    @Test
    void displayTotalWorldPopulationTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        population = new Population();
        population.setTotalPopulation(6078749450L);
        extractedWorldPopulation.add(population);
        apr.displayWorldPopulation(extractedWorldPopulation);
    }

    /**
     * test for city population and population not in the city for a continent display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationInContinentTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Europe");
        population.setTotalPopulation(730074600L);
        population.setPopulationInCities(241942813L);
        population.setCityPopulationPercentage("33.1395 %");
        population.setPopulationNotInCities(488131787L);
        population.setNonCityPopulationPercentage("66.8605 %");

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayPopulation(extractedCitiesAndNonCitiesPopulation, "Continent", "Europe");
    }

    /**
     * test for city population and population not in the city for a region display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationInRegionTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Southern Europe");
        population.setTotalPopulation(144674200L);
        population.setPopulationInCities(40016658L);
        population.setCityPopulationPercentage("27.6598 %");
        population.setPopulationNotInCities(104657542l);
        population.setNonCityPopulationPercentage("72.3402 %");

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayPopulation(extractedCitiesAndNonCitiesPopulation, "Region", "Southern Europe");
    }

    /**
     * test for city population and population not in the city for a country display
     */
    @Test
    void displayAdditionalCitiesAndNonCitiesPopulationInCountryTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        population = new Population();
        population.setName("Austria");
        population.setTotalPopulation(8091800L);
        population.setPopulationInCities(2384273L);
        population.setCityPopulationPercentage("29.4653 %");
        population.setPopulationNotInCities(5707527L);
        population.setNonCityPopulationPercentage("70.5347 %");

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayPopulation(extractedCitiesAndNonCitiesPopulation, "Country", "Austria");
    }

    /**
     * test for population in a district display
     */
    @Test
    void displayAdditionalPopulationInDistrictTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "District", "Gelderland");
    }

    /**
     * test for population in a city display
     */
    @Test
    void displayAdditionalPopulationInCityTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "City", "Resistencia");
    }







    /**
     * Unit tests for language report related query and display methods
     * @author Cham Myae Pyae Sone
     */

    /**
     * null test for language display
     */
    @Test
    void displayLanguageNullTest(){

        lgr.displayLanguage(null);
    }

    /**
     * empty test for language display
     */
    @Test
    void displayLanguageEmptyTest(){
        ArrayList<Language> extractedLanguage = new ArrayList<>();
        lgr.displayLanguage(extractedLanguage);
    }

    /**
     * adding null value to test for language display
     */
    @Test
    void displayLanguageContainsNullTest(){
        ArrayList<Language> extractedLanguage = new ArrayList<>();
        extractedLanguage.add(null);
        extractedLanguage.add(null);

        lgr.displayLanguage(extractedLanguage);
    }

    /**
     * test for language display
     */
    @Test
    void displayLanguageTest() {
        ArrayList<Language> extractedLanguage = new ArrayList<>();

        extractedLanguage.add(language);
        lgr.displayLanguage(extractedLanguage);
    }

}

