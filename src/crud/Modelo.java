package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Connection connection;

    public Modelo() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Tienda", "root", "root1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para Customers
    public void addCustomer(String name, String address, String email) throws SQLException {
        String query = "INSERT INTO Customers (name, address, email) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, email);
        ps.executeUpdate();
    }

    public void updateCustomer(int customerId, String name, String address, String email) throws SQLException {
        String query = "UPDATE Customers SET name=?, address=?, email=? WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, email);
        ps.setInt(4, customerId);
        ps.executeUpdate();
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }

    public ResultSet findCustomer(int customerId) throws SQLException {
        String query = "SELECT * FROM Customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerId);
        return ps.executeQuery();
    }

    // Métodos para Orders
    public void addOrder(Date orderDate, int customerId) throws SQLException {
        String query = "INSERT INTO Orders (order_date, customer_id) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(orderDate.getTime()));
        ps.setInt(2, customerId);
        ps.executeUpdate();
    }

    public void updateOrder(int orderId, Date orderDate, int customerId) throws SQLException {
        String query = "UPDATE Orders SET order_date=?, customer_id=? WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(orderDate.getTime()));
        ps.setInt(2, customerId);
        ps.setInt(3, orderId);
        ps.executeUpdate();
    }

    public void deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM Orders WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, orderId);
        ps.executeUpdate();
    }

    public ResultSet findOrder(int orderId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, orderId);
        return ps.executeQuery();
    }

    // Métodos para Shipments
    public void addShipment(Date shipmentDate, int orderId) throws SQLException {
        String query = "INSERT INTO Shipments (shipment_date, order_id) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(shipmentDate.getTime()));
        ps.setInt(2, orderId);
        ps.executeUpdate();
    }

    public void updateShipment(int shipmentId, Date shipmentDate, int orderId) throws SQLException {
        String query = "UPDATE Shipments SET shipment_date=?, order_id=? WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(shipmentDate.getTime()));
        ps.setInt(2, orderId);
        ps.setInt(3, shipmentId);
        ps.executeUpdate();
    }

    public void deleteShipment(int shipmentId) throws SQLException {
        String query = "DELETE FROM Shipments WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, shipmentId);
        ps.executeUpdate();
    }

    public ResultSet findShipment(int shipmentId) throws SQLException {
        String query = "SELECT * FROM Shipments WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, shipmentId);
        return ps.executeQuery();
    }
}
