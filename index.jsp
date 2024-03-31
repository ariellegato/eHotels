<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Hotel Booking</title>

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
<div class="container">
    <h1>Welcome to Hotel Booking</h1>
    <h2>Please select a category:</h2>
    <!-- Link to booking.jsp -->
    <a href="booking.jsp" class="button">Booking</a>
    <!-- Link to room.jsp -->
    <a href="room.jsp" class="button">Room</a>
    <!-- Link to renting.jsp -->
    <a href="renting.jsp" class="button">Renting</a>
    <!-- Link to customer.jsp -->
    <a href="customer.jsp" class="button">Customer</a>
</div>
</body>
<body>
<div class="container">
    <h2>Select what type of user you are:</h2>
    <!-- Link to employee.jsp -->
    <a href="employee.jsp" class="button">Employee</a>
    <!-- Link to customer.jsp -->
    <a href="customer.jsp" class="button">Customer</a>
</div>
</body>
</html>

//to modify