package com.pahanaedu.dao;

import com.pahanaedu.model.User;
import com.pahanaedu.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Retrieves a user by their username.
     * @param username The username to search for.
     * @return User object if found, null otherwise.
     */
    public User getUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT id, username, password, role FROM users WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username); // Set the username parameter

            rs = pstmt.executeQuery(); // Execute the query

            if (rs.next()) { // If a record is found
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user by username: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(conn, pstmt, rs); // Always close resources
        }
        return user;
    }

    // Optional: Add a method to register a new user (for admin, or self-registration)
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if at least one row was inserted
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeResources(conn, pstmt, null);
        }
    }
}