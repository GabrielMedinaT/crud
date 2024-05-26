package crud;

import java.sql.*;

public class Modelo {
    private Connection connection;
    public Modelo() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Tienda", "root", "root1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCustomer(String name, String address, String email) {
        try {
            String query = "INSERT INTO Customers (name, address, email) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCustomer(int customerId, String name, String address, String email) {
        try {
            String query = "UPDATE Customers SET name=?, address=?, email=? WHERE customer_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.setInt(4, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteCustomer(int customerId) {
        try {
            String query = "DELETE FROM Customers WHERE customer_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findCustomer(int customerId) {
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Customers WHERE customer_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
