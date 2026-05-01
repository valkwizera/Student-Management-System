package org.example.demo.servlet;

import org.example.demo.dao.UserDao;
import org.example.demo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            User user = dao.getUserById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/edit-user.jsp").forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setCountry(req.getParameter("country"));
        try {
            dao.updateUser(user);
            res.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}