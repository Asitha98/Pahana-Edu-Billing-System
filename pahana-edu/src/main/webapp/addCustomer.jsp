<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>
    <form action="customer" method="post">
        <label>Account No:</label>
        <input type="text" name="accountNo" required><br><br>

        <label>Name:</label>
        <input type="text" name="name" required><br><br>

        <label>Address:</label>
        <textarea name="address"></textarea><br><br>

        <label>Phone:</label>
        <input type="text" name="phone"><br><br>

        <label>Units:</label>
        <input type="number" name="units" value="0"><br><br>

        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
