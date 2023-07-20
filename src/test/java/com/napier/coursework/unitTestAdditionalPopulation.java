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
    void displayCitiesAndNonCitiesPopulationEmptyTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "", population.getName());
    }

    /*@Test
    void displayCitiesAndNonCitiesPopulationNullTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();
        extractedCitiesAndNonCitiesPopulation.add(null);
        extractedCitiesAndNonCitiesPopulation.add(null);

        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "", population.getName());
    }*/

    @Test
    void displayPopulationEmptyTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        apr.displayPopulation(extractedPopulation, "City", population.getCity() );
    }

    /*@Test
    void displayPopulationNullTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();
        extractedPopulation.add(null);
        extractedPopulation.add(null);

        apr.displayPopulation(extractedPopulation, "", population.getName());
    }*/
    @Test
    void displayWorldPopulationTest(){
        ArrayList<Population> extractedWorldPopulation = new ArrayList<>();
        apr.displayWorldPopulation();
    }

    @Test
    void displayCitiesAndNonCitiesPopulationInContinentTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Continent", population.getContinent());
    }

    @Test
    void displayCitiesAndNonCitiesPopulationInCountryTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Country", population.getCountry());
    }

    @Test
    void displayCitiesAndNonCitiesPopulationInRegionTest(){
        ArrayList<Population> extractedCitiesAndNonCitiesPopulation = new ArrayList<>();

        extractedCitiesAndNonCitiesPopulation.add(population);
        apr.displayCitiesAndNonCitiesPopulation(extractedCitiesAndNonCitiesPopulation, "Region", population.getRegion());
    }

    @Test
    void displayPopulationInDistrictTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "District", population.getDistrict());
    }

    @Test
    void displayPopulationInCityTest(){
        ArrayList<Population> extractedPopulation = new ArrayList<>();

        extractedPopulation.add(population);
        apr.displayPopulation(extractedPopulation, "City", population.getCity());
    }
}