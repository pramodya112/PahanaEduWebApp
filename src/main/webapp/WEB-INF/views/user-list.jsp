<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management - Admin</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 900px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h1 { color: #0056b3; text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #007bff; color: white; }
        .action-links a { margin-right: 10px; text-decoration: none; color: #007bff; }
        .action-links a:hover { text-decoration: underline; }
        .add-user-btn { display: inline-block; padding: 10px 15px; background-color: #28a745; color: white; text-decoration: none; border-radius: 5px; margin-bottom: 15px; }
        .add-user-btn:hover { background-color: #218838; }
        .back-link { display: block; margin-top: 20px; text-align: center; }
        .back-link a { color: #007bff; text-decoration: none; }
        .back-link a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Management</h1>
        <c:if test="${not empty sessionScope.successMessage}">
            <p style="color: green; text-align: center; font-weight: bold;"><c:out value="${sessionScope.successMessage}" /></p>
            <c:remove var="successMessage" scope="session"/> <%-- Remove message after displaying --%>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p style="color: red; text-align: center; font-weight: bold;"><c:out value="${sessionScope.errorMessage}" /></p>
            <c:remove var="errorMessage" scope="session"/> <%-- Remove message after displaying --%>
        </c:if>

        <a href="${pageContext.request.contextPath}/manageUsers?action=new" class="add-user-btn">Add New User</a>

        <c:if test="${not empty requestScope.userList}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${requestScope.userList}">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.role}"/></td>
                            <td class="action-links">
                                <a href="${pageContext.request.contextPath}/manageUsers?action=edit&id=<c:out value='${user.id}'/>">Edit</a>
                                <a href="${pageContext.request.contextPath}/manageUsers?action=delete&id=<c:out value='${user.id}'/>" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.userList}">
            <p>No users found in the system.</p>
        </c:if>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>