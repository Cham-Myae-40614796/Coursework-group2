package com.napier.coursework;

import javax.xml.crypto.Data;
import java.sql.*;

public class App {

    public static void main(String[] args){

        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.connect();


        CityReport cr = new CityReport();
        cr.setConn(dbConn.getConn());
        cr.generateCityReport();

        dbConn.disconnect();


    }

}
