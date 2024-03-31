package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelChainService {
    /**
     * Method to get all Hotel chains from the database
     *
     * @return List of Hotel chains from database
     * @throws Exception when trying to connect to database
     */
    public List<HotelChain> getHotelChains() throws Exception {

        // sql query
        String sql = "SELECT * FROM HotelChain";
        // database connection object
        ConnectionDB db = new ConnectionDB();

        // data structure to keep all hotelChains retrieved from database
        List<HotelChain> hotelChains = new ArrayList<>();

        // try to connect to database, catch any exceptions
        try (Connection con = db.getConnection()) {
            // prepare the statement
            PreparedStatement stmt = con.prepareStatement(sql);

            // get the results from executing the query
            ResultSet rs = stmt.executeQuery();

            // iterate through the result set
            while (rs.next()) {
                // create new hotelchain object
                HotelChain hotelChain = new HotelChain(
                        rs.getInt("hotelChainID"),
                        rs.getString("name"),
                        rs.getInt("phoneNumber"),
                        rs.getInt("streetNumber"),
                        rs.getString("postalCode"),
                        rs.getString("email"),
                        rs.getString("streetName")
                );

                // append hotelChain in hotelChains list
                hotelChains.add(hotelChain);
            }

            //close the result set
            rs.close();
            // close the statement
            stmt.close();
            con.close();
            db.close();

            // return the hotel chains retrieved from database
            return hotelChains;
        } catch (Exception e) {
            // throw any errors occurred
            throw new Exception(e.getMessage());
        }
    }


    /**
     * Method to update a hotel chain
     *
     * @param hotelChain customer to be updated
     * @return string returned that states if the customer updated or not
     * @throws Exception when trying to connect to database
     */
    public String updateHotelChain(HotelChain hotelChain) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "UPDATE HotelChain SET name=?, email=?, phoneNumber=?, streetNumber=?, streetName=?, postalCode=? WHERE hotelChainID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hotelChain.getHotelChainName());
            stmt.setString(2, hotelChain.getHotelChainEmail());
            stmt.setInt(3, hotelChain.getPhoneNumber());
            stmt.setString(4, hotelChain.getStreetName());
            stmt.setString(5, hotelChain.getStreetName());
            stmt.setString(6, hotelChain.getPostalCode());
            stmt.setInt(7, hotelChain.getHotelChainID());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while updating hotel chain: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Hotel chain successfully updated!";
        }

        return message;
    }

    /**
     * Method to delete by ID a hotel chain
     *
     * @param hotelChainID id of hotel chain to be deleted from database
     * @return string returned that states if the grade deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteHotelChain(Integer hotelChainID) throws Exception {
        Connection con = null;
        String message = "";

        // sql query
        String sql = "DELETE FROM HotelChain WHERE hotelChainID=?";

        // database connection object
        ConnectionDB db = new ConnectionDB();

        // try connect to database, catch any exceptions
        try {
            con = db.getConnection();
            // prepare statement
            PreparedStatement stmt = con.prepareStatement(sql);
            // set every ? of statement
            stmt.setInt(1, hotelChainID);
            // execute the query
            stmt.executeUpdate();
            // close the statement
            stmt.close();

        } catch (Exception e) {
            message = "Error while delete hotel chain: " + e.getMessage();
        } finally {
            if (con != null) con.close();
            if (message.equals("")) message = "Hotel Chain successfully deleted!";
        }

        return message;
    }
}
