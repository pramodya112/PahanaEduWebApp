<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${user != null ? 'Edit User' : 'Add New User'}" /></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 500px; margin: auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h1 { color: #0056b3; text-align: center; margin-bottom: 25px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="password"], select {
            width: calc(100% - 22px); /* Account for padding and border */
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box; /* Include padding and border in the element's total width and height */
        }
        .form-actions { text-align: right; margin-top: 25px; }
        .btn { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-primary:hover { background-color: #0056b3; }
        .btn-cancel { background-color: #6c757d; color: white; margin-right: 10px; }
        .btn-cancel:hover { background-color: #5a6268; }
        .error-message { color: red; text-align: center; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <h1><c:out value="${user != null ? 'Edit User' : 'Add New User'}" /></h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message"><c:out value="${errorMessage}" /></p>
        </c:if>

        <form action="${pageContext.request.contextPath}/manageUsers" method="post">
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}'/>">
            </c:if>
            <input type="hidden" name="action" value="saveUser">

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<c:out value='${user.username}'/>" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" <c:if test="${user == null}">required</c:if>>
                <c:if test="${user != null}">
                    <small>Leave blank to keep current password.</small>
                </c:if>
            </div>

            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="admin" <c:if test="${user.role == 'admin'}">selected</c:if>>Admin</option>
                    <option value="user" <c:if test="${user.role == 'user'}">selected</c:if>>User</option>
                    </select>
            </div>

            <div class="form-actions">
                <button type="button" class="btn btn-cancel" onclick="window.location.href='${pageContext.request.contextPath}/manageUsers'">Cancel</button>
                <button type="submit" class="btn btn-primary">Save User</button>
            </div>
        </form>
    </div>
</body>
</html>