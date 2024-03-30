<%--
  Created by IntelliJ IDEA.
  User: esosa
  Date: 2024-03-30
  Time: 2:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.HotelBooking.Renting" %>
<%@ page import="com.HotelBooking.RentingService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>

<%
    RentingService rentingService = new RentingService();
    List<Renting> rentings = null;
    String message = null;

    // Get all rentings
    try {
        rentings = rentingService.getRentings();
    } catch (SQLException | IOException e) {
        message = "An error occurred while fetching rentings: " + e.getMessage();
    } catch (Exception e) {
        message = "An unexpected error occurred: " + e.getMessage();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rentings Service</title>
</head>
<body>
<h1>Rentings Service</h1>

<% if (message != null) { %>
<p><%= message %></p>
<% } %>

<table border="1">
    <thead>
    <tr>
        <th>Renting ID</th>
        <th>Check-In Date</th>
        <th>Check-Out Date</th>
        <!-- Add more columns as needed -->
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% if (rentings != null) {
        for (Renting renting : rentings) { %>
    <tr>
        <td><%= renting.getRentingID() %></td>
        <td><%= renting.getCheckIn() %></td>
        <td><%= renting.getCheckOut() %></td>
        <!-- Add more columns as needed -->
        <td>
            <!-- Link to delete renting -->
            <form action="deleteRenting.jsp" method="post">
                <input type="hidden" name="rentingID" value="<%= renting.getRentingID() %>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <%  }
    } %>
    </tbody>
</table>

<hr>

<h2>Add Renting</h2>
<form action="createRenting.jsp" method="post">
    <!-- Input fields for creating a new renting -->
    <label for="checkIn">Check-In Date:</label>
    <input type="date" id="checkIn" name="checkIn" required><br>

    <label for="checkOut">Check-Out Date:</label>
    <input type="date" id="checkOut" name="checkOut" required><br>

    <!-- Add more input fields for other columns as needed -->

    <input type="submit" value="Add Renting">
</form>
</body>
</html>

