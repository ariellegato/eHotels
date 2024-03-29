public class Room {
    private int roomNumber;
    private double price;
    private String amenities;
    private int capacity;
    private boolean seaView;
    private boolean mountainView;
    private boolean extendable;
    private String problems;
    private int hotelID;

    // Constructor
    public Room(int roomNumber, double price, String amenities, int capacity, boolean seaView, boolean mountainView, boolean extendable, String problems, int hotelID) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.amenities = amenities;
        this.capacity = capacity;
        this.seaView = seaView;
        this.mountainView = mountainView;
        this.extendable = extendable;
        this.problems = problems;
        this.hotelID = hotelID;
    }

    // Getters and Setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isSeaView() {
        return seaView;
    }

    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public boolean isMountainView() {
        return mountainView;
    }

    public void setMountainView(boolean mountainView) {
        this.mountainView = mountainView;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
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
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", price=" + price +
                ", amenities='" + amenities + '\'' +
                ", capacity=" + capacity +
                ", seaView=" + seaView +
                ", mountainView=" + mountainView +
                ", extendable=" + extendable +
                ", problems='" + problems + '\'' +
                ", hotelID=" + hotelID +
                '}';
    }
}
