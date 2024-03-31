<%--
  Created by IntelliJ IDEA.
  User: kayla
  Date: 2024-03-30
  Time: 8:45 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Employee Management</title>
    <style>
        .container {
            text-align: center;
            margin-top: 50px;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
<h1>Welcome to the Employee Management System</h1>

<p>Please select one of the following below:</p>

<div>
    <button><a href="customer.jsp" class="button">Customer</a></button>
    <button><a href="addresses.jsp" class="button">Address</a></button>
    <button><a href="hotelchain.jsp" class="button">HotelChain</a></button>
    <button><a href="renting.jsp" class="button">Renting</a></button>
    <button><a href="booking.jsp" class="button">Booking</a></button>

</div>

</body>
</html>
