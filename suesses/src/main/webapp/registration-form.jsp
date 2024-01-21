<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>

    <button onclick="location.href='user_login'">登入</button>

    <form action="/register_process" method="post">
        <label for="phone_number">Phone Number:</label>
        <input type="text" id="phone_number" name="phone_number" required><br>

        <label for="user_name">Username:</label>
        <input type="text" id="user_name" name="user_name" required><br>
        
        <label for="password_hash">Password:</label>
        <input type="password" id="password_hash" name="password_hash" required><br>

        <label for="confirm_password">Confirm Password:</label>
        <input type="password" id="confirm_password" name="confirm_password" required><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>
