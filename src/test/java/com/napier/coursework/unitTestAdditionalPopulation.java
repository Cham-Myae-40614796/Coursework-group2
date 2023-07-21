package com.napier.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class unitTestAdditionalPopulation {
    static AdditionalPopulationReport apr;

    static Population population;

    @BeforeAll
    static void init(){
        apr = new AdditionalPopulationReport();
        population = new Population();
        //population.setName("");
        population.setContinent("Europe");
        population.setRegion("Southern Europe");
        population.setCountry("Austria");
        population.setDistrict("Gelderland");
        population.setCity("Resistencia");
        /*population.setCountryName("South Korea");
        population.setTotalPopulation(607874945);
        population.setPopulationInCities(9981619);
        population.setPopulationNotInCities(9981234);*/
    }

    @Test
    void displayWorldPopulationEmptyTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        apr.displayWorldPopulation(extractedWorldPopulation);
    }

    @Test
    void displayCitiesAndNonCitiesPopulationEmptyTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "", "");
    }

    @Test
    void displayPopulationEmptyTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        apr.displayPopulation(extractedPopulation, "", "" );
    }

    @Test
    void displayWorldPopulationNullTest(){
        apr.displayWorldPopulation(null);
    }

    @Test
    void displayCitiesAndNonCitiesPopulationNullTest(){
        apr.displayCitiesAndNonCitiesPopulation(null, "Continent", "Europe");
    }

    @Test
    void displayPopulationNullTest(){
        apr.displayPopulation(null, "District", "Gederland");
    }

    @Test
    void displayWorldPopulationContainsNullTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        extractedWorldPopulation.add(null);

        apr.displayWorldPopulation(extractedWorldPopulation);
    }
    @Test
    void displayCitiesAndNonCitiesPopulationContainsNullTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        extractedCitiesAndNonCitiesPopulation.add(null);
        extractedCitiesAndNonCitiesPopulation.add(null);

        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Continent", "Europe");
    }

    @Test
    void displayPopulationContainsNullTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        extractedPopulation.add(null);
        extractedPopulation.add(null);

        apr.displayPopulation(extractedPopulation, "District", "Gederland");
    }

    @Test
    void displayWorldPopulationTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        apr.displayWorldPopulation(extractedWorldPopulation);
    }

    @Test
    void displayCitiesAndNonCitiesPopulationInContinentTest(){
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

    @Test
    void displayCitiesAndNonCitiesPopulationInRegionTest(){
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

    @Test
    void displayCitiesAndNonCitiesPopulationInCountryTest(){
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


    @Test
    void displayPopulationInDistrictTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "District", "Gelderland");
    }

    @Test
    void displayPopulationInCityTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "City", "Resistencia");
    }
}