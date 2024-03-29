import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    /**
     * Method to get all customers from database
     *
     * @return list of all customers found in database
     * @throws Exception when trying to connect to database
     */
    public List<Customer> getCustomers() throws Exception {
        String sql = "SELECT * FROM Customer";
        ConnectionDB db = new ConnectionDB();
        List<Customer> customers = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customerID"),
                        rs.getString("name"),
                        rs.getDate("registrationDate"),
                        rs.getInt("streetNumber"),
                        rs.getString("streetName"),
                        rs.getString("postalCode")
                );
                customers.add(customer);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return customers;
    }

    /**
     * Method to create a customer in the database
     *
     * @param customer customer to be created
     * @return string returned that states if the customer created or not
     * @throws Exception when trying to connect to database
     */
    public String createCustomer(Customer customer) throws Exception {
        String message = "";
        Connection con = null;
        ConnectionDB db = new ConnectionDB();

        String insertCustomerQuery = "INSERT INTO Customer (name, customerID, registrationDate, streetNumber, streetName, postalCode) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(insertCustomerQuery);
            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getCustomerID());
            stmt.setDate(3, customer.getRegistrationDate());
            stmt.setInt(4, customer.getStreetNumber());
            stmt.setString(5, customer.getStreetName());
            stmt.setString(6, customer.getPostalCode());

            int output = stmt.executeUpdate();
            System.out.println(output);

            stmt.close();
        } catch (Exception e) {
            message = "Error while inserting customer: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Customer successfully inserted!";
        }

        return message;
    }

    /**
     * Method to update a customer
     *
     * @param customer customer to be updated
     * @return string returned that states if the customer updated or not
     * @throws Exception when trying to connect to database
     */
    public String updateCustomer(Customer customer) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "UPDATE Customer SET name=?, registrationDate=?, streetNumber=?, streetName=?, postalCode=? WHERE customerID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, customer.getName());
            stmt.setDate(2, customer.getRegistrationDate());
            stmt.setInt(3, customer.getStreetNumber());
            stmt.setString(4, customer.getStreetName());
            stmt.setString(5, customer.getPostalCode());
            stmt.setInt(6, customer.getCustomerID());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while updating customer: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Customer successfully updated!";
        }

        return message;
    }

    /**
     * Method to delete a customer
     *
     * @param customerID ID of the customer to be deleted
     * @return string returned that states if the customer deleted or not
     * @throws Exception when trying to connect to database
     */
    public String deleteCustomer(int customerID) throws Exception {
        Connection con = null;
        String message = "";
        ConnectionDB db = new ConnectionDB();

        String sql = "DELETE FROM Customer WHERE customerID=?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, customerID);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            message = "Error while deleting customer: " + e.getMessage();
        } finally {
            if (con != null)
                con.close();
            if (message.equals(""))
                message = "Customer successfully deleted!";
        }

        return message;
    }
}
