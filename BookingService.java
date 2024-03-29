import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    /**
     * Method to get all bookings from database
     *
     * @return list of all bookings found in database
     * @throws Exception when trying to connect to database
     */
    public List<Booking> getBookings() throws Exception {
        String sql = "SELECT * FROM Booking";
        ConnectionDB db = new ConnectionDB();
        List<Booking> bookings = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("bookingID"),
                        rs.getDate("checkIn"),
                        rs.getDate("checkOut"),
                        rs.getDate("date"),
                        rs.getBoolean("archived"),
                        rs.getInt("customerID"),
                        rs.getInt("roomNumber"),
                        rs.getInt("hotelID")
                );
                bookings.add(booking);
            }

            rs.close();
            stmt.close();
            con.close();
            //db.close();

            return bookings;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create a booking in the database
     *
     * @param booking booking to be created
     * @return string returned that states if the booking created or not
     * @throws Exception when trying to connect to database
     */
    public String createBooking(Booking booking) throws Exception {
        String message = "";
        Connection con = null;
        ConnectionDB db = new ConnectionDB();

        String insertBookingQuery = "INSERT INTO Booking (bookingID, checkIn, checkOut, date, archived, customerID, roomNumber, hotelID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertBookingQuery);
            stmt.setInt(1, booking.getBookingID());
            stmt.setDate(2, booking.getCheckIn());
            stmt.setDate(3, booking.getCheckOut());
            stmt.setDate(4, booking.getDate());
            stmt.setBoolean(5, booking.isArchived());
            stmt.setInt(6, booking.getCustomerID());
            stmt.setInt(7, booking.getRoomNumber());
            stmt.setInt(8, booking.getHotelID());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
            //db.close();
        } catch (Exception e) {
            message = "Error while inserting booking: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Booking successfully inserted!";
        }

        return message;
    }

    /**
     * Method to update a booking
     *
     * @param booking booking to be updated
     * @return string returned that states if the booking updated or not
     * @throws Exception when trying to connect to database
     */
    public String updateBooking(Booking booking) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "UPDATE Booking SET checkIn=?, checkOut=?, date=?, archived=?, customerID=?, roomNumber=?, hotelID=? WHERE bookingID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, booking.getCheckIn());
            stmt.setDate(2, booking.getCheckOut());
            stmt.setDate(3, booking.getDate());
            stmt.setBoolean(4, booking.isArchived());
            stmt.setInt(5, booking.getCustomerID());
            stmt.setInt(6, booking.getRoomNumber());
            stmt.setInt(7, booking.getHotelID());
            stmt.setInt(8, booking.getBookingID());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while updating booking: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Booking successfully updated!";
        }

        return message;
    }

    /**
     * Method to delete a booking
     *
     * @param bookingID ID of the booking to be deleted
     * @return string returned that states if the booking deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteBooking(int bookingID) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "DELETE FROM Booking WHERE bookingID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting booking: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Booking successfully deleted!";
        }

        return message;
    }
}