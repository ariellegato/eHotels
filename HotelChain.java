package com.demo;

public class HotelChain {

    private Integer hotelChainID;
    private String hotelChainName;
    private int phoneNumber;
    private int streetNumber;
    private String streetName;
    private String postalCode;
    private String hotelChainEmail;

    /**
     * Constructor to create HotelChain object with hotelChainID, hotelChainName, phoneNumber, streetNumber, streetName, postalCode, hotelChainEmail
     *
     * @param hotelChainID id of hotel chain
     * @param hotelChainName name of hotel chain
     * @param phoneNumber phone number of hotel chain
     * @param streetNumber street number of address
     * @param streetName street name of hotel chain
     * @param postalCode postal code of hotel chain
     * @param hotelChainEmail email address of hotel chain
     */
    public HotelChain(Integer hotelChainID, String hotelChainName,
                      int phoneNumber, int streetNumber, String postalCode,
                      String hotelChainEmail, String streetName) {

        this.hotelChainID = hotelChainID;
        this.hotelChainName = hotelChainName;
        this.phoneNumber = phoneNumber;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.hotelChainEmail = hotelChainEmail;
        this.streetName = streetName;
    }

    /* Getters */

    public Integer getHotelChainID() { return this.hotelChainID; }
    public String getHotelChainName() { return this.hotelChainName; }
    public int getPhoneNumber() { return this.phoneNumber;}
    public int getStreetNumber() { return this.streetNumber;}
    public String getStreetName() { return this.streetName;}
    public String getHotelChainEmail() { return this.hotelChainEmail;}
    public String getPostalCode() { return  this.postalCode;}


    /* Setters */
    public void setHotelChainID(int hotelChainID) { this.hotelChainID = hotelChainID; }
    public void setHotelChainName(String hotelChainName) {this.hotelChainName = hotelChainName; }
    public void setHotelChainEmail(String hotelChainEmail) {this.hotelChainEmail = hotelChainEmail; }
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber; }
    public void setStreetNumber(int streetNumber) {this.streetNumber = streetNumber; }
    public void setPostalCode(String postalCode) {this.postalCode = postalCode; }
    public void setStreetName(String streetName) {this.streetName = streetName; }

    @Override
    public String toString() {
        return "HotelChain{" +
                "hotelChainID=" + hotelChainID +
                ", hotelChainName='" + hotelChainName + '\'' +
                ", hotelChainEmail=" + hotelChainEmail +
                ", streetNumber=" + streetNumber +
                ", streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
