package com.pahanaedu.model;

public class User {
    private int id;
    private String username;
    private String password; // In a real app, never store plain passwords. Use hashing (e.g., bcrypt)!
    private String role; // e.g., "admin", "user"

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor without ID, for new users
    public User(String username, String password, String role) {
        this(-1, username, password, role); // Default ID to -1 or 0 for new users
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}