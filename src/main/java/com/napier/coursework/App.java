package com.napier.coursework;

import javax.xml.crypto.Data;
import java.sql.*;

public class App {

    public static void main(String[] args){

        DatabaseConnection db_conn = new DatabaseConnection();
        db_conn.connect();


        CityReport cr = new CityReport();
        cr.setConn(db_conn.getConn());
        cr.generateCityReport();

        db_conn.disconnect();


    }

}
