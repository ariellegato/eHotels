package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    /**
     * Method to get all employees from the database
     *
     * @return List of employees from the database
     * @throws Exception when trying to connect to the database
     */
    public List<Employee> getEmployees() throws Exception {
        String sql = "SELECT * FROM Employee";
        ConnectionDB db = new ConnectionDB();
        List<Employee> employees = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("name"),
                        rs.getInt("sin"),
                        rs.getString("position"),
                        rs.getInt("employeeID"),
                        rs.getInt("managerID"),
                        rs.getInt("streetNumber"),
                        rs.getString("streetName"),
                        rs.getString("postalCode")
                );
                employees.add(employee);
            }

            rs.close();
            stmt.close();
            con.close();
            db.close();

            return employees;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Method to create an employee in the database
     *
     * @param employee Employee object to be created
     * @return Message indicating if the employee was successfully created or not
     * @throws Exception when trying to connect to the database
     */
    public String createEmployee(Employee employee) throws Exception {
        String message = "";
        Connection con = null;
        String insertEmployeeQuery = "INSERT INTO Employee (name, sin, position, employeeID, managerID, streetNumber, streetName, postalCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionDB db = new ConnectionDB();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertEmployeeQuery);

            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getSIN());
            stmt.setString(3, employee.getPosition());
            stmt.setInt(4, employee.getEmployeeID());
            stmt.setInt(5, employee.getManagerID());
            stmt.setInt(6, employee.getStreetNumber());
            stmt.setString(7, employee.getStreetName());
            stmt.setString(8, employee.getPostalCode());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
            db.close();
        } catch (Exception e) {
            message = "Error while inserting employee: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Employee successfully inserted!";
        }

        return message;
    }

    /**
     * Method to delete an employee from the database
     *
     * @param employeeID Employee ID of the employee to be deleted
     * @return Message indicating if the employee was successfully deleted or not
     * @throws Exception when trying to connect to the database
     */
    public String deleteEmployee(int employeeID) throws Exception {
        Connection con = null;
        String message = "";
        String sql = "DELETE FROM Employee WHERE employeeID = ?;";
        ConnectionDB db = new ConnectionDB();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting employee: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Employee successfully deleted!";
        }

        return message;
    }
}

