package org.example.demo.servlet;

import org.example.demo.dao.UserDao;
import org.example.demo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class UserServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            List<User> users = dao.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/user-list.jsp").forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}