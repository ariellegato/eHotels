package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressService {
    /**
     * Method to get all addresses from the database
     *
     * @return List of addresses from database
     * @throws Exception when trying to connect to database
     */
    public List<Address> getAddresses() throws Exception {

        // sql query
        String sql = "SELECT * FROM Address";
        // database connection object
        ConnectionDB db = new ConnectionDB();

        // data structure to keep all addresses retrieved from database
        List<Address> addresses = new ArrayList<>();

        // try connect to database, catch any exceptions
        try (Connection con = db.getConnection()) {
            // prepare the statement
            PreparedStatement stmt = con.prepareStatement(sql);

            // get the results from executing the query
            ResultSet rs = stmt.executeQuery();


            // iterate through the result set
            while (rs.next()) {
                // create new grade object
                Address address = new Address(
                        rs.getInt("streetNumber"),
                        rs.getString("streetName"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("province"),
                        rs.getString("postalCode")
                );

                // append address in addresses list
                addresses.add(address);
            }

            //close the result set
            rs.close();
            // close the statement
            stmt.close();
            con.close();
            db.close();

            // return the grades retrieved from database
            return addresses;
        } catch (Exception e) {
            // throw any errors occurred
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create an address in the database
     *
     * @param address address to be created
     * @return string returned that states if the address created or not
     * @throws Exception when trying to connect to database
     */
    public String createAddress(Address address) throws Exception {
        String message = "";
        Connection con = null;

        // connection object
        ConnectionDB db = new ConnectionDB();
        //System.out.println(address.getStreetNumber());
        //System.out.println(address.getStreetName());
        //System.out.println(address.getPostalCode());

        // sql query
        String insertStudentQuery = "INSERT INTO Address (streetNumber, streetName, city, country, province, postalCode) VALUES (?, ?, ?, ?, ?, ?);";


        // try connect to database, catch any exceptions
        try {
            con = db.getConnection(); //get Connection

            // prepare the statement
            PreparedStatement stmt = con.prepareStatement(insertStudentQuery);

            // set every ? of statement
            stmt.setInt(1, address.getStreetNumber());
            stmt.setString(2, address.getStreetName());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getCountry());
            stmt.setString(5, address.getProvince());
            stmt.setString(6, address.getPostalCode());

            // execute the query
            int output = stmt.executeUpdate();
            System.out.println(output);

            // close the statement
            stmt.close();
            // close the connection
            db.close();
        } catch (Exception e) {
            message = "Error while inserting address: " + e.getMessage();
        } finally {
            if (con != null) // if connection is still open, then close.
                con.close();
            if (message.equals("")) message = "Address successfully inserted!";

        }

        // return respective message
        return message;
    }

    /**
     * Method to delete an address
     *
     * @param streetNumber streetNumber of address to be deleted from database
     * @param streetName streetName of address to be deleted from database
     * @param postalCode postalCode of address to be deleted from database
     * @return string returned that states if the address deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteAddress(int streetNumber, String streetName, String postalCode) throws Exception {
        Connection con = null;
        String message = "";

        // sql query
        String sql = "DELETE FROM Address WHERE (streetNumber, streetName, postalCode) = (?,?,?);";

        // database connection object
        ConnectionDB db = new ConnectionDB();

        // try connect to database, catch any exceptions
        try {
            con = db.getConnection();

            // prepare statement
            PreparedStatement stmt = con.prepareStatement(sql);

            // set every ? of statement
            stmt.setInt(1, streetNumber);
            stmt.setString(2, streetName);
            stmt.setString(3, postalCode);

            // execute the query
            stmt.executeUpdate();

            // close the statement
            stmt.close();

        } catch (Exception e) {
            message = "Error while delete address: " + e.getMessage();
        } finally {
            if (con != null) con.close();
            if (message.equals("")) message = "Address successfully deleted!";
        }

        return message;
    }
}
