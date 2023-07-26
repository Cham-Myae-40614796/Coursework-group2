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


class IntegrationTest {

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
        dbConn.connect("localhost", 33060);
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
        assertEquals("Pakistan",extractedCountries.get(5).getCountryName(), "Sixth data row of the country's name in the world table should be Pakistan");
        assertEquals("PAK", extractedCountries.get(5).getCountryCode(), "Sixth data row of the country's code in the world table should be PAK");
        assertEquals("Asia", extractedCountries.get(5).getContinent(), "Sixth data row of the country's continent in the world table should be Asia");
        assertEquals("Southern and Central Asia", extractedCountries.get(5).getRegion(), "Sixth data row of the country's region in the world table should be Southern and Central Asia");
        assertEquals(156483000, extractedCountries.get(5).getPopulation(), "Sixth data row of the country's population in the world table should be 156483000");
        assertEquals("Islamabad",extractedCountries.get(5).getCapital(), "Sixth data row of the country's capital in the world table should be Islamabad");
    }

    /**
     * Test for country in the continent extraction
     */
    @Test
    void extractCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals("Japan", extractedCountries.get(5).getCountryName(), "Sixth data row of the country's name in the continent table should be Japan");
        assertEquals("JPN", extractedCountries.get(5).getCountryCode(), "Sixth data row of the country's code in the continent table should be JPN");
        assertEquals("Asia", extractedCountries.get(5).getContinent(), "Sixth data row of the country's continent in the continent table should be Asia");
        assertEquals("Eastern Asia", extractedCountries.get(5).getRegion(), "Sixth data row of the country's region in the continent table should be Eastern Asia");
        assertEquals(126714000, extractedCountries.get(5).getPopulation(), "Sixth data row of the country's population in the continent table should be 126714000");
        assertEquals("Tokyo", extractedCountries.get(5).getCapital(), "Sixth data row of the country's capital in the continent table should be Tokyo");
    }

    /**
     * Test for country in the region extraction
     */
    @Test
    void extractCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals("Hong Kong", extractedCountries.get(5).getCountryName(), "Sixth data row of the country's name in the region table should be Hong Kong");
        assertEquals("HKG", extractedCountries.get(5).getCountryCode(), "Sixth data row of the country's code in the region table should be HKG");
        assertEquals("Asia", extractedCountries.get(5).getContinent(), "Sixth data row of the country's continent in the region table should be Asia");
        assertEquals("Eastern Asia", extractedCountries.get(5).getRegion(), "Sixth data row of the country's region in the region table should be Eastern Asia");
        assertEquals(6782000, extractedCountries.get(5).getPopulation(), "Sixth data row of the country's population in the region table should be 6782000");
        assertEquals("Victoria", extractedCountries.get(5).getCapital(), "Sixth data row of the country's capital in the region table should be Victoria");
    }

    /**
     * Test for top country in the world extraction
     */
    @Test
    void extractTopCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("United States", extractedCountries.get(2).getCountryName(), "Third data row of the top populated country's name in the world table should be United States");
        assertEquals("USA", extractedCountries.get(2).getCountryCode(), "Third data row of the top populated country's code in the world table should be USA");
        assertEquals("North America", extractedCountries.get(2).getContinent(), "Third data row of the top populated country's continent in the world table should be North America");
        assertEquals("North America", extractedCountries.get(2).getRegion(), "Third data row of the top populated country's region in the world table should be North America");
        assertEquals(278357000, extractedCountries.get(2).getPopulation(), "Third data row of the top populated country's population in the world table should be 278357000");
        assertEquals("Washington", extractedCountries.get(2).getCapital(), "Third data row of the top populated country's capital in the world table should be Washington");
    }

    /**
     * Test for top country in the continent extraction
     */
    @Test
    void extractTopCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("Indonesia", extractedCountries.get(2).getCountryName(), "Third data row of the top populated country's name in the continent table should be Indonesia");
        assertEquals("IDN", extractedCountries.get(2).getCountryCode(), "Third data row of the top populated country's code in the continent table should be IND");
        assertEquals("Asia", extractedCountries.get(2).getContinent(), "Third data row of the top populated continent's name in the continent table should be Asia");
        assertEquals("Southeast Asia", extractedCountries.get(2).getRegion(), "Third data row of the top populated country's name in the region table should be Southeast Asia");
        assertEquals(212107000, extractedCountries.get(2).getPopulation(), "Third data row of the top populated country's population in the continent table should be 212107000");
        assertEquals("Jakarta", extractedCountries.get(2).getCapital(), "Third data row of the top populated country's capital in the continent table should be Jakarta");
    }

    /**
     * Test for top country in the region extraction
     */
    @Test
    void extractTopCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals("South Korea", extractedCountries.get(2).getCountryName(), "Third data row of the top populated country's name in the region table should be South Korea");
        assertEquals("KOR", extractedCountries.get(2).getCountryCode(), "Third data row of the top populated country's code in the region table should be KOR");
        assertEquals("Asia", extractedCountries.get(2).getContinent(), "Third data row of the top populated country's continent in the region table should be Asia");
        assertEquals("Eastern Asia", extractedCountries.get(2).getRegion(), "Third data row of the top populated country's region in the region table should be Southern Asia");
        assertEquals(46844000, extractedCountries.get(2).getPopulation(), "Third data row of the top populated country's population in the region table should be 46844000");
        assertEquals("Seoul", extractedCountries.get(2).getCapital(), "Third data row of the top populated country's capital in the region table should be Seoul");
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
        assertEquals("Karachi", extractedCities.get(5).getCityName(), "Sixth data row of the city's name in the world table should be Karachi");
        assertEquals("Pakistan", extractedCities.get(5).getCountryName(), "Sixth data row of the city's country in the world table should be Pakistan");
        assertEquals("Sindh", extractedCities.get(5).getDistrict(), "Sixth data row of the city's district in the world table should be Sindh");
        assertEquals(9269265, extractedCities.get(5).getPopulation(),"Sixth data row of the city's population in the world table should be 9269265");
    }

    /**
     * Test for city in the continent extraction
     */
    @Test
    void extractCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Istanbul", extractedCities.get(5).getCityName(), "Sixth data row of the city's name in the continent table should be Istanbul");
        assertEquals("Turkey", extractedCities.get(5).getCountryName(), "Sixth data row of the city's country in the continent table should be Turkey");
        assertEquals("Istanbul", extractedCities.get(5).getDistrict(), "Sixth data row of the city's name in the district table should be Istanbul");
        assertEquals(8787958, extractedCities.get(5).getPopulation(),"Sixth data row of the city's population in the continent table should be 8787958");
    }

    /**
     * Test for city in the region extraction
     */
    @Test
    void extractCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Tianjin", extractedCities.get(5).getCityName(), "Sixth data row of the city's name in the region table should be Tianjin");
        assertEquals("China", extractedCities.get(5).getCountryName(), "Sixth data row of the city's country in the region table should be China");
        assertEquals("Tianjin", extractedCities.get(5).getDistrict(), "Sixth data row of the city's name in the district table should be Tianjin");
        assertEquals(5286800, extractedCities.get(5).getPopulation(),"Sixth data row of the city's population in the region table should be 5286800");
    }

    /**
     * Test for city in the country extraction
     */
    @Test
    void extractCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Sétif", extractedCities.get(5).getCityName(), "Sixth data row of the city's name in the country table should be Sétif");
        assertEquals("Algeria", extractedCities.get(5).getCountryName(), "Sixth data row of the city's country in the country table should be Algeria");
        assertEquals("Sétif", extractedCities.get(5).getDistrict(), "Sixth data row of the city's district in the country table should be Sétif");
        assertEquals(179055, extractedCities.get(5).getPopulation(),"Sixth data row of the city's population in the country table should be 179055");
    }

    /**
     * Test for city in the district extraction
     */
    @Test
    void extractCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals("Ceyhan", extractedCities.get(2).getCityName(), "Third data row of the city's name in the district table should be Ceyhan");
        assertEquals("Turkey", extractedCities.get(2).getCountryName(), "Third data row of the city's country in the country table should be Turkey");
        assertEquals("Adana", extractedCities.get(2).getDistrict(), "Third data row of the city's district in the district table should be Adana");
        assertEquals(102412, extractedCities.get(2).getPopulation(),"Third data row of the city's population in the district table should be 102412");
    }

    /**
     * Test for top city in the world extraction
     */
    @Test
    void extractTopCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("São Paulo", extractedCities.get(2).getCityName(), "Third data row of the top populated city's name in the world table should be São Paulo");
        assertEquals("Brazil", extractedCities.get(2).getCountryName(), "Third data row of the top populated city's country in the world table should be Brazil");
        assertEquals("São Paulo", extractedCities.get(2).getDistrict(), "Third data row of the top populated city's district in the world table should be São Paulo");
        assertEquals(9968485, extractedCities.get(2).getPopulation(),"Third data row of the top populated city's population in the world table should be 9968485");

    }

    /**
     * Test for top city in the continent extraction
     */
    @Test
    void extractTopCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Shanghai", extractedCities.get(2).getCityName(), "Third data row of the top populated city's name in the continent table should be Shanghai");
        assertEquals("China", extractedCities.get(2).getCountryName(), "Third data row of the top populated city's country in the continent table should be China");
        assertEquals("Shanghai", extractedCities.get(2).getDistrict(), "Third data row of the top populated city's district in the continent table should be Shanghai");
        assertEquals(9696300, extractedCities.get(2).getPopulation(),"Third data row of the top populated city's population in the continent table should be 9696300");
    }

    /**
     * Test for top city in the region extraction
     */
    @Test
    void extractTopCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Tokyo", extractedCities.get(2).getCityName(), "Third data row of the top populated city's name in the region table should be Tokyo");
        assertEquals("Japan", extractedCities.get(2).getCountryName(), "Third data row of the top populated city's country in the region table should be Japan");
        assertEquals("Tokyo-to", extractedCities.get(2).getDistrict(), "Third data row of the top populated city's district in the region table should be Tokyo-to");
        assertEquals(7980230, extractedCities.get(2).getPopulation(), "Third data row of the top populated city's population in the region table should be 7980230");
    }

    /**
     * Test for top city in the country extraction
     */
    @Test
    void extractTopCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Constantine", extractedCities.get(2).getCityName(), "Third data row of the top populated city's name in the country table should be Constantine");
        assertEquals("Algeria", extractedCities.get(2).getCountryName(), "Third data row of the top populated city's country in the country table should be Algeria");
        assertEquals("Constantine", extractedCities.get(2).getDistrict(), "Third data row of the top populated city's district in the country table should be Constantine");
        assertEquals(443727, extractedCities.get(2).getPopulation(),"Third data row of the top populated city's population in the country table should be 443727");
    }

    /**
     * Test for top city in the district extraction
     */
    @Test
    void extractTopCityInDistrictTest() {
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals("Ceyhan", extractedCities.get(2).getCityName(), "Third data row of the top populated city's name in the district table should be Ceyhan");
        assertEquals("Turkey", extractedCities.get(2).getCountryName(), "Third data row of the top populated city's country in the district table should be Turkey");
        assertEquals("Adana",extractedCities.get(2).getDistrict(), "Third data row of the top populated city's district in the district table should be Adana");
        assertEquals(102412, extractedCities.get(2).getPopulation(), "Third data row of the top populated city's population in the district table should be 102412");
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
        assertEquals("Peking", extractedCapitalCites.get(5).getCityName(), "Sixth data row of the capital city's name in the world table should be Peking");
        assertEquals("China", extractedCapitalCites.get(5).getCountryName(), "Sixth data row of the capital city's country in the world table should be China");
        assertEquals(7472000, extractedCapitalCites.get(5).getPopulation(), "Sixth data row of the capital city's population in the world table should be 7472000");
    }

    /**
     * Test for capital city in the continent extraction
     */
    @Test
    void extractCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals("Bangkok", extractedCapitalCites.get(5).getCityName(),"Sixth data row of the capital city's name in the continent table should be Bangkok");
        assertEquals("Thailand", extractedCapitalCites.get(5).getCountryName(), "Sixth data row of the capital city's country in the continent table should be Thailand");
        assertEquals(6320174, extractedCapitalCites.get(5).getPopulation(), "Sixth data row of the capital city's population in the continent table should be 6320174");
    }

    /**
     * Test for capital city in the region extraction
     */
    @Test
    void extractCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals("Victoria", extractedCapitalCites.get(5).getCityName(), "Sixth data row of the capital city's name in the region table should be Victoria");
        assertEquals("Hong Kong", extractedCapitalCites.get(5).getCountryName(), "Sixth data row of the capital city's country in the region table should be Hong Kong");
        assertEquals(1312637, extractedCapitalCites.get(5).getPopulation(), "Sixth data row of the capital city's population in the region table should be 1312637");
    }

    /**
     * Test for top capital city in the world extraction
     */
    @Test
    void extractTopCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Ciudad de México", extractedCapitalCites.get(2).getCityName(), "Third data row of the top populated capital city's name in the world table should be Ciudad de México");
        assertEquals("Mexico",extractedCapitalCites.get(2).getCountryName(), "Third data row of the top populated capital city's country in the world table should be Mexico");
        assertEquals(8591309, extractedCapitalCites.get(2).getPopulation(), "Third data row of the top populated capital city's population in the world table should be 8591309");
    }

    /**
     * Test for top capital city in the continent extraction
     */
    @Test
    void extractTopCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Tokyo", extractedCapitalCites.get(2).getCityName(), "Third data row of the top populated capital city's name in the continent table should be Tokyo");
        assertEquals("Japan", extractedCapitalCites.get(2).getCountryName(), "Third data row of the top populated capital city's country in the continent table should be Japan");
        assertEquals(7980230, extractedCapitalCites.get(2).getPopulation(), "Third data row of the top populated capital city's population in the continent table should be 7980230");
    }

    /**
     * Test for top capital city in the region extraction
     */
    @Test
    void extractTopCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals("Peking", extractedCapitalCites.get(2).getCityName(), "Third data row of the top populated capital city's name in the region table should be Peking");
        assertEquals("China", extractedCapitalCites.get(2).getCountryName(), "Third data row of the top populated capital city's country in the continent table should be China");
        assertEquals(7472000, extractedCapitalCites.get(2).getPopulation(), "Third data row of the top populated capital city's population in the region table should be 7472000");
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
        assertEquals("Asia", extractedPopulation.get(0).getName(), "First data row of the population in the continent table should be of Asia Continent");
        assertEquals(3705025700L, extractedPopulation.get(0).getTotalPopulation(), "First data row of the total population in the Asia continent should be 3705025700L");
        assertEquals(697604103L, extractedPopulation.get(0).getPopulationInCities(), "First data row of the population in the city of Asia continent should be 697604103L");
        assertEquals("18.8286 %", extractedPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage in the Asia continent table should be 18.8286 %");
        assertEquals(3007421597L, extractedPopulation.get(0).getPopulationNotInCities(), "First data row of the population not in the city of Asia continent should be 3007421597L");
        assertEquals("81.1714 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the population percentage in the Asia continent table should be 81.1714 %");
    }

    /**
     * Test for population in region extraction
     */
    @Test
    void extractPopulationInRegionTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Region");
        assertEquals("Australia and New Zealand", extractedPopulation.get(0).getName(), "First data row of the population in the region table should be Australia and New Zealand");
        assertEquals(22753100L, extractedPopulation.get(0).getTotalPopulation(), "First data row of the total population in the region table should be 22753100L");
        assertEquals(13163436L, extractedPopulation.get(0).getPopulationInCities(), "First data row of the population of the city in the region table should be 13163436L");
        assertEquals("57.8534 %", extractedPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage in the region table should be 57.8534 %");
        assertEquals(9589664L, extractedPopulation.get(0).getPopulationNotInCities(), "First data row of the population not in the city of the region table should be 9589664L");
        assertEquals("42.1466 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the population percentage in the region table should be 42.1466 %");
    }

    /**
     * Test for population in country extraction
     */
    @Test
    void extractPopulationInCountryTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Name");
        assertEquals("Afghanistan", extractedPopulation.get(0).getName(), "First data row of the population in the country table should be Afghanistan");
        assertEquals(22720000L, extractedPopulation.get(0).getTotalPopulation(), "First data row of the total population in the country table should be 22720000L");
        assertEquals(2332100L, extractedPopulation.get(0).getPopulationInCities(), "First data row of the population of the city in the country table should be 2332100L");
        assertEquals("10.2645 %", extractedPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage in the country table should be 10.2645 %");
        assertEquals(20387900L, extractedPopulation.get(0).getPopulationNotInCities(), "First data row of the population not in the city of the country table should be 20387900L");
        assertEquals("89.7355 %", extractedPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the population percentage in the country table should be 89.7355 %");
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
        assertEquals(6078749450L, extractedWorldPopulation.get(0).getTotalPopulation(), "First data row of the total world population should be 6078749450L");
    }

    /**
     * Test for city population and population not in a city for a continent extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInContinent(){
        String whereClause = "WHERE cnty.Continent = 'Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Continent", whereClause);
        assertEquals("Europe", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "First data row of the population and non population of the city in a continent table should be Europe");
        assertEquals(730074600L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), "First data row of the total population and non population of the city in a continent table should be 730074600L");
        assertEquals(241942813L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), "First data row of the population of the city in a continent table should be 241942813L");
        assertEquals("33.1395 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage of the city in a continent table should be 33.1395 %");
        assertEquals(488131787L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), "First data row of the non population of the city in a continent table should be 488131787L");
        assertEquals("66.8605 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the non population percentage of the city in a continent table should be 66.8605 %");
    }

    /**
     * Test for city population and population not in a city for a region extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInRegion(){
        String whereClause = "WHERE cnty.Region = 'Southern Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Region", whereClause);
        assertEquals("Southern Europe", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "First data row of the population and non population of the city in a region table should be Southern Europe");
        assertEquals(144674200L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), "First data row of the total population and non population of the city in a region table should be 144674200L");
        assertEquals(40016658L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), "First data row of the population of the city in a region table should be 40016658L");
        assertEquals("27.6598 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage of the city in a region table should be 27.6598 %");
        assertEquals(104657542L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), "First data row of the non population of the city in a region table should be 104657542L");
        assertEquals("72.3402 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the non population percentage of the city in a region table should be 72.3402 %");
    }

    /**
     * Test for city population and population not in a city for a country extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInCountry(){
        String whereClause = "WHERE cnty.Name = 'Austria' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Name", whereClause);
        assertEquals("Austria", extractedCitiesAndNonCitiesPopulation.get(0).getName(), "First data row of the population and non population of the city in a country table should be Austria");
        assertEquals(8091800L, extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), "First data row of the total population and non population of the city in a country table should be 8091800L");
        assertEquals(2384273L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), "First data row of the population of the city in a country table should be 2384273L");
        assertEquals("29.4653 %", extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "First data row of the population percentage of the city in a country table should be 29.4653 %");
        assertEquals(5707527L, extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), "First data row of the non population of the city in a country table should be 5707527L");
        assertEquals("70.5347 %", extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "First data row of the non population percentage of the city in a country table should be 70.5347 %");
    }

    /**
     * Test for population for a district extraction
     */
    @Test
    void extractPopulationInDistrict(){
        String whereClause = "WHERE city.District = 'Gelderland' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("District", whereClause);
        assertEquals("Gelderland", extractedPopulation.get(0).getName(), "First data row of the population in a district table should be Gelderland");
        assertEquals(545548L, extractedPopulation.get(0).getTotalPopulation(), "First data row of the population in a district table should be 545548L");
    }

    /**
     * Test for population for a city extraction
     */
    @Test
    void extractPopulationInCity(){
        String whereClause = "WHERE city.Name = 'Resistencia' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("Name", whereClause);
        assertEquals("Resistencia", extractedPopulation.get(0).getName(), "First data row of the population in a city table should be Resistencia");
        assertEquals(229212L, extractedPopulation.get(0).getTotalPopulation(), "First data row of the population in a city table should be 229212L");
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
        assertEquals("Chinese", extractedLanguage.get(0).getCountryLanguage(), "First data row of the population who speak Chinese among world population");
        assertEquals(1191843539L, extractedLanguage.get(0).getPopulation(), "First data row of the population who speak Chinese among world 1191843539L");
        assertEquals("19.61 %", extractedLanguage.get(0).getPercentage(), "First data row of the population percentage who speak Chinese among world population");
    }
}
