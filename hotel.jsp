<%@ page import="com.demo.HotelService" %>
<%@ page import="com.demo.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Our Hotels</title>
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
<%
    ArrayList<Message> messages;

    // get any incoming messages from session attribute named messages
    // if nothing exists then messages is an empty arraylist
    if ((ArrayList<Message>) session.getAttribute("messages") == null) messages = new ArrayList<>();
        // else get that value
    else messages = (ArrayList<Message>) session.getAttribute("messages");

    String msgField = "";

    // create the object in the form of a stringified json
    for (Message m : messages) {
        msgField += "{\"type\":\"" + m.type + "\",\"value\":\"" + m.value.replaceAll("['\"]+", "") + "\"},";
    }

    // empty session messages
    session.setAttribute("messages", new ArrayList<Message>());

    HotelService hotelService = new HotelService();
    List<Hotel> hotels = null;
        try {
        hotels = hotelService.getHotels();
        } catch (Exception e) {
        e.printStackTrace();
        }
%>

<jsp:include page="navbar.jsp"/>

<input type="hidden" name="message" id="message" value='<%=msgField%>' >
<div class="container">
    <div class="row" id="row">
        <div class="col-md-12">
            <div class="card" id="card-container">
                <div class="card-body" id="card">
                    <% if (hotels == null || hotels.isEmpty()) { %>
                    <h1 style="margin-top: 5rem;">No Hotels found!</h1>
                    <% } else { %>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>hotelid</th>
                                <th>name</th>
                                <th>email</th>
                                <th>phonenumber</th>
                                <th>category</th>
                                <th>hotelchainid</th>
                                <th>streetnumber</th>
                                <th>streetname</th>
                                <th>postalcode</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Hotel hotel : hotels) { %>
                            <tr>
                                <td><%= hotel.getHotelID() %></td>
                                <td><%= hotel.getName() %></td>
                                <td><%= hotel.getEmail() %></td>
                                <td><%= hotel.getPhoneNumber() %></td>
                                <td><%= hotel.getCategory() %></td>
                                <td><%= hotel.getHotelChainID() %></td>
                                <td><%= hotel.getStreetNumber() %></td>
                                <td><%= hotel.getStreetName() %></td>
                                <td><%= hotel.getPostalCode() %></td>
                                <form method="POST" action="#">
                                    <td>
                                        <input type="hidden" value="<%= hotel.getHotelID() %>" name="id" />
                                        <button style="all: unset; cursor: pointer;" type="submit"><i class="fa fa-trash"></i></button>
                                    </td>
                                </form>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/assets/js/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

<script>
    $(document).ready(function() {
        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "preventDuplicates": false
        };
        /* In order to access variables sent to ejs file to script you must Parse them to string */
        /* then to parse them back to JSON */
        let messages = document.getElementById("message").value;
        if (messages !== "") messages = JSON.parse("[" + messages.slice(0, -1) + "]");
        else messages = [];

        messages
            .forEach(({
                          type,
                          value
                      }) => {
                switch (type) {
                    case "error":
                        toastr.error(value)
                        break;
                    case "success":
                        toastr.success(value)
                        break;
                    case "warning":
                        toastr.warning(value)
                        break;
                    default:
                        toastr.info(value)
                        break;
                }
            });
    })
</script>


</body>
</html>
