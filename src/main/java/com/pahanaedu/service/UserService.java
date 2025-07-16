package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Authenticates a user based on username and password.
     * @param username The username provided by the user.
     * @param password The password provided by the user (plain text for now).
     * @return The authenticated User object if successful, null otherwise.
     */
    public User authenticateUser(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            System.err.println("Authentication Error: Username or password cannot be empty.");
            return null;
        }

        User user = userDAO.getUserByUsername(username.trim()); // Get user from DB via DAO

        // In a real application, you would hash the provided password and compare it
        // with the stored hashed password (e.g., using BCrypt).
        // For this assignment, we're doing a direct comparison for simplicity.
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("User '" + username + "' authenticated successfully.");
            return user;
        } else {
            System.err.println("Authentication failed for user '" + username + "'. Invalid credentials.");
            return null;
        }
    }

    /**
     * Registers a new user.
     * @param username New username.
     * @param password Password for the new user.
     * @param role Role for the new user (e.g., "user").
     * @return true if registration is successful, false otherwise.
     */
    public boolean registerUser(String username, String password, String role) {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            System.err.println("Registration Error: Username or password cannot be empty.");
            return false;
        }
        if (userDAO.getUserByUsername(username.trim()) != null) {
            System.err.println("Registration Error: Username '" + username + "' already exists.");
            return false;
        }
        User newUser = new User(username.trim(), password.trim(), role.trim());
        // In a real app, hash the password before storing!
        return userDAO.addUser(newUser);
    }
}