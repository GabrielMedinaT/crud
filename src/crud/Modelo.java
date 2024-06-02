package crud;

import java.sql.*;

public class Modelo {

    private Connection connection;

    public Modelo() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "root1234");
            createDatabase();
            connect();
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Creación de la base de datos
    public void createDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS tienda");
        statement.close();
    }

    // Conexión a la base de datos
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tienda", "root", "root1234");
    }

    // Creación de tablas en la base de datos con sus respectivas columnas y con delete on cascade
    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Customers (customer_id INT PRIMARY KEY, name VARCHAR(255), address VARCHAR(255), email VARCHAR(255))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Orders (order_id INT PRIMARY KEY , order_date DATE, customer_id INT, FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Shipments (shipment_id INT PRIMARY KEY , shipment_date DATE, order_id INT, FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE)");
        statement.close();
    }

    // Métodos para Customers
    public void addCustomer(int customerId, String name, String address, String email) throws SQLException {
        String query = "INSERT INTO Customers (customer_id, name, address, email) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerId);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, email);
        ps.executeUpdate();
        ps.close();
    }

    public void updateCustomer(int customerId, String name, String address, String email) throws SQLException {
        String query = "UPDATE Customers SET name=?, address=?, email=? WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, email);
        ps.setInt(4, customerId);
        ps.executeUpdate();
        ps.close();
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerId);
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet findCustomer(int customerId) throws SQLException {
        String query = "SELECT * FROM Customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, customerId);
        return ps.executeQuery();
    }

    public ResultSet getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customers";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Métodos para Orders
public void addOrder(int orderId, Date orderDate, int customerId) throws SQLException {
    String query = "INSERT INTO Orders (order_id, order_date, customer_id) VALUES (?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setInt(1, orderId); // Corregido: setInt para el orderId
    ps.setDate(2, new java.sql.Date(orderDate.getTime()));
    ps.setInt(3, customerId);
    ps.executeUpdate();
    ps.close();
}


    public void updateOrder(int orderId, Date orderDate, int customerId) throws SQLException {
        String query = "UPDATE Orders SET order_date=?, customer_id=? WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(orderDate.getTime()));
        ps.setInt(2, customerId);
        ps.setInt(3, orderId);
        ps.executeUpdate();
        ps.close();
    }

    public void deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM Orders WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, orderId);
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet findOrder(int orderId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE order_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, orderId);
        return ps.executeQuery();
    }

    public ResultSet getAllOrders() throws SQLException {
        String query = "SELECT * FROM Orders";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Métodos para Shipments
    public void addShipment(Date shipmentDate, int orderId) throws SQLException {
        String query = "INSERT INTO Shipments (shipment_date, order_id) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(shipmentDate.getTime()));
        ps.setInt(2, orderId);
        ps.executeUpdate();
        ps.close();
    }

    public void updateShipment(int shipmentId, Date shipmentDate, int orderId) throws SQLException {
        String query = "UPDATE Shipments SET shipment_date=?, order_id=? WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(shipmentDate.getTime()));
        ps.setInt(2, orderId);
        ps.setInt(3, shipmentId);
        ps.executeUpdate();
        ps.close();
    }

    public void deleteShipment(int shipmentId) throws SQLException {
        String query = "DELETE FROM Shipments WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, shipmentId);
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet findShipment(int shipmentId) throws SQLException {
        String query = "SELECT * FROM Shipments WHERE shipment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, shipmentId);
        return ps.executeQuery();
    }

    public ResultSet getAllShipments() throws SQLException {
        String query = "SELECT * FROM Shipments";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Obtener todos los Customer IDs
    public ResultSet getAllCustomerIDs() throws SQLException {
        String query = "SELECT customer_id FROM Customers";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Obtener todos los Order IDs
    public ResultSet getAllOrderIDs() throws SQLException {
        String query = "SELECT order_id FROM Orders";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
