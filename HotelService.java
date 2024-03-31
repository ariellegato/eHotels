package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelService {

    /**
     * Method to get all hotels from the database
     *
     * @return List of hotels from the database
     * @throws Exception when trying to connect to the database
     */
    public List<Hotel> getHotels() throws Exception {
        String sql = "SELECT * FROM hotel";
        ConnectionDB db = new ConnectionDB();
        List<Hotel> hotels = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getInt("hotelID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("phoneNumber"),
                        rs.getInt("category"),
                        rs.getInt("hotelChainID"),
                        rs.getInt("streetNumber"),
                        rs.getString("streetName"),
                        rs.getString("postalCode")
                );
                hotels.add(hotel);
            }

            rs.close();
            stmt.close();
            con.close();
            db.close();

            System.out.println("Number of hotels retrieved: " + hotels.size());


            return hotels;
        } catch (Exception e) {
            System.err.println("Error while fetching hotels: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create a hotel in the database
     *
     * @param hotel Hotel object to be created
     * @return Message indicating if the hotel was successfully created or not
     * @throws Exception when trying to connect to the database
     */
    public String createHotel(Hotel hotel) throws Exception {
        String message = "";
        Connection con = null;
        String insertHotelQuery = "INSERT INTO Hotel (hotelID, name, email, phoneNumber, category, hotelChainID, streetNumber, streetName, postalCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionDB db = new ConnectionDB();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertHotelQuery);

            stmt.setInt(1, hotel.getHotelID());
            stmt.setString(2, hotel.getName());
            stmt.setString(3, hotel.getEmail());
            stmt.setInt(4, hotel.getPhoneNumber());
            stmt.setInt(5, hotel.getCategory());
            stmt.setInt(6, hotel.getHotelChainID());
            stmt.setInt(7, hotel.getStreetNumber());
            stmt.setString(8, hotel.getStreetName());
            stmt.setString(9, hotel.getPostalCode());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
            db.close();
        } catch (Exception e) {
            message = "Error while inserting hotel: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Hotel successfully inserted!";
        }

        return message;
    }

    /**
     * Method to delete a hotel from the database
     *
     * @param hotelID Hotel ID of the hotel to be deleted
     * @return Message indicating if the hotel was successfully deleted or not
     * @throws Exception when trying to connect to the database
     */
    public String deleteHotel(int hotelID) throws Exception {
        Connection con = null;
        String message = "";
        String sql = "DELETE FROM Hotel WHERE hotelID = ?;";
        ConnectionDB db = new ConnectionDB();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, hotelID);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting hotel: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Hotel successfully deleted!";
        }

        return message;
    }

    /**
     * Method to View aggregated capacity of all rooms of a specific hotel
     *
     * @param hotel hotel for which to calculate capacity
     * @return aggregated capacity of hotel
     * @throws Exception when trying to connect to database
     */
    public String getHotelCapacity(String hotel) throws Exception {
        String message = "";
        Connection con = null;
        ConnectionDB db = new ConnectionDB();

        String sql = "SELECT total_capacity FROM HotelCapacity WHERE LOWER(hotel_name) = LOWER(?);";

        int capacity;

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hotel);
            ResultSet rs = stmt.executeQuery();
            capacity = rs.getInt("total_capacity");
            rs.close();
            stmt.close();
            con.close();

            message = "Aggregated capacity of " + hotel + ": " + capacity;

        } catch (Exception e) {
            //message = "Error while searching available rooms: " + e.getMessage();
            throw new Exception(e.getMessage());
        }

        return message;
    }

}
