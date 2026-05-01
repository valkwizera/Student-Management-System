<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.demo.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<nav class="navbar px-3" style="background-color:#e8531d">
    <span class="navbar-brand text-white fw-bold">User Management App</span>
    <a class="btn btn-light" href="add-user">Add User</a>
</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2 class="mb-0">Users</h2>
        <a href="add-user" class="btn btn-success">New User</a>
    </div>

    <table class="table table-bordered table-striped align-middle">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getCountry() %></td>
            <td>
                <a href="edit-user?id=<%= user.getId() %>" class="btn btn-sm btn-primary">Edit</a>
                <a href="delete-user?id=<%= user.getId() %>" class="btn btn-sm btn-danger"
                   onclick="return confirm('Delete this user?');">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No users found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
