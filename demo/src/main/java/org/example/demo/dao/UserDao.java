package org.example.demo.dao;

import org.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private String URL      = "jdbc:mysql://localhost:3306/user_management";
    private String USER     = "root";
    private String PASSWORD = "mysql";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<User> getAllUsers() throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                ));
            }
        }
        return list;
    }

    public void addUser(User user) throws Exception {
        String sql = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.executeUpdate();
        }
    }

    public User getUserById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                );
            }
        }
        return null;
    }

    public void updateUser(User user) throws Exception {
        String sql = "UPDATE users SET name=?, email=?, country=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        }
    }

    public void deleteUser(int id) throws Exception {
        String deleteSql = "DELETE FROM users WHERE id = ?";
        String shiftSql = "UPDATE users SET id = id - 1 WHERE id > ?";

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement deletePs = con.prepareStatement(deleteSql);
                 PreparedStatement shiftPs = con.prepareStatement(shiftSql);
                 Statement st = con.createStatement()) {

                deletePs.setInt(1, id);
                deletePs.executeUpdate();

                shiftPs.setInt(1, id);
                shiftPs.executeUpdate();

                st.executeUpdate("ALTER TABLE users AUTO_INCREMENT = 1");
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
