import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RentingService {

    /**
     * Method to get all rentings from database
     *
     * @return list of all rentings found in database
     * @throws Exception when trying to connect to database
     */
    public List<Renting> getRentings() throws Exception {
        String sql = "SELECT * FROM Renting";
        ConnectionDB db = new ConnectionDB();
        List<Renting> rentings = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Renting renting = new Renting(
                        rs.getInt("rentingID"),
                        rs.getDate("checkIn"),
                        rs.getDate("checkOut"),
                        rs.getDate("date"),
                        rs.getBoolean("archived"),
                        rs.getInt("customerID"),
                        rs.getInt("roomNumber"),
                        rs.getInt("hotelID")
                );
                rentings.add(renting);
            }

            rs.close();
            stmt.close();
            con.close();
           // db.close();

            return rentings;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create a renting in the database
     *
     * @param renting renting to be created
     * @return string returned that states if the renting created or not
     * @throws Exception when trying to connect to database
     */
    public String createRenting(Renting renting) throws Exception {
        String message = "";
        Connection con = null;
        ConnectionDB db = new ConnectionDB();

        String insertRentingQuery = "INSERT INTO Renting (rentingID, checkIn, checkOut, date, archived, customerID, roomNumber, hotelID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertRentingQuery);
            stmt.setInt(1, renting.getRentingID());
            stmt.setDate(2, renting.getCheckIn());
            stmt.setDate(3, renting.getCheckOut());
            stmt.setDate(4, renting.getDate());
            stmt.setBoolean(5, renting.isArchived());
            stmt.setInt(6, renting.getCustomerID());
            stmt.setInt(7, renting.getRoomNumber());
            stmt.setInt(8, renting.getHotelID());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
           // db.close();
        } catch (Exception e) {
            message = "Error while inserting renting: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Renting successfully inserted!";
        }

        return message;
    }

    /**
     * Method to update a renting
     *
     * @param renting renting to be updated
     * @return string returned that states if the renting updated or not
     * @throws Exception when trying to connect to database
     */
    public String updateRenting(Renting renting) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "UPDATE Renting SET checkIn=?, checkOut=?, date=?, archived=?, customerID=?, roomNumber=?, hotelID=? WHERE rentingID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, renting.getCheckIn());
            stmt.setDate(2, renting.getCheckOut());
            stmt.setDate(3, renting.getDate());
            stmt.setBoolean(4, renting.isArchived());
            stmt.setInt(5, renting.getCustomerID());
            stmt.setInt(6, renting.getRoomNumber());
            stmt.setInt(7, renting.getHotelID());
            stmt.setInt(8, renting.getRentingID());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while updating renting: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Renting successfully updated!";
        }

        return message;
    }

    /**
     * Method to delete a renting
     *
     * @param rentingID ID of the renting to be deleted
     * @return string returned that states if the renting deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteRenting(int rentingID) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "DELETE FROM Renting WHERE rentingID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, rentingID);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting renting: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Renting successfully deleted!";
        }

        return message;
    }
}
