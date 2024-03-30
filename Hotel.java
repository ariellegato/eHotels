package com.demo;

public class Hotel {
    private int hotelID;
    private String name;
    private String email;
    private int phoneNumber;
    private int category;
    private int hotelChainID;
    private int streetNumber;
    private String streetName;
    private String postalCode;

    // Constructor

    public Hotel(int hotelID, String name, String email, int phoneNumber, int category, int hotelChainID, int streetNumber, String streetName, String postalCode) {
        this.hotelID = hotelID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.hotelChainID = hotelChainID;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }


    /* Getters */
    public int getHotelID() { return this.hotelID; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public int getPhoneNumber() { return this.phoneNumber; }
    public int getCategory() { return this.category; }
    public int getHotelChainID() { return this.hotelChainID; }
    public int getStreetNumber() { return this.streetNumber; }
    public String getStreetName() { return this.streetName; }
    public String getPostalCode() { return this.postalCode; }

    /* Setters */
    public void setHotelID(int hotelID) { this.hotelID = hotelID; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCategory(int category) { this.category = category; }
    public void setHotelChainID(int hotelChainID) { this.hotelChainID = hotelChainID; }
    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public String toString() {
        return "<ul>"
                + "<li>hotel= " + hotelID + "</li>"
                + "<li>hotel= " + name + "</li>"
                + "<li>hotel= " + email + "</li>"
                + "<li>hotel= " + phoneNumber + "</li>"
                + "<li>hotel= " + category + "</li>"
                + "<li>hotel= " + hotelChainID + "</li>"
                + "<li>hotel= " + streetNumber + "</li>"
                + "<li>hotel= " + streetName + "</li>"
                + "<li>hotel= " + postalCode + "</li>";
    }
}
