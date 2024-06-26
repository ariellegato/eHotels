<%@ page import="com.demo.AddressService" %>
<%@ page import="com.demo.Address" %>
<%@ page import="com.demo.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    // retrieve addresses from database
    AddressService addressService = new AddressService();
    List<Address> addresses = null;
    try {
        addresses = addressService.getAddresses();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title> Addresses Page </title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<input type="hidden" name="message" id="message" value='<%=msgField%>' >
<div class="container">
    <div class="row" id="row">
        <div class="col-md-12">
            <div class="card" id="card-container">
                <div class="card-body" id="card">
                    <% if (addresses.size() == 0) { %>
                    <h1 style="margin-top: 5rem;">No Addresses found!</h1>
                    <% } else { %>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Street Number</th>
                                <th>Street Name</th>
                                <th>City</th>
                                <th>Country</th>
                                <th>Province</th>
                                <th>Postal Code</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Address address : addresses) { %>
                            <tr>
                                <td><%= address.getStreetNumber() %></td>
                                <td><%= address.getStreetName() %></td>
                                <td><%= address.getCity() %></td>
                                <td><%= address.getCountry() %></td>
                                <td><%= address.getProvince() %></td>
                                <td><%= address.getPostalCode() %></td>

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

<script>
    function setModalFields(row) {
        document.getElementById("streetNumber").value = row.dataset.streetNumber;
        document.getElementById("streetName").value = row.dataset.streetName;
        document.getElementById("city").value = row.dataset.city;
        document.getElementById("country").value = row.dataset.country;
        document.getElementById("province").value = row.dataset.province;
        document.getElementById("postalCode").value = row.dataset.postalCode;

        //document.getElementById("modal-form").action = "update-address-controller.jsp";
        //document.getElementById("modal-form").method = "POST";
    }
</script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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
