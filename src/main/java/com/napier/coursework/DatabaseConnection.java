package com.napier.coursework;
/**
 * Represents database connection to SQL
 * Handles Connection and Disconnection to database
 * @author Thar Htet Nyan
 * @version 0.1.0.3
 * @since 0.1.0.2
 */

import java.sql.*;

public class DatabaseConnection {

    /**
     * Stores SQL database connection
     */
    private Connection conn = null;

    /**
     *
     * @return conn is returned to execute query and extract data from SQL database.
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Make a connection to the SQL database.
     */
    public void connect(){
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Retry connection count to the database
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database

                if (i%2 ==0) {
                    conn = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                } else {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                }
                
                System.out.println("Successfully connected");
                // Exit retry loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect the connection from the SQL database.
     */
    public void disconnect(){
        if (conn != null) {
            try {
                // Close connection
                conn.close();
                System.out.println("Connection Disconnected.");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}
