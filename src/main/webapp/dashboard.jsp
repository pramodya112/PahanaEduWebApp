<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 800px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h1 { color: #0056b3; }
        h2 { color: #007bff; }
        p { line-height: 1.6; }
        .logout-link { float: right; margin-top: 10px; }
        .logout-link a { color: #dc3545; text-decoration: none; font-weight: bold; }
        .logout-link a:hover { text-decoration: underline; }
        .role-content { margin-top: 20px; padding: 15px; border: 1px solid #ddd; border-radius: 5px; background-color: #e9ecef; }
    </style>
</head>
<body>
    <div class="container">
        <div class="logout-link">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <h1>Welcome to Your Dashboard!</h1>

        <%-- Check if user is logged in --%>
        <c:if test="${empty sessionScope.loggedInUser}">
            <p style="color: red;">You are not logged in. Please <a href="${pageContext.request.contextPath}/login">login here</a>.</p>
        </c:if>

        <c:if test="${not empty sessionScope.loggedInUser}">
            <h2>Hello, ${sessionScope.loggedInUser.username}!</h2>
            <p>Your role: <strong>${sessionScope.userRole}</strong></p>

            <div class="role-content">
                <c:choose>
                    <c:when test="${sessionScope.userRole == 'admin'}">
                        <h3>Admin Privileges</h3>
                        <p>As an **administrator**, you have full access to manage users, customers, and inventory items.</p>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/manageUsers">Manage Users</a> (Coming Soon!)</li>
                            <li><a href="${pageContext.request.contextPath}/manageCustomers">Manage Customers</a> (Coming Soon!)</li>
                            <li><a href="${pageContext.request.contextPath}/manageItems">Manage Inventory Items</a> (Coming Soon!)</li>
                        </ul>
                    </c:when>
                    <c:when test="${sessionScope.userRole == 'user'}">
                        <h3>User View</h3>
                        <p>As a **regular user**, you can view your specific information and perform general tasks.</p>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/viewProfile">View Profile</a> (Coming Soon!)</li>
                            <li><a href="${pageContext.request.contextPath}/viewInvoices">View Invoices</a> (Coming Soon!)</li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <h3>Unknown Role</h3>
                        <p>Your role is not recognized. Please contact support.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</body>
</html>