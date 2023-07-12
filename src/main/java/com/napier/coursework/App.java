package com.napier.coursework;

import javax.xml.crypto.Data;
import java.sql.*;

public class App {

    public static void main(String[] args){

        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.connect();

        CountryReport cntyr = new CountryReport();
        cntyr.setConn(dbConn.getConn());
        cntyr.generateCountryReport();

        CityReport ctyr = new CityReport();
        ctyr.setConn(dbConn.getConn());
        ctyr.generateCityReport();

        dbConn.disconnect();


    }

}
