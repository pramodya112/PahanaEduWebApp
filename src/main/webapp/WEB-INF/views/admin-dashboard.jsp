<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        nav { background-color: #333; padding: 10px; overflow: hidden; }
        nav a { float: left; display: block; color: white; text-align: center; padding: 14px 16px; text-decoration: none; }
        nav a:hover { background-color: #ddd; color: black; }
        .logout-link { float: right; }
        h1 { color: #333; }
        ul { list-style-type: none; padding: 0; }
        li { margin-bottom: 10px; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <nav>
        <a href="<%= request.getContextPath() %>/admin/dashboard">Admin Home</a>
        <a href="<%= request.getContextPath() %>/manageUsers">Manage Users</a>
        <a href="<%= request.getContextPath() %>/logout" class="logout-link">Logout</a>
    </nav>

    <h1>Admin Dashboard</h1>

    <%
        // Retrieve logged-in user and role from session
        com.pahanaedu.model.User loggedInUser = (com.pahanaedu.model.User) session.getAttribute("loggedInUser");
        String userRole = (String) session.getAttribute("userRole");
    %>

    <p>Hello, **<%= loggedInUser != null ? loggedInUser.getUsername() : "Admin" %>**!</p>
    <p>Your role: **<%= userRole %>**</p>

    <hr>

    <h2>Quick Access:</h2>
    <ul>
        <li><a href="<%= request.getContextPath() %>/manageUsers">Manage User Accounts</a> (View, Add, Edit, Delete Users)</li>
        <li><a href="#">View System Logs</a> (Placeholder)</li>
        <li><a href="#">Configure System Settings</a> (Placeholder)</li>
    </ul>

</body>
</html>