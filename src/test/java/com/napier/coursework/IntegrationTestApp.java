package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.util.ArrayList;

import static com.napier.coursework.UnitTestApp.city;
import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTestApp {

    static DatabaseConnection dbConn;
  
    static CountryReport cotyr;

    static CityReport cr;

    static CapitalCityReport ccr;

    static PopulationReport popr;
  
    static LanguageReport lgr;


    static AdditionalPopulationReport apr;

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
        popr = new PopulationReport();
        popr.setConn(dbConn.getConn());
        apr = new AdditionalPopulationReport();
        apr.setConn(dbConn.getConn());
        lgr = new LanguageReport();
        lgr.setConn(dbConn.getConn());
    }
    @AfterAll
    static void end(){
        dbConn.disconnect();
    }

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




    @Test
    void extractCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Karachi");
        assertEquals(extractedCities.get(5).getCountryName(), "Pakistan");
        assertEquals(extractedCities.get(5).getDistrict(), "Sindh");
        assertEquals(extractedCities.get(5).getPopulation(),9269265);
    }

    @Test
    void extractCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Istanbul");
        assertEquals(extractedCities.get(5).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(5).getDistrict(), "Istanbul");
        assertEquals(extractedCities.get(5).getPopulation(),8787958);
    }

    @Test
    void extractCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Tianjin");
        assertEquals(extractedCities.get(5).getCountryName(), "China");
        assertEquals(extractedCities.get(5).getDistrict(), "Tianjin");
        assertEquals(extractedCities.get(5).getPopulation(),5286800);
    }

    @Test
    void extractCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(5).getCityName(), "Sétif");
        assertEquals(extractedCities.get(5).getCountryName(), "Algeria");
        assertEquals(extractedCities.get(5).getDistrict(), "Sétif");
        assertEquals(extractedCities.get(5).getPopulation(),179055);
    }

    @Test
    void extractCityInDistrictTest(){
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, false);
        assertEquals(extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals(extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(extractedCities.get(2).getPopulation(),102412);
    }

    @Test
    void extractTopCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "São Paulo");
        assertEquals(extractedCities.get(2).getCountryName(), "Brazil");
        assertEquals(extractedCities.get(2).getDistrict(), "São Paulo");
        assertEquals(extractedCities.get(2).getPopulation(),9968485);

    }

    @Test
    void extractTopCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Shanghai");
        assertEquals(extractedCities.get(2).getCountryName(), "China");
        assertEquals(extractedCities.get(2).getDistrict(), "Shanghai");
        assertEquals(extractedCities.get(2).getPopulation(),9696300);
    }

    @Test
    void extractTopCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Tokyo");
        assertEquals(extractedCities.get(2).getCountryName(), "Japan");
        assertEquals(extractedCities.get(2).getDistrict(), "Tokyo-to");
        assertEquals(extractedCities.get(2).getPopulation(), 7980230);
    }

    @Test
    void extractTopCityInCountryTest(){
        String whereClause = "WHERE country.Name = 'Algeria' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Constantine");
        assertEquals(extractedCities.get(2).getCountryName(), "Algeria");
        assertEquals(extractedCities.get(2).getDistrict(), "Constantine");
        assertEquals(extractedCities.get(2).getPopulation(),443727);
    }

    @Test
    void extractTopCityInDistrictTest() {
        String whereClause = "WHERE city.District = 'Adana' ";
        ArrayList<City> extractedCities = cr.extractCities(whereClause, true);
        assertEquals(extractedCities.get(2).getCityName(), "Ceyhan");
        assertEquals(extractedCities.get(2).getCountryName(), "Turkey");
        assertEquals(extractedCities.get(2).getDistrict(), "Adana");
        assertEquals(extractedCities.get(2).getPopulation(), 102412);
    }




    @Test
    void extractCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Peking");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "China");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 7472000);
    }

    @Test
    void extractCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Bangkok");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "Thailand");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 6320174);
    }

    @Test
    void extractCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, false);
        assertEquals(extractedCapitalCites.get(5).getCityName(), "Victoria");
        assertEquals(extractedCapitalCites.get(5).getCountryName(), "Hong Kong");
        assertEquals(extractedCapitalCites.get(5).getPopulation(), 1312637);
    }

    @Test
    void extractTopCapitalCityInWorldTest(){
        String whereClause = "";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Ciudad de México");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "Mexico");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 8591309);
    }

    @Test
    void extractTopCapitalCityInContinentTest(){
        String whereClause = "WHERE country.Continent = 'Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Tokyo");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "Japan");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 7980230);
    }

    @Test
    void extractTopCapitalCityInRegionTest(){
        String whereClause = "WHERE country.Region = 'Eastern Asia' ";
        ArrayList<City> extractedCapitalCites = ccr.extractCapitalCities(whereClause, true);
        assertEquals(extractedCapitalCites.get(2).getCityName(), "Peking");
        assertEquals(extractedCapitalCites.get(2).getCountryName(), "China");
        assertEquals(extractedCapitalCites.get(2).getPopulation(), 7472000);
    }








    @Test
    void extractPopulationInContinentTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Continent");
        assertEquals(extractedPopulation.get(0).getName(), "North America");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 482993000L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 168250381L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "34.8350 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 314742619L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "65.1650 %");
    }

    @Test
    void extractPopulationInRegionTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Region");
        assertEquals(extractedPopulation.get(0).getName(), "Caribbean");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 38140000L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 11067550L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "29.0182 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 27072450L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "70.9818 %");
    }

    @Test
    void extractPopulationInCountryTest(){
        ArrayList<Population> extractedPopulation = popr.extractPopulation("Country");
        assertEquals(extractedPopulation.get(0).getName(), "Aruba");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 103000L);
        assertEquals(extractedPopulation.get(0).getPopulationInCities(), 29034L);
        assertEquals(extractedPopulation.get(0).getCityPopulationPercentage(), "28.1883 %");
        assertEquals(extractedPopulation.get(0).getPopulationNotInCities(), 73966L);
        assertEquals(extractedPopulation.get(0).getNonCityPopulationPercentage(), "71.8117 %");
    }
  
  
  
  

    @Test
    void extractWorldPopulationTest(){
        ArrayList<Population> extractedWorldPopulation = apr.extractWorldPopulation();
        assertEquals(extractedWorldPopulation.get(0).getTotalPopulation(), 6078749450L);
    }

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

    @Test
    void extractCitiesAndNonCitiesPopulationInCountry(){
        String whereClause = "WHERE cnty.Name = 'Austria' ";
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = apr.extractCitiesAndNonCitiesPopulation("Country", whereClause);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getName(), "Austria");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getTotalPopulation(), 8091800L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationInCities(), 2384273L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getCityPopulationPercentage(), "29.4653 %");
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getPopulationNotInCities(), 5707527L);
        assertEquals(extractedCitiesAndNonCitiesPopulation.get(0).getNonCityPopulationPercentage(), "70.5347 %");
    }

    @Test
    void extractPopulationInDistrict(){
        String whereClause = "WHERE city.District = 'Gelderland' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("District", whereClause);
        assertEquals(extractedPopulation.get(0).getName(), "Gelderland");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 545548L);
    }

    @Test
    void extractPopulationInCity(){
        String whereClause = "WHERE city.Name = 'Resistencia' ";
        ArrayList<Population> extractedPopulation = apr.extractPopulation("City", whereClause);
        assertEquals(extractedPopulation.get(0).getName(), "Resistencia");
        assertEquals(extractedPopulation.get(0).getTotalPopulation(), 229212L);
    }
  
  
  
  
  
  
    @Test
    void extractLanguageTest(){
        ArrayList<Language> extractedLanguage = lgr.extractLanguage();
        assertEquals(extractedLanguage.get(0).getCountryLanguage(), "Chinese");
        assertEquals(extractedLanguage.get(0).getPopulation(), 1191843539);
        assertEquals(extractedLanguage.get(0).getPercentage(), "19.61 %");
    }
}
