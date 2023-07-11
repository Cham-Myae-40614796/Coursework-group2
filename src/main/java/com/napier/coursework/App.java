package com.napier.coursework;

import javax.xml.crypto.Data;
import java.sql.*;

public class App {

    public static void main(String[] args){

        DatabaseConnection db_conn = new DatabaseConnection();
        db_conn.connect();
//        String format = "| %-20s | %-20s | %-20s | %-20s |%n";
//        System.out.println("Hello World!");
//        System.out.printf("---------------------------------------------------------------------------------------------%n");
//        System.out.printf(format, "City Name", "Country Name", "District", "Population");
//        System.out.printf("---------------------------------------------------------------------------------------------%n");
        CityReport cr = new CityReport();
        cr.setConn(db_conn.getConn());
        cr.generateCityReport();
        db_conn.disconnect();

    }

}
