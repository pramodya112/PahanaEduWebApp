<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pahana Edu - Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #e9ecef; color: #333; margin: 20px; }
        .container { max-width: 960px; margin: 0 auto; background-color: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }
        h1, h2 { color: #007bff; }
        p { line-height: 1.6; }
        .user-info { font-weight: bold; }
        .logout-link { display: inline-block; margin-top: 20px; padding: 10px 15px; background-color: #dc3545; color: white; text-decoration: none; border-radius: 5px; }
        .logout-link:hover { background-color: #c82333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Pahana Edu Dashboard!</h1>

        <%-- Check if user is logged in (session attribute exists) --%>
        <%
            com.pahanaedu.model.User loggedInUser = (com.pahanaedu.model.User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                // If not logged in, redirect to login page
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return; // Stop further processing of this JSP
            }
        %>

        <p class="user-info">Logged in as: <%= loggedInUser.getUsername() %> (Role: <%= loggedInUser.getRole() %>)</p>
        <p>This is your main dashboard. From here, you can navigate to different sections of the application.</p>

        <h2>Navigation</h2>
        <ul>
            <li><a href="<%= request.getContextPath() %>/customers">Manage Customers (Coming Soon)</a></li>
            <li><a href="<%= request.getContextPath() %>/items">Manage Items (Coming Soon)</a></li>
            <li><a href="<%= request.getContextPath() %>/billing">Manage Billing (Coming Soon)</a></li>
            <li><a href="<%= request.getContextPath() %>/help.jsp">Help Section (Coming Soon)</a></li>
        </ul>

        <a href="logout" class="logout-link">Logout</a>
    </div>
</body>
</html>