package com.pahanaedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; // Added import for ResultSet
import java.sql.SQLException;
import java.sql.Statement; // Added import for Statement

public class DBConnection {

    // Database connection parameters for H2 in-memory database
    private static final String JDBC_URL = "jdbc:h2:mem:pahana_edu_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa"; // Default H2 user
    private static final String PASSWORD = ""; // Default H2 password

    // Static block to load the H2 JDBC driver when the class is loaded
    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("H2 JDBC Driver loaded."); // Added confirmation
        } catch (ClassNotFoundException e) {
            System.err.println("H2 JDBC Driver not found: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    /**
     * Establishes and returns a connection to the database.
     * @return A valid Connection object, or null if a connection error occurs.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            // System.out.println("Database connection established."); // Commented out to avoid excessive output
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Closes the given database connection, statement, and result set to release resources.
     * Always call this in a finally block to ensure resources are closed even if errors occur.
     * @param connection The Connection object to close.
     * @param statement The Statement (or PreparedStatement) object to close. Can be null.
     * @param resultSet The ResultSet object to close. Can be null.
     */
    public static void closeResources(Connection connection,
                                      Statement statement, // Changed to java.sql.Statement
                                      ResultSet resultSet) { // Changed to java.sql.ResultSet
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            // System.out.println("Database resources closed."); // Commented out to avoid excessive output
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initializes the database schema (creates tables and inserts initial data) if they don't exist.
     * This method should be called once when the application starts.
     */
    public static void initializeDatabase() {
        Connection conn = null;
        Statement stmt = null; // Changed to java.sql.Statement
        try {
            conn = getConnection();
            if (conn != null) {
                stmt = conn.createStatement();

                // Users table
                stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY," +
                             "username VARCHAR(50) NOT NULL UNIQUE," +
                             "password VARCHAR(255) NOT NULL," + // Store hashed passwords in a real app!
                             "role VARCHAR(20) DEFAULT 'user'" +
                             ")");
                System.out.println("Table 'users' checked/created.");

                // Customers table
                stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                             "account_number VARCHAR(20) PRIMARY KEY," +
                             "name VARCHAR(100) NOT NULL," +
                             "address VARCHAR(255)," +
                             "telephone_number VARCHAR(15)," +
                             "units_consumed DOUBLE DEFAULT 0.0" +
                             ")");
                System.out.println("Table 'customers' checked/created.");

                // Items table
                stmt.execute("CREATE TABLE IF NOT EXISTS items (" +
                             "item_id VARCHAR(20) PRIMARY KEY," +
                             "item_name VARCHAR(100) NOT NULL," +
                             "unit_price DOUBLE NOT NULL," +
                             "quantity INT NOT NULL" +
                             ")");
                System.out.println("Table 'items' checked/created.");

                // Insert default users (only if they don't exist)
                stmt.execute("INSERT INTO users (username, password, role) SELECT 'admin', 'adminpass', 'admin' WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin')");
                stmt.execute("INSERT INTO users (username, password, role) SELECT 'user', 'userpass', 'user' WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'user')");
                System.out.println("Default users 'admin' and 'user' checked/inserted.");

                // Insert sample customers
                stmt.execute("INSERT INTO customers (account_number, name, address, telephone_number, units_consumed) SELECT 'C001', 'Alice Smith', '123 Main St, Colombo', '0771234567', 150.50 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE account_number = 'C001')");
                stmt.execute("INSERT INTO customers (account_number, name, address, telephone_number, units_consumed) SELECT 'C002', 'Bob Johnson', '456 Elm Ave, Kandy', '0719876543', 200.75 WHERE NOT EXISTS (SELECT 1 FROM customers WHERE account_number = 'C002')");
                System.out.println("Sample customers checked/inserted.");

                // Insert sample items
                stmt.execute("INSERT INTO items (item_id, item_name, unit_price, quantity) SELECT 'B001', 'Java Programming Basics', 1200.00, 50 WHERE NOT EXISTS (SELECT 1 FROM items WHERE item_id = 'B001')");
                stmt.execute("INSERT INTO items (item_id, item_name, unit_price, quantity) SELECT 'B002', 'SQL Database Design', 950.00, 30 WHERE NOT EXISTS (SELECT 1 FROM items WHERE item_id = 'B002')");
                System.out.println("Sample items checked/inserted.");

                System.out.println("Database schema initialized successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database schema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null); // Close resources after use
        }
    }
}