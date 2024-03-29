import java.sql.Date;

public class Booking {
    private int bookingID;
    private Date checkIn;
    private Date checkOut;
    private Date date;
    private boolean archived;
    private int customerID;
    private int roomNumber;
    private int hotelID;

    // Constructor
    public Booking(int bookingID, Date checkIn, Date checkOut, Date date, boolean archived, int customerID, int roomNumber, int hotelID) {
        this.bookingID = bookingID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.date = date;
        this.archived = archived;
        this.customerID = customerID;
        this.roomNumber = roomNumber;
        this.hotelID = hotelID;
    }

    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", date=" + date +
                ", archived=" + archived +
                ", customerID=" + customerID +
                ", roomNumber=" + roomNumber +
                ", hotelID=" + hotelID +
                '}';
    }
}
