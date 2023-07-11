package com.napier.coursework;

import javax.xml.crypto.Data;
import java.sql.*;

public class App {

    public static void main(String[] args){

        DatabaseConnection db_conn = new DatabaseConnection();
        db_conn.connect();
        System.out.println("Hello World!");
        db_conn.disconnect();

    }

}
