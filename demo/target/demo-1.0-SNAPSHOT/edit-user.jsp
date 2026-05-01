<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar px-3" style="background-color:#e8531d">
    <span class="navbar-brand text-white fw-bold">User Management App</span>
    <a class="btn btn-light" href="list">Users</a>
</nav>

<div class="container mt-5" style="max-width: 500px;">
    <h2 class="text-center mb-4">Edit User</h2>

    <form action="edit-user" method="post">
        <input type="hidden" name="id" value="${user.id}">

        <div class="mb-3">
            <label class="form-label fw-semibold">Full Name</label>
            <input type="text" name="name" class="form-control" value="${user.name}" required>
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Email Address</label>
            <input type="email" name="email" class="form-control" value="${user.email}" required>
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Country</label>
            <input type="text" name="country" class="form-control" value="${user.country}" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Update User</button>
        <a href="list" class="btn btn-secondary w-100 mt-2">Cancel</a>
    </form>
</div>
</body>
</html>
