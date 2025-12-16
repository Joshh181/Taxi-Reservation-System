package com.mycompany.taxireservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Manager
 * Handles all database connections for the Taxi Reservation System
 */
public class DatabaseConnection {
    
    // ============================================
    // DATABASE CONFIGURATION
    // ============================================
    // CHANGE THESE VALUES TO MATCH YOUR MYSQL SETUP!
    
    private static final String HOST = "localhost";        // Database host (usually localhost)
    private static final String PORT = "3306";             // MySQL port (default: 3306)
    private static final String DATABASE = "taxi_reservation_db";  // Database name
    private static final String USERNAME = "root";         // MySQL username
    private static final String PASSWORD = "admin123";     // ⚠️ CHANGE THIS to YOUR password!
    
    // Complete connection URL
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE 
                                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    // Connection object (singleton pattern)
    private static Connection connection = null;
    
    /**
     * Get database connection (creates new connection if needed)
     * @return Connection object or null if connection fails
     */
    public static Connection getConnection() {
        try {
            // Check if connection exists and is valid
            if (connection == null || connection.isClosed()) {
                
                // Step 1: Load MySQL JDBC Driver
                System.out.println("Loading MySQL JDBC Driver...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("✓ Driver loaded successfully!");
                
                // Step 2: Establish connection
                System.out.println("Connecting to database...");
                System.out.println("URL: " + URL);
                System.out.println("Username: " + USERNAME);
                
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                
                System.out.println("✓ Database connected successfully!");
                System.out.println("✓ Connection established to: " + DATABASE);
            }
            
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: MySQL JDBC Driver not found!");
            System.err.println("Make sure mysql-connector-j.jar is added to your project libraries.");
            e.printStackTrace();
            return null;
            
        } catch (SQLException e) {
            System.err.println("❌ ERROR: Database connection failed!");
            System.err.println("Please check:");
            System.err.println("1. MySQL Server is running");
            System.err.println("2. Database name is correct: " + DATABASE);
            System.err.println("3. Username is correct: " + USERNAME);
            System.err.println("4. Password is correct");
            System.err.println("5. Port is correct: " + PORT);
            System.err.println("\nError details:");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error closing connection:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test database connection
     * @return true if connection successful, false otherwise
     */
    public static boolean testConnection() {
        System.out.println("\n========================================");
        System.out.println("Testing Database Connection...");
        System.out.println("========================================");
        
        Connection conn = getConnection();
        
        if (conn != null) {
            System.out.println("✓ Connection test PASSED!");
            System.out.println("========================================\n");
            return true;
        } else {
            System.out.println("❌ Connection test FAILED!");
            System.out.println("========================================\n");
            return false;
        }
    }
    
    /**
     * Main method for testing connection independently
     */
    public static void main(String[] args) {
        System.out.println("DATABASE CONNECTION TEST");
        System.out.println("========================\n");
        
        if (testConnection()) {
            System.out.println("✅ SUCCESS! You can now use the database.");
        } else {
            System.out.println("❌ FAILED! Please check your configuration.");
        }
        
        // Clean up
        closeConnection();
    }
}