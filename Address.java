package com.demo;

public class Address {
    private int streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private Integer id;
    private String course_name;
    private int grade;
    private int student_id;

    // Constructor

    public Address(int streetNumber, String streetName, String city, String country, String province, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.province = province;
        this.postalCode = postalCode;
    }


    /* Getters */
    public int getStreetNumber() { return this.streetNumber; }
    public String getStreetName() { return this.streetName; }
    public String getCity() { return this.city; }
    public String getCountry() { return this.country; }
    public String getProvince() { return this.province; }
    public String getPostalCode() { return this.postalCode; }

    /* Setters */
    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setCity(String city) { this.city = city; }
    public void setCountry(String country) { this.country = country; }
    public void setProvince(String province) { this.province = province; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public String toString() {
        return "<ul>"
                + "<li>address= " + streetNumber + "</li>"
                + "<li>address= " + streetName + "</li>"
                + "<li>address= " + city + "</li>"
                + "<li>address= " + country + "</li>"
                + "<li>address= " + province + "</li>"
                + "<li>address= " + postalCode + "</li>";
    }
}
