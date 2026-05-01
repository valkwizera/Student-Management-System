package org.example.demo.servlet;

import org.example.demo.dao.UserDao;
import org.example.demo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add-user.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setCountry(req.getParameter("country"));
        try {
            dao.addUser(user);
            res.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}