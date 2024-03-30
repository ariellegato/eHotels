<%@ page import="com.HotelBooking.Booking" %>
<%@ page import="com.HotelBooking.BookingService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.SQLTimeoutException" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>

<%-- Other HTML and JSP code --%>

<%
  List<Booking> bookings = null;
  String errorMessage = null;

  try {
    bookings = BookingService.getBookings();
  } catch (SQLException e) {
    // Handle SQL exceptions
    errorMessage = "An SQL exception occurred: " + e.getMessage();
  } catch (ServletException | IOException e) {
    // Handle servlet or IO exceptions
    errorMessage = "A servlet or IO exception occurred: " + e.getMessage();
  } catch (Exception e) {
    // Catch any other generic exceptions
    errorMessage = "An unexpected exception occurred: " + e.getMessage();
  }

  if (errorMessage != null) {
    // Display error message
%>
<p>Error: <%= errorMessage %></p>
<%
} else {
  // Display bookings
  for (Booking booking : bookings) {
%>
<tr>
  <td><%= booking.getBookingID() %></td>
  <td><%= booking.getCheckIn() %></td>
  <td><%= booking.getCheckOut() %></td>
  <%-- Add more columns as needed --%>
  <td>
    <a href='updateBooking.jsp?bookingID=<%= booking.getBookingID() %>'>Update</a>
    <a href='deleteBooking.jsp?bookingID=<%= booking.getBookingID() %>'>Delete</a>
  </td>
</tr>
<%
    }
  }
%>


