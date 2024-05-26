package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class Vista extends JFrame {
    private JTextField txtCustomerId = new JTextField(10);
    private JTextField txtCustomerName = new JTextField(10);
    private JTextField txtCustomerAddress = new JTextField(10);
    private JTextField txtCustomerEmail = new JTextField(10);
    private JButton btnAddCustomer = new JButton("Añadir");
    private JButton btnUpdateCustomer = new JButton("Actualizar");
    private JButton btnDeleteCustomer = new JButton("Eliminar");
    private JButton btnFindCustomer = new JButton("Buscar");

    private JTextField txtOrderId = new JTextField(10);
    private JTextField txtOrderDate = new JTextField(10);
    private JTextField txtOrderCustomerId = new JTextField(10);
    private JButton btnAddOrder = new JButton("Añadir");
    private JButton btnUpdateOrder = new JButton("Actualizar");
    private JButton btnDeleteOrder = new JButton("Eliminar");
    private JButton btnFindOrder = new JButton("Buscar");

    private JTextField txtShipmentId = new JTextField(10);
    private JTextField txtShipmentDate = new JTextField(10);
    private JTextField txtShipmentOrderId = new JTextField(10);
    private JButton btnAddShipment = new JButton("Añadir");
    private JButton btnUpdateShipment = new JButton("Actualizar");
    private JButton btnDeleteShipment = new JButton("Eliminar");
    private JButton btnFindShipment = new JButton("Buscar");

    public Vista() {
        setTitle("Gestión de Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para Customers
        JPanel customerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        customerPanel.add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        customerPanel.add(txtCustomerId, gbc);

        // Customer Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        customerPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        customerPanel.add(txtCustomerName, gbc);

        // Customer Address
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        customerPanel.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        customerPanel.add(txtCustomerAddress, gbc);

        // Customer Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        customerPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        customerPanel.add(txtCustomerEmail, gbc);

        // Customer Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        customerPanel.add(btnAddCustomer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        customerPanel.add(btnUpdateCustomer, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        customerPanel.add(btnDeleteCustomer, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        customerPanel.add(btnFindCustomer, gbc);

        addValidation(txtCustomerName);
        addValidation(txtCustomerAddress);
        addValidation(txtCustomerEmail);

        tabbedPane.addTab("Customers", customerPanel);

        // Panel para Orders
        JPanel orderPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Order ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        orderPanel.add(new JLabel("Order ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        orderPanel.add(txtOrderId, gbc);

        // Order Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        orderPanel.add(new JLabel("Fecha de Orden:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        orderPanel.add(txtOrderDate, gbc);

        // Customer ID
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        orderPanel.add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        orderPanel.add(txtOrderCustomerId, gbc);

        // Order Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        orderPanel.add(btnAddOrder, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        orderPanel.add(btnUpdateOrder, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        orderPanel.add(btnDeleteOrder, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        orderPanel.add(btnFindOrder, gbc);

        tabbedPane.addTab("Orders", orderPanel);

        // Panel para Shipments
        JPanel shipmentPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Shipment ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        shipmentPanel.add(new JLabel("Shipment ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        shipmentPanel.add(txtShipmentId, gbc);

        // Shipment Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        shipmentPanel.add(new JLabel("Fecha de Envío:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        shipmentPanel.add(txtShipmentDate, gbc);

        // Order ID
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        shipmentPanel.add(new JLabel("Order ID:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        shipmentPanel.add(txtShipmentOrderId, gbc);

        // Shipment Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        shipmentPanel.add(btnAddShipment, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        shipmentPanel.add(btnUpdateShipment, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        shipmentPanel.add(btnDeleteShipment, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        shipmentPanel.add(btnFindShipment, gbc);

        tabbedPane.addTab("Shipments", shipmentPanel);

        add(tabbedPane);
    }

    private void addValidation(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textField.getText().length() >= 30) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!Pattern.matches("[a-zA-Z0-9 ]", String.valueOf(c))) {
                    e.consume();
                }
            }
        });
    }

    // Métodos para Customers
    public int getCustomerId() {
        try {
            return Integer.parseInt(txtCustomerId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getCustomerName() {
        return txtCustomerName.getText();
    }

    public String getCustomerAddress() {
        return txtCustomerAddress.getText();
    }

    public String getCustomerEmail() {
        return txtCustomerEmail.getText();
    }

    public boolean areCustomerFieldsValid() {
        return !getCustomerName().isEmpty() && !getCustomerAddress().isEmpty() && !getCustomerEmail().isEmpty();
    }

    public void addCustomerListener(ActionListener listenForAddButton) {
        btnAddCustomer.addActionListener(listenForAddButton);
    }

    public void updateCustomerListener(ActionListener listenForUpdateButton) {
        btnUpdateCustomer.addActionListener(listenForUpdateButton);
    }

    public void deleteCustomerListener(ActionListener listenForDeleteButton) {
        btnDeleteCustomer.addActionListener(listenForDeleteButton);
    }

    public void findCustomerListener(ActionListener listenForFindButton) {
        btnFindCustomer.addActionListener(listenForFindButton);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displayCustomerInfo(String name, String address, String email) {
        String message = String.format("Nombre: %s\nDirección: %s\nEmail: %s", name, address, email);
        JOptionPane.showMessageDialog(this, message, "Información del Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para Orders
    public int getOrderId() {
        try {
            return Integer.parseInt(txtOrderId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getOrderDate() {
        return txtOrderDate.getText();
    }

    public int getOrderCustomerId() {
        try {
            return Integer.parseInt(txtOrderCustomerId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public boolean areOrderFieldsValid() {
        return !getOrderDate().isEmpty() && getOrderCustomerId() != -1;
    }

    public void addOrderListener(ActionListener listenForAddButton) {
        btnAddOrder.addActionListener(listenForAddButton);
    }

    public void updateOrderListener(ActionListener listenForUpdateButton) {
        btnUpdateOrder.addActionListener(listenForUpdateButton);
    }

    public void deleteOrderListener(ActionListener listenForDeleteButton) {
        btnDeleteOrder.addActionListener(listenForDeleteButton);
    }

    public void findOrderListener(ActionListener listenForFindButton) {
        btnFindOrder.addActionListener(listenForFindButton);
    }

    public void displayOrderInfo(String orderDate, int customerId) {
        String message = String.format("Fecha de Orden: %s\nCustomer ID: %d", orderDate, customerId);
        JOptionPane.showMessageDialog(this, message, "Información de la Orden", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para Shipments
    public int getShipmentId() {
        try {
            return Integer.parseInt(txtShipmentId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getShipmentDate() {
        return txtShipmentDate.getText();
    }

    public int getShipmentOrderId() {
        try {
            return Integer.parseInt(txtShipmentOrderId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public boolean areShipmentFieldsValid() {
        return !getShipmentDate().isEmpty() && getShipmentOrderId() != -1;
    }

    public void addShipmentListener(ActionListener listenForAddButton) {
        btnAddShipment.addActionListener(listenForAddButton);
    }

    public void updateShipmentListener(ActionListener listenForUpdateButton) {
        btnUpdateShipment.addActionListener(listenForUpdateButton);
    }

    public void deleteShipmentListener(ActionListener listenForDeleteButton) {
        btnDeleteShipment.addActionListener(listenForDeleteButton);
    }

    public void findShipmentListener(ActionListener listenForFindButton) {
        btnFindShipment.addActionListener(listenForFindButton);
    }

    public void displayShipmentInfo(String shipmentDate, int orderId) {
        String message = String.format("Fecha de Envío: %s\nOrder ID: %d", shipmentDate, orderId);
        JOptionPane.showMessageDialog(this, message, "Información del Envío", JOptionPane.INFORMATION_MESSAGE);
    }
}
