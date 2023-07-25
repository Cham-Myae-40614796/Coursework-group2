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
        assertEquals(extractedCountries.get(5).getCountryName(), "Pakistan");
        assertEquals(extractedCountries.get(5).getCountryCode(), "PAK");
        assertEquals(extractedCountries.get(5).getContinent(), "Asia");
        assertEquals(extractedCountries.get(5).getRegion(), "Southern and Central Asia");
        assertEquals(extractedCountries.get(5).getPopulation(), 156483000);
        assertEquals(extractedCountries.get(5).getCapital(), "Islamabad");
    }

    /**
     * Test for country in the continent extraction
     */
    @Test
    void extractCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals(extractedCountries.get(5).getCountryName(), "Japan");
        assertEquals(extractedCountries.get(5).getCountryCode(), "JPN");
        assertEquals(extractedCountries.get(5).getContinent(), "Asia");
        assertEquals(extractedCountries.get(5).getRegion(), "Eastern Asia");
        assertEquals(extractedCountries.get(5).getPopulation(), 126714000);
        assertEquals(extractedCountries.get(5).getCapital(), "Tokyo");
    }

    /**
     * Test for country in the region extraction
     */
    @Test
    void extractCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, false);
        assertEquals(extractedCountries.get(5).getCountryName(), "Hong Kong");
        assertEquals(extractedCountries.get(5).getCountryCode(), "HKG");
        assertEquals(extractedCountries.get(5).getContinent(), "Asia");
        assertEquals(extractedCountries.get(5).getRegion(), "Eastern Asia");
        assertEquals(extractedCountries.get(5).getPopulation(), 6782000);
        assertEquals(extractedCountries.get(5).getCapital(), "Victoria");
    }

    /**
     * Test for top country in the world extraction
     */
    @Test
    void extractTopCountryInWorldTest(){
        String whereClause = "";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(2).getCountryName(), "United States");
        assertEquals(extractedCountries.get(2).getCountryCode(), "USA");
        assertEquals(extractedCountries.get(2).getContinent(), "North America");
        assertEquals(extractedCountries.get(2).getRegion(), "North America");
        assertEquals(extractedCountries.get(2).getPopulation(), 278357000);
        assertEquals(extractedCountries.get(2).getCapital(), "Washington");
    }

    /**
     * Test for top country in the continent extraction
     */
    @Test
    void extractTopCountryInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(2).getCountryName(), "Indonesia");
        assertEquals(extractedCountries.get(2).getCountryCode(), "IDN");
        assertEquals(extractedCountries.get(2).getContinent(), "Asia");
        assertEquals(extractedCountries.get(2).getRegion(), "Southeast Asia");
        assertEquals(extractedCountries.get(2).getPopulation(), 212107000);
        assertEquals(extractedCountries.get(2).getCapital(), "Jakarta");
    }

    /**
     * Test for top country in the region extraction
     */
    @Test
    void extractTopCountryInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<Country> extractedCountries = cotyr.extractCountries(whereClause, true);
        assertEquals(extractedCountries.get(2).getCountryName(), "South Korea");
        assertEquals(extractedCountries.get(2).getCountryCode(), "KOR");
        assertEquals(extractedCountries.get(2).getContinent(), "Asia");
        assertEquals(extractedCountries.get(2).getRegion(), "Eastern Asia");
        assertEquals(extractedCountries.get(2).getPopulation(), 46844000);
        assertEquals(extractedCountries.get(2).getCapital(), "Seoul");
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
        assertEquals(extractedCities.get(5).getCityName(), "Karachi");
        assertEquals(extractedCities.get(5).getCountryName(), "Pakistan");
        assertEquals(extractedCities.get(5).getDistrict(), "Sindh");
        assertEquals(extractedCities.get(5).getPopulation(),9269265);
    }

    /**
     * Test for city in the continent extraction
     */
    @Test
    void extractCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Istanbul");
        assertEquals(extractedCities.get(5).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(5).getDistrict(), "Istanbul");
        assertEquals(extractedCities.get(5).getPopulation(),8787958);
    }

    /**
     * Test for city in the region extraction
     */
    @Test
    void extractCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Tianjin");
        assertEquals(extractedCities.get(5).getCountryName(), "China");
        assertEquals(extractedCities.get(5).getDistrict(), "Tianjin");
        assertEquals(extractedCities.get(5).getPopulation(),5286800);
    }

    /**
     * Test for city in the country extraction
     */
    @Test
    void extractCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Sétif");
        assertEquals(extractedCities.get(5).getCountryName(), "Algeria");
        assertEquals(extractedCities.get(5).getDistrict(), "Sétif");
        assertEquals(extractedCities.get(5).getPopulation(),179055);
    }

    /**
     * Test for city in the district extraction
     */
    @Test
    void extractCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals(extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(extractedCities.get(2).getPopulation(),102412);
    }

    /**
     * Test for top city in the world extraction
     */
    @Test
    void extractTopCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "São Paulo");
        assertEquals(extractedCities.get(2).getCountryName(), "Brazil");
        assertEquals(extractedCities.get(2).getDistrict(), "São Paulo");
        assertEquals(extractedCities.get(2).getPopulation(),9968485);

    }

    /**
     * Test for top city in the continent extraction
     */
    @Test
    void extractTopCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Shanghai");
        assertEquals(extractedCities.get(2).getCountryName(), "China");
        assertEquals(extractedCities.get(2).getDistrict(), "Shanghai");
        assertEquals(extractedCities.get(2).getPopulation(),9696300);
    }

    /**
     * Test for top city in the region extraction
     */
    @Test
    void extractTopCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Tokyo");
        assertEquals(extractedCities.get(2).getCountryName(), "Japan");
        assertEquals(extractedCities.get(2).getDistrict(), "Tokyo-to");
        assertEquals(extractedCities.get(2).getPopulation(), 7980230);
    }

    /**
     * Test for top city in the country extraction
     */
    @Test
    void extractTopCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Constantine");
        assertEquals(extractedCities.get(2).getCountryName(), "Algeria");
        assertEquals(extractedCities.get(2).getDistrict(), "Constantine");
        assertEquals(extractedCities.get(2).getPopulation(),443727);
    }

    /**
     * Test for top city in the district extraction
     */
    @Test
    void extractTopCityInDistrictTest() {
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals(extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(extractedCities.get(2).getPopulation(), 102412);
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
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Peking");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "China");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 7472000);
    }

    /**
     * Test for capital city in the continent extraction
     */
    @Test
    void extractCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Bangkok");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "Thailand");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 6320174);
    }

    /**
     * Test for capital city in the region extraction
     */
    @Test
    void extractCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Victoria");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "Hong Kong");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 1312637);
    }

    /**
     * Test for top capital city in the world extraction
     */
    @Test
    void extractTopCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Ciudad de México");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "Mexico");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 8591309);
    }

    /**
     * Test for top capital city in the continent extraction
     */
    @Test
    void extractTopCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Tokyo");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "Japan");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 7980230);
    }

    /**
     * Test for top capital city in the region extraction
     */
    @Test
    void extractTopCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Peking");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "China");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 7472000);
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
        assertEquals(extractedPopulation.get(0).getName(), "Asia");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 3705025700L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 697604103L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "18.8286 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 3007421597L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "81.1714 %");
    }

    /**
     * Test for population in region extraction
     */
    @Test
    void extractPopulationInRegionTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Region");
        assertEquals(extractedPopulation.get(0).getName(), "Australia and New Zealand");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 22753100L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 13163436L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "57.8534 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 9589664L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "42.1466 %");
    }

    /**
     * Test for population in country extraction
     */
    @Test
    void extractPopulationInCountryTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Name");
        assertEquals(extractedPopulation.get(0).getName(), "Afghanistan");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 22720000L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 2332100L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "10.2645 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 20387900L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "89.7355 %");
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
        assertEquals(extractedWorldPopulation.get(0).getTotalPopulation(), 6078749450L);
    }

    /**
     * Test for city population and population not in a city for a continent extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInContinent(){
        String whereClause = "WHERE cnty.Continent = 'Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Continent", whereClause);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Europe");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 730074600L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 241942813L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "33.1395 %");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 488131787L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "66.8605 %");
    }

    /**
     * Test for city population and population not in a city for a region extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInRegion(){
        String whereClause = "WHERE cnty.Region = 'Southern Europe' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Region", whereClause);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Southern Europe");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 144674200L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 40016658L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "27.6598 %");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 104657542l);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "72.3402 %");
    }

    /**
     * Test for city population and population not in a city for a country extraction
     */
    @Test
    void extractCitiesAndNonCitiesPopulationInCountry(){
        String whereClause = "WHERE cnty.Name = 'Austria' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Name", whereClause);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Austria");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 8091800L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 2384273L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "29.4653 %");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 5707527L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "70.5347 %");
    }

    /**
     * Test for population for a district extraction
     */
    @Test
    void extractPopulationInDistrict(){
        String whereClause = "WHERE city.District = 'Gelderland' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("District", whereClause);
        assertEquals(extractedPopulation.get(0).getName(), "Gelderland");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 545548L);
    }

    /**
     * Test for population for a city extraction
     */
    @Test
    void extractPopulationInCity(){
        String whereClause = "WHERE city.Name = 'Resistencia' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("Name", whereClause);
        assertEquals(extractedPopulation.get(0).getName(), "Resistencia");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 229212L);
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
        assertEquals(extractedLanguage.get(0).getCountryLanguage(), "Chinese");
        assertEquals(extractedLanguage.get(0).getPopulation(), 1191843539);
        assertEquals(extractedLanguage.get(0).getPercentage(), "19.61 %");
    }
}
