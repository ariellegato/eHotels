package com.demo;

public class Employee {
    private String name;
    private int sin;
    private String position;
    private int employeeID;
    private int managerID;
    private int streetNumber;
    private String streetName;
    private String postalCode;

    // Constructor

    public Employee(String name, int sin, String position, int employeeID, int managerID, int streetNumber, String streetName, String postalCode) {
        this.name = name;
        this.sin = sin;
        this.position = position;
        this.employeeID = employeeID;
        this.managerID = managerID;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Employee(String name, int sin, String position, int employeeID, int streetNumber, String streetName, String postalCode) {
        this.name = name;
        this.sin = sin;
        this.position = position;
        this.employeeID = employeeID;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }


    /* Getters */

    public String getName() { return this.name; }
    public int getSIN() { return this.sin; }
    public String getPosition() { return this.position; }
    public int getEmployeeID() { return this.employeeID; }
    public int getManagerID() { return this.managerID; }
    public int getStreetNumber() { return this.streetNumber; }
    public String getStreetName() { return this.streetName; }
    public String getPostalCode() { return this.postalCode; }

    /* Setters */

    public void setName(String name) { this.name = name; }
    public void setSIN(int sin) { this.sin = sin; }
    public void setPosition(String position) { this.position = position; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }
    public void setManagerID(int managerID) { this.managerID = managerID; }
    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Override
    public String toString() {
        return "<ul>"
                + "<li>hotel= " + name + "</li>"
                + "<li>hotel= " + sin + "</li>"
                + "<li>hotel= " + position + "</li>"
                + "<li>hotel= " + employeeID + "</li>"
                + "<li>hotel= " + managerID + "</li>"
                + "<li>hotel= " + streetNumber + "</li>"
                + "<li>hotel= " + streetName + "</li>"
                + "<li>hotel= " + postalCode + "</li>";
    }

}
