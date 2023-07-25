package com.napier.coursework;

/**
 * Integration testing for the whole coding and queries
 * mainly test extract methods
 * @author Thar Htet Nyan, Kyi Phyu khin, Htet Myat Thiri, Cham Myae Pyae Sone
 * @version 0.1.0.3
 * @since 0.1.0.3
 */

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class IntegrationTestApp {

    /**
     * declaring variable for respective necessary report files
     */
    static DatabaseConnection dbConn;
  
    static CountryReport cotyr;

    static CityReport cr;

    static CapitalCityReport ccr;

    static PopulationReport popr;

    static AdditionalPopulationReport apr;

    static LanguageReport lgr;


    /**
     * init method before performing tests
     */
    @BeforeAll
    static void init(){
        /**
         * setting connection to SQL database for each report
         */
        dbConn = new DatabaseConnection();
        dbConn.connect("localhost", 33061);
        cotyr = new CountryReport();
        cotyr.setConn(dbConn.getConn());
        cr = new CityReport();
        cr.setConn(dbConn.getConn());
        ccr = new CapitalCityReport();
        ccr.setConn(dbConn.getConn());
        popr = new PopulationReport();
        popr.setConn(dbConn.getConn());
        apr = new AdditionalPopulationReport();
        apr.setConn(dbConn.getConn());
        lgr = new LanguageReport();
        lgr.setConn(dbConn.getConn());
    }

    /**
     * disconnecting connection with SQL database
     */
    @AfterAll
    static void end(){
        dbConn.disconnect();
    }

    /**
     * Integration test for country report extract methods
     * @author Thar Htet Nyan
     */

    /**
     * Test for country in the world extraction
     */
    @Test
    void extractCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals("Sixth data row of the country's name in the world table should be Pakistan",extractedCountries.get(5).getCountryName(), "Pakistan");
        assertEquals("Sixth data row of the country's code in the world table should be PAK", extractedCountries.get(5).getCountryCode(), "PAK");
        assertEquals("Sixth data row of the country's continent in the world table should be Asia", extractedCountries.get(5).getContinent(), "Asia");
        assertEquals("Sixth data row of the country's region in the world table should be Southern and Central Asia", extractedCountries.get(5).getRegion(), "Southern and Central Asia");
        assertEquals(156483000, extractedCountries.get(5).getPopulation(), 156483000);
        assertEquals("Sixth data row of the country's capital in the world table should be Islamabad",extractedCountries.get(5).getCapital(), "Islamabad");
    }

    /**
     * Test for country in the continent extraction
     */
    @Test
    void extractCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals("Sixth data row of the country's name in the continent table should be Japan", extractedCountries.get(5).getCountryName(), "Japan");
        assertEquals("Sixth data row of the country's code in the continent table should be JPN", extractedCountries.get(5).getCountryCode(), "JPN");
        assertEquals("Sixth data row of the country's continent in the continent table should be Asia", extractedCountries.get(5).getContinent(), "Asia");
        assertEquals("Sixth data row of the country's region in the continent table should be Eastern Asia", extractedCountries.get(5).getRegion(), "Eastern Asia");
        assertEquals(126714000, extractedCountries.get(5).getPopulation(), 126714000);
        assertEquals("Sixth data row of the country's capital in the continent table should be Tokyo", extractedCountries.get(5).getCapital(), "Tokyo");
    }

    /**
     * Test for country in the region extraction
     */
    @Test
    void extractCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals("Sixth data row of the country's name in the region table should be Hong Kong", extractedCountries.get(5).getCountryName(), "Hong Kong");
        assertEquals("Sixth data row of the country's code in the region table should be HKG", extractedCountries.get(5).getCountryCode(), "HKG");
        assertEquals("Sixth data row of the country's continent in the region table should be Asia", extractedCountries.get(5).getContinent(), "Asia");
        assertEquals("Sixth data row of the country's region in the region table should be Eastern Asia", extractedCountries.get(5).getRegion(), "Eastern Asia");
        assertEquals(6782000, extractedCountries.get(5).getPopulation(), 6782000);
        assertEquals("Sixth data row of the country's capital in the region table should be Victoria", extractedCountries.get(5).getCapital(), "Victoria");
    }

    /**
     * Test for top country in the world extraction
     */
    @Test
    void extractTopCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("Third data row of the top populated country's name in the world table should be United States", extractedCountries.get(2).getCountryName(), "United States");
        assertEquals("Third data row of the top populated country's code in the world table should be USA", extractedCountries.get(2).getCountryCode(), "USA");
        assertEquals("Third data row of the top populated country's continent in the world table should be North America", extractedCountries.get(2).getContinent(), "North America");
        assertEquals("Third data row of the top populated country's region in the world table should be North America", extractedCountries.get(2).getRegion(), "North America");
        assertEquals(278357000, extractedCountries.get(2).getPopulation(), 278357000);
        assertEquals("Third data row of the top populated country's capital in the world table should be Washington", extractedCountries.get(2).getCapital(), "Washington");
    }

    /**
     * Test for top country in the continent extraction
     */
    @Test
    void extractTopCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("Third data row of the top populated country's name in the continent table should be Indonesia", extractedCountries.get(2).getCountryName(), "Indonesia");
        assertEquals("Third data row of the top populated country's code in the continent table should be IND", extractedCountries.get(2).getCountryCode(), "IDN");
        assertEquals("Third data row of the top populated continent's name in the continent table should be Asia", extractedCountries.get(2).getContinent(), "Asia");
        assertEquals("Third data row of the top populated country's name in the region table should be Southeast Asia", extractedCountries.get(2).getRegion(), "Southeast Asia");
        assertEquals(212107000, extractedCountries.get(2).getPopulation(), 212107000);
        assertEquals("Third data row of the top populated country's capital in the continent table should be Jakarta", extractedCountries.get(2).getCapital(), "Jakarta");
    }

    /**
     * Test for top country in the region extraction
     */
    @Test
    void extractTopCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("Third data row of the top populated country's name in the region table should be South Korea", extractedCountries.get(2).getCountryName(), "South Korea");
        assertEquals("Third data row of the top populated country's code in the region table should be KOR", extractedCountries.get(2).getCountryCode(), "KOR");
        assertEquals("Third data row of the top populated country's continent in the region table should be Asia", extractedCountries.get(2).getContinent(), "Asia");
        assertEquals("Third data row of the top populated country's region in the region table should be Southern Asia", extractedCountries.get(2).getRegion(), "Eastern Asia");
        assertEquals(46844000, extractedCountries.get(2).getPopulation(), 46844000);
        assertEquals("Third data row of the top populated country's capital in the region table should be Seoul", extractedCountries.get(2).getCapital(), "Seoul");
    }

    /**
     * Integration tests for city report extract methods
     * @author Thar Htet Nyan
     */

    /**
     * Test for city in the world extraction
     */
    @Test
    void extractCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Sixth data row of the city's name in the world table should be Karachi", extractedCities.get(5).getCityName(), "Karachi");
        assertEquals("Sixth data row of the city's country in the world table should be Pakistan", extractedCities.get(5).getCountryName(), "Pakistan");
        assertEquals("Sixth data row of the city's district in the world table should be Sindh", extractedCities.get(5).getDistrict(), "Sindh");
        assertEquals(9269265, extractedCities.get(5).getPopulation(),9269265);
    }

    /**
     * Test for city in the continent extraction
     */
    @Test
    void extractCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Sixth data row of the city's name in the continent table should be Istanbul", extractedCities.get(5).getCityName(), "Istanbul");
        assertEquals("Sixth data row of the city's country in the continent table should be Turkey", extractedCities.get(5).getCountryName(), "Turkey");
        assertEquals("Sixth data row of the city's name in the district table should be Istanbul", extractedCities.get(5).getDistrict(), "Istanbul");
        assertEquals(8787958, extractedCities.get(5).getPopulation(),8787958);
    }

    /**
     * Test for city in the region extraction
     */
    @Test
    void extractCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Sixth data row of the city's name in the region table should be Tianjin", extractedCities.get(5).getCityName(), "Tianjin");
        assertEquals("Sixth data row of the city's country in the region table should be China", extractedCities.get(5).getCountryName(), "China");
        assertEquals("Sixth data row of the city's name in the district table should be Tianjin", extractedCities.get(5).getDistrict(), "Tianjin");
        assertEquals(5286800, extractedCities.get(5).getPopulation(),5286800);
    }

    /**
     * Test for city in the country extraction
     */
    @Test
    void extractCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Sixth data row of the city's name in the country table should be Sétif", extractedCities.get(5).getCityName(), "Sétif");
        assertEquals("Sixth data row of the city's country in the country table should be Algeria", extractedCities.get(5).getCountryName(), "Algeria");
        assertEquals("Sixth data row of the city's district in the country table should be Sétif", extractedCities.get(5).getDistrict(), "Sétif");
        assertEquals(179055, extractedCities.get(5).getPopulation(),179055);
    }

    /**
     * Test for city in the district extraction
     */
    @Test
    void extractCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Third data row of the city's name in the district table should be Ceyhan", extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals("Third data row of the city's country in the country table should be Turkey", extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals("Third data row of the city's district in the district table should be Adana", extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(102412, extractedCities.get(2).getPopulation(),102412);
    }

    /**
     * Test for top city in the world extraction
     */
    @Test
    void extractTopCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Third data row of the top populated city's name in the world table should be São Paulo", extractedCities.get(2).getCityName(), "São Paulo");
        assertEquals("Third data row of the top populated city's country in the world table should be Brazil", extractedCities.get(2).getCountryName(), "Brazil");
        assertEquals("Third data row of the top populated city's district in the world table should be São Paulo", extractedCities.get(2).getDistrict(), "São Paulo");
        assertEquals(9968485, extractedCities.get(2).getPopulation(),9968485);

    }

    /**
     * Test for top city in the continent extraction
     */
    @Test
    void extractTopCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Third data row of the top populated city's name in the continent table should be Shanghai", extractedCities.get(2).getCityName(), "Shanghai");
        assertEquals("Third data row of the top populated city's country in the continent table should be China", extractedCities.get(2).getCountryName(), "China");
        assertEquals("Third data row of the top populated city's district in the continent table should be Shanghai", extractedCities.get(2).getDistrict(), "Shanghai");
        assertEquals(9696300, extractedCities.get(2).getPopulation(),9696300);
    }

    /**
     * Test for top city in the region extraction
     */
    @Test
    void extractTopCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Third data row of the top populated city's name in the region table should be Tokyo", extractedCities.get(2).getCityName(), "Tokyo");
        assertEquals("Third data row of the top populated city's country in the region table should be Japan", extractedCities.get(2).getCountryName(), "Japan");
        assertEquals("Third data row of the top populated city's district in the region table should be Tokyo-to", extractedCities.get(2).getDistrict(), "Tokyo-to");
        assertEquals(7980230, extractedCities.get(2).getPopulation(), 7980230);
    }

    /**
     * Test for top city in the country extraction
     */
    @Test
    void extractTopCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Third data row of the top populated city's name in the country table should be Constantine", extractedCities.get(2).getCityName(), "Constantine");
        assertEquals("Third data row of the top populated city's country in the country table should be Algeria", extractedCities.get(2).getCountryName(), "Algeria");
        assertEquals("Third data row of the top populated city's district in the country table should be Constantine", extractedCities.get(2).getDistrict(), "Constantine");
        assertEquals(443727, extractedCities.get(2).getPopulation(),443727);
    }

    /**
     * Test for top city in the district extraction
     */
    @Test
    void extractTopCityInDistrictTest() {
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Third data row of the top populated city's name in the district table should be Ceyhan", extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals("Third data row of the top populated city's country in the district table should be Turkey", extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals("Third data row of the top populated city's district in the district table should be Adana",extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(102412, extractedCities.get(2).getPopulation(), 102412);
    }

    /**
     * Integration tests for capital city report extract methods
     * @author Thar Htet Nyan
     */

    /**
     * Test for capital city in the world extraction
     */
    @Test
    void extractCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals("Sixth data row of the capital city's name in the world table should be Peking", extractedCapitalCites.get(5).getCityName(), "Peking");
        assertEquals("Sixth data row of the capital city's country in the world table should be China", extractedCapitalCites.get(5).getCountryName(), "China");
        assertEquals(7472000, extractedCapitalCites.get(5).getPopulation(), 7472000);
    }

    /**
     * Test for capital city in the continent extraction
     */
    @Test
    void extractCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals("Sixth data row of the capital city's name in the continent table should be Bangkok", extractedCapitalCites.get(5).getCityName(), "Bangkok");
        assertEquals("Sixth data row of the capital city's country in the continent table should be Thailand", extractedCapitalCites.get(5).getCountryName(), "Thailand");
        assertEquals(6320174, extractedCapitalCites.get(5).getPopulation(), 6320174);
    }

    /**
     * Test for capital city in the region extraction
     */
    @Test
    void extractCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals("Sixth data row of the capital city's name in the region table should be Victoria", extractedCapitalCites.get(5).getCityName(), "Victoria");
        assertEquals("Sixth data row of the capital city's country in the region table should be Hong Kong", extractedCapitalCites.get(5).getCountryName(), "Hong Kong");
        assertEquals(1312637, extractedCapitalCites.get(5).getPopulation(), 1312637);
    }

    /**
     * Test for top capital city in the world extraction
     */
    @Test
    void extractTopCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Third data row of the top populated capital city's name in the world table should be Ciudad de México", extractedCapitalCites.get(2).getCityName(), "Ciudad de México");
        assertEquals("Third data row of the top populated capital city's country in the world table should be Mexico",extractedCapitalCites.get(2).getCountryName(), "Mexico");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 8591309);
    }

    /**
     * Test for top capital city in the continent extraction
     */
    @Test
    void extractTopCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Third data row of the top populated capital city's name in the continent table should be Tokyo", extractedCapitalCites.get(2).getCityName(), "Tokyo");
        assertEquals("Third data row of the top populated capital city's country in the continent table should be Japan", extractedCapitalCites.get(2).getCountryName(), "Japan");
        assertEquals(7980230, extractedCapitalCites.get(2).getPopulation(), 7980230);
    }

    /**
     * Test for top capital city in the region extraction
     */
    @Test
    void extractTopCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Third data row of the top populated capital city's name in the region table should be Peking", extractedCapitalCites.get(2).getCityName(), "Peking");
        assertEquals("Third data row of the top populated capital city's country in the continent table should be China", extractedCapitalCites.get(2).getCountryName(), "China");
        assertEquals(7472000, extractedCapitalCites.get(2).getPopulation(), 7472000);
    }

    /**
     * Integration tests for population report extract methods
     * @author Kyi Phyu Khin
     */

    /**
     * Test for population in continent extraction
     */
    @Test
    void extractPopulationInContinentTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Continent");
        assertEquals("First data row of the population in the continent table should be Asia", extractedPopulation.get(0).getName(), "Asia");
        assertEquals(3705025700L, extractedPopulation.get(0).getTotalPopulation(), 3705025700L);
        assertEquals(697604103L, extractedPopulation.get(0).getPopulationInCities(), 697604103L);
        assertEquals("First data row of the population percentage in the continent table should be 18.8286 %", extractedPopulation.get(0).getCityPopulationPercentage(), "18.8286 %");
        assertEquals(3007421597L, extractedPopulation.get(0).getPopulationNotInCities(), 3007421597L);
        assertEquals("First data row of the population percentage in the continent table should be 81.1714 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "81.1714 %");
    }

    /**
     * Test for population in region extraction
     */
    @Test
    void extractPopulationInRegionTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Region");
        assertEquals("First data row of the population in the region table should be Australia and New Zealand", extractedPopulation.get(0).getName(), "Australia and New Zealand");
        assertEquals(22753100L, extractedPopulation.get(0).getTotalPopulation(), 22753100L);
        assertEquals(13163436L, extractedPopulation.get(0).getPopulationInCities(), 13163436L);
        assertEquals("First data row of the population percentage in the region table should be 57.8534 %", extractedPopulation.get(0).getCityPopulationPercentage(), "57.8534 %");
        assertEquals(9589664L, extractedPopulation.get(0).getPopulationNotInCities(), 9589664L);
        assertEquals("First data row of the population percentage in the region table should be 42.1466 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "42.1466 %");
    }

    /**
     * Test for population in country extraction
     */
    @Test
    void extractPopulationInCountryTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Country");
        assertEquals("First data row of the population in the country table should be Afghanistan", extractedPopulation.get(0).getName(), "Afghanistan");
        assertEquals(22720000L, extractedPopulation.get(0).getTotalPopulation(), 22720000L);
        assertEquals(2332100L, extractedPopulation.get(0).getPopulationInCities(), 2332100L);
        assertEquals("First data row of the population percentage in the country table should be 10.2645 %", extractedPopulation.get(0).getCityPopulationPercentage(), "10.2645 %");
        assertEquals(20387900L, extractedPopulation.get(0).getPopulationNotInCities(), 20387900L);
        assertEquals("First data row of the population percentage in the country table should be 89.7355 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "89.7355 %");
    }

    /**
     * Integration tests for additional population report extract methods
     * @author Htet Myat Thiri
     */

    /**
     * Test for total world population extraction
     */
    @Test
    void extractWorldPopulationTest(){
        ArrayList<Population> extractedWorldPopulation = apr.extractWorldPopulation();
        assertEquals(6078749450L, extractedWorldPopulation.get(0).getTotalPopulation(), 6078749450L);
    }

    /**
     * Test for city population and population not in a city for a continent extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInContinent(){
        String whereClause = "WHERE cnty.Continent = 'Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Continent", whereClause);
        assertEquals("First data row of the population and non population of the city in a continent table should be Europe", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Europe");
        assertEquals(730074600L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 730074600L);
        assertEquals(241942813L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 241942813L);
        assertEquals("First data row of the population and non population percentage of the city in a continent table should be 33.1395 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "33.1395 %");
        assertEquals(488131787L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 488131787L);
        assertEquals("First data row of the population and non population percentage of the city in a continent table should be 66.8605 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "66.8605 %");
    }

    /**
     * Test for city population and population not in a city for a region extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInRegion(){
        String whereClause = "WHERE cnty.Region = 'Southern Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Region", whereClause);
        assertEquals("First data row of the population and non population of the city in a region table should be Southern Europe", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Southern Europe");
        assertEquals(144674200L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 144674200L);
        assertEquals(40016658L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 40016658L);
        assertEquals("First data row of the population and non population percentage of the city in a region table should be 27.6598 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "27.6598 %");
        assertEquals(104657542L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 104657542L);
        assertEquals("First data row of the population and non population percentage of the city in a region table should be 72.3402 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "72.3402 %");
    }

    /**
     * Test for city population and population not in a city for a country extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInCountry(){
        String whereClause = "WHERE cnty.Name = 'Austria' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Country", whereClause);
        assertEquals("First data row of the population and non population of the city in a country table should be Austria", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Austria");
        assertEquals(8091800L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 8091800L);
        assertEquals(2384273L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 2384273L);
        assertEquals("First data row of the population and non population percentage of the city in a country table should be 29.4653 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "29.4653 %");
        assertEquals(5707527L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 5707527L);
        assertEquals("First data row of the population and non population percentage of the city in a country table should be 70.5347 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "70.5347 %");
    }

    /**
     * Test for population for a district extraction
     */
    @Test
    void extractPopulationInDistrict(){
        String whereClause = "WHERE city.District = 'Gelderland' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("District", whereClause);
        assertEquals("First data row of the population in a district table should be Gelderland", extractedPopulation.get(0).getName(), "Gelderland");
        assertEquals(545548L, extractedPopulation.get(0).getTotalPopulation(), 545548L);
    }

    /**
     * Test for population for a city extraction
     */
    @Test
    void extractPopulationInCity(){
        String whereClause = "WHERE city.Name = 'Resistencia' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("City", whereClause);
        assertEquals("First data row of the population in a city table should be Resistencia", extractedPopulation.get(0).getName(), "Resistencia");
        assertEquals(229212L, extractedPopulation.get(0).getTotalPopulation(), 229212L);
    }

    /**
     * Integration tests for language report extract methods
     * @author Cham Myae Pyae Sone
     */

    /**
     * Test for language extraction
     */
    @Test
    void extractLanguageTest(){
        ArrayList<Language> extractedLanguage = lgr.extractLanguage();
        assertEquals("First data row of the population who speak Chinese among world population", extractedLanguage.get(0).getCountryLanguage(), "Chinese");
        assertEquals(1191843539L, extractedLanguage.get(0).getPopulation(), 1191843539L);
        assertEquals("First data row of the population percentage who speak Chinese among world population", extractedLanguage.get(0).getPercentage(), "19.61 %");
    }
}
