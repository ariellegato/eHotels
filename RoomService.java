import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    /**
     * Method to get all rooms from database
     *
     * @return list of all rooms found in database
     * @throws Exception when trying to connect to database
     */
    public List<Room> getRooms() throws Exception {
        String sql = "SELECT * FROM Room";
        ConnectionDB db = new ConnectionDB();
        List<Room> rooms = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("roomNumber"),
                        rs.getDouble("price"),
                        rs.getString("amenities"),
                        rs.getInt("capacity"),
                        rs.getBoolean("seaView"),
                        rs.getBoolean("mountainView"),
                        rs.getBoolean("extendable"),
                        rs.getString("problems"),
                        rs.getInt("hotelID")
                );
                rooms.add(room);
            }

            rs.close();
            stmt.close();
            con.close();
            db.close();

            return rooms;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create a room in the database
     *
     * @param room room to be created
     * @return string returned that states if the room created or not
     * @throws Exception when trying to connect to database
     */
    public String createRoom(Room room) throws Exception {
        String message = "";
        Connection con = null;
        ConnectionDB db = new ConnectionDB();

        String insertRoomQuery = "INSERT INTO Room (roomNumber, price, amenities, capacity, seaView, mountainView, extendable, problems, hotelID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertRoomQuery);
            stmt.setInt(1, room.getRoomNumber());
            stmt.setDouble(2, room.getPrice());
            stmt.setString(3, room.getAmenities());
            stmt.setInt(4, room.getCapacity());
            stmt.setBoolean(5, room.isSeaView());
            stmt.setBoolean(6, room.isMountainView());
            stmt.setBoolean(7, room.isExtendable());
            stmt.setString(8, room.getProblems());
            stmt.setInt(9, room.getHotelID());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
            db.close();
        } catch (Exception e) {
            message = "Error while inserting room: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Room successfully inserted!";
        }

        return message;
    }

    /**
     * Method to update a room
     *
     * @param room room to be updated
     * @return string returned that states if the room updated or not
     * @throws Exception when trying to connect to database
     */
    public String updateRoom(Room room) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "UPDATE Room SET price=?, amenities=?, capacity=?, seaView=?, mountainView=?, extendable=?, problems=?, hotelID=? WHERE roomNumber=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, room.getPrice());
            stmt.setString(2, room.getAmenities());
            stmt.setInt(3, room.getCapacity());
            stmt.setBoolean(4, room.isSeaView());
            stmt.setBoolean(5, room.isMountainView());
            stmt.setBoolean(6, room.isExtendable());
            stmt.setString(7, room.getProblems());
            stmt.setInt(8, room.getHotelID());
            stmt.setInt(9, room.getRoomNumber());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while updating room: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Room successfully updated!";
        }

        return message;
    }

    /**
     * Method to delete a room
     *
     * @param roomNumber ID of the room to be deleted
     * @return string returned that states if the room deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteRoom(int roomNumber) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "DELETE FROM Room WHERE roomNumber=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, roomNumber);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting room: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Room successfully deleted!";
        }

        return message;
    }
}
