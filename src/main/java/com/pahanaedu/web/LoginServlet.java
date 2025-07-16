package com.pahanaedu.web;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBConnection; // To initialize DB schema

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet annotation maps this Servlet to the URL pattern "/login"
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // Recommended for Servlets

    private UserService userService; // Service layer dependency

    @Override
    public void init() throws ServletException {
        // This method is called once when the Servlet is initialized (e.g., when Tomcat starts)
        super.init();
        // Initialize DB schema here to ensure tables exist before any operations
        DBConnection.initializeDatabase();
        // Initialize UserDAO and UserService
        UserDAO userDAO = new UserDAO();
        this.userService = new UserService(userDAO);
        System.out.println("LoginServlet initialized. Database schema checked and UserService ready.");
    }

    /**
     * Handles HTTP GET requests.
     * When a user navigates to "/login" directly, this method serves the login page.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login.jsp page to display the login form.
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests (when the login form is submitted).
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get parameters from the HTML form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Authenticate user using the Service Layer
        User authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            // 3. If authentication successful:
            // Create a new session or get the existing one.
            HttpSession session = request.getSession();
            // Store user information in the session (e.g., username, role)
            session.setAttribute("loggedInUser", authenticatedUser);
            session.setAttribute("userRole", authenticatedUser.getRole());
            System.out.println("User " + authenticatedUser.getUsername() + " logged in with role: " + authenticatedUser.getRole());

            // Redirect to a protected dashboard page (e.g., customer dashboard)
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp"); // Will create dashboard.jsp next
        } else {
            // 4. If authentication failed:
            // Set an error message in the request scope
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            // Forward back to the login page to display the error message
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}