package crud;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Vista extends JFrame {

    private Modelo model;
    private JTextField txtCustomerId = new JTextField(10);
    private JTextField txtCustomerName = new JTextField(10);
    private JTextField txtCustomerAddress = new JTextField(10);
    private JTextField txtCustomerEmail = new JTextField(10);
    private JButton btnAddCustomer = new JButton("Añadir");
    private JButton btnUpdateCustomer = new JButton("Actualizar");
    private JButton btnDeleteCustomer = new JButton("Eliminar");
    private JButton btnFindCustomer = new JButton("Buscar");
    private JButton btnShowAllCustomers = new JButton("Mostrar Todos");
    private JComboBox<Integer> cbOrderCustomerId = new JComboBox<>();
    private JComboBox<Integer> cbShipmentOrderId = new JComboBox<>();

    private JTextField txtOrderId = new JTextField(10);
    private UtilDateModel modelOrderDate = new UtilDateModel();
    private JDatePanelImpl datePanelOrderDate = new JDatePanelImpl(modelOrderDate, new Properties());
    private JDatePickerImpl datePickerOrderDate = new JDatePickerImpl(datePanelOrderDate, new DateLabelFormatter());
    private JButton btnAddOrder = new JButton("Añadir");
    private JButton btnUpdateOrder = new JButton("Actualizar");
    private JButton btnDeleteOrder = new JButton("Eliminar");
    private JButton btnFindOrder = new JButton("Buscar");
    private JButton btnShowAllOrders = new JButton("Mostrar Todos");

    private JTextField txtShipmentId = new JTextField(10);
    private UtilDateModel modelShipmentDate = new UtilDateModel();
    private JDatePanelImpl datePanelShipmentDate = new JDatePanelImpl(modelShipmentDate, new Properties());
    private JDatePickerImpl datePickerShipmentDate = new JDatePickerImpl(datePanelShipmentDate, new DateLabelFormatter());
    private JButton btnAddShipment = new JButton("Añadir");
    private JButton btnUpdateShipment = new JButton("Actualizar");
    private JButton btnDeleteShipment = new JButton("Eliminar");
    private JButton btnFindShipment = new JButton("Buscar");
    private JButton btnShowAllShipments = new JButton("Mostrar Todos");

    public Vista() {
        setTitle("Gestión de Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();
        model = new Modelo();

        // Llenar los ComboBoxes con IDs existentes
        fillCustomerIDs(cbOrderCustomerId);
        fillOrderIDs(cbShipmentOrderId);

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

        gbc.gridx = 5;
        gbc.gridy = 3;
        customerPanel.add(btnShowAllCustomers, gbc);

        addValidation(txtCustomerId, "customerId");
        addValidation(txtCustomerName, "name");
        addValidation(txtCustomerAddress, "address");
        addValidation(txtCustomerEmail, "email");

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

        // Customer ID
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        orderPanel.add(new JLabel("ID Cliente:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        orderPanel.add(cbOrderCustomerId, gbc);

        // Order Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        orderPanel.add(new JLabel("Fecha de Orden:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        orderPanel.add(datePickerOrderDate, gbc);

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

        gbc.gridx = 4;
        gbc.gridy = 2;
        orderPanel.add(btnShowAllOrders, gbc);

        addValidation(txtOrderId, "orderId");

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

        // Order ID
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        shipmentPanel.add(new JLabel("Order ID:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        shipmentPanel.add(cbShipmentOrderId, gbc);

        // Shipment Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        shipmentPanel.add(new JLabel("Fecha de Envío:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        shipmentPanel.add(datePickerShipmentDate, gbc);

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

        gbc.gridx = 4;
        gbc.gridy = 2;
        shipmentPanel.add(btnShowAllShipments, gbc);

        addValidation(txtShipmentId, "shipmentId");

        tabbedPane.addTab("Shipments", shipmentPanel);

        add(tabbedPane);
    }

    private void fillCustomerIDs(JComboBox<Integer> comboBox) {
        try {
            ResultSet rs = model.getAllCustomerIDs();
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                comboBox.addItem(customerId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillOrderIDs(JComboBox<Integer> comboBox) {
        try {
            ResultSet rs = model.getAllOrderIDs();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                comboBox.addItem(orderId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateCustomerIDs() {
        cbOrderCustomerId.removeAllItems();
        fillCustomerIDs(cbOrderCustomerId);
    }

    public void updateOrderIDs() {
        cbShipmentOrderId.removeAllItems();
        fillOrderIDs(cbShipmentOrderId);
    }

    private void addValidation(JTextField textField, String type) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String charStr = String.valueOf(e.getKeyChar());
                String currentText = textField.getText();

                // Condición para customerId, orderId, shipmentId
                if (type.equals("customerId") || type.equals("orderId") || type.equals("shipmentId")) {
                    if (currentText.length() >= 4 || !Character.isDigit(e.getKeyChar())) {
                        e.consume();
                        return;
                    }
                }

                // Condición para longitud máxima de 30 caracteres
                if (currentText.length() >= 30) {
                    e.consume();
                    return;
                }

                // Condición para email
                if (type.equals("email")) {
                    if (!Pattern.matches("[a-zA-Z0-9@._]", charStr)) {
                        e.consume();
                        return;
                    }
                } else {
                    // Condición general
                    if (!Pattern.matches("[a-zA-Z0-9 ]", charStr)) {
                        e.consume();
                        return;
                    }
                }
            }

            // Disable pasting into the field
            @Override
            public void keyPressed(KeyEvent e) {
                if ((type.equals("customerId") || type.equals("orderId") || type.equals("shipmentId"))
                        && e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
                    e.consume();
                }
            }
        });

        // Disable pasting into the field via mouse right-click
        textField.setTransferHandler(null);
    }

    boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidCustomerId(String id) {
        try {
            int customerId = Integer.parseInt(id);
            return customerId > 0 && id.length() <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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
        return isValidCustomerId(txtCustomerId.getText())
                && !getCustomerName().isEmpty()
                && !getCustomerAddress().isEmpty()
                && isValidEmail(getCustomerEmail());
    }

    public int getOrderId() {
        try {
            return Integer.parseInt(txtOrderId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Date getOrderDate() {
        return (Date) datePickerOrderDate.getModel().getValue();
    }

    public int getOrderCustomerId() {
        return (int) cbOrderCustomerId.getSelectedItem();
    }

    public boolean areOrderFieldsValid() {
        return getOrderDate() != null && getOrderCustomerId() != -1;
    }

    public int getShipmentId() {
        try {
            return Integer.parseInt(txtShipmentId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Date getShipmentDate() {
        return (Date) datePickerShipmentDate.getModel().getValue();
    }

    public int getShipmentOrderId() {
        return (int) cbShipmentOrderId.getSelectedItem();
    }

    public boolean areShipmentFieldsValid() {
        return getShipmentDate() != null && getShipmentOrderId() != -1;
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

    public void showAllCustomersListener(ActionListener listenForShowAllButton) {
        btnShowAllCustomers.addActionListener(listenForShowAllButton);
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

    public void showAllOrdersListener(ActionListener listenForShowAllButton) {
        btnShowAllOrders.addActionListener(listenForShowAllButton);
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

    public void showAllShipmentsListener(ActionListener listenForShowAllButton) {
        btnShowAllShipments.addActionListener(listenForShowAllButton);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displayCustomerInfo(String name, String address, String email) {
        String message = String.format("Nombre: %s\nDirección: %s\nEmail: %s", name, address, email);
        JOptionPane.showMessageDialog(this, message, "Información del Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayAllCustomers(ResultSet rs) throws SQLException {
        String[] columnNames = {"ID", "Nombre", "Dirección", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        while (rs.next()) {
            int id = rs.getInt("customer_id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            Object[] row = {id, name, address, email};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Personalizar la apariencia de la tabla
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un JDialog para que la ventana sea redimensionable
        JDialog dialog = new JDialog();
        dialog.setTitle("Todos los Clientes");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton btnDelete = new JButton("Eliminar");
        JButton btnSelect = new JButton("Seleccionar");
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelect);

        // Establecer el icono personalizado redimensionado
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/crud/imagenes/cliente.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dialog.setIconImage(scaledIcon.getImage());

        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);

        // Acción de eliminar
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int customerId = (int) model.getValueAt(selectedRow, 0);
                try {
                    this.model.deleteCustomer(customerId);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(dialog, "Cliente eliminado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error al eliminar cliente: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un cliente para eliminar.");
            }
        });

        // Acción de seleccionar
        btnSelect.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                String name = (String) model.getValueAt(selectedRow, 1);
                String address = (String) model.getValueAt(selectedRow, 2);
                String email = (String) model.getValueAt(selectedRow, 3);

                txtCustomerId.setText(String.valueOf(id));
                txtCustomerName.setText(name);
                txtCustomerAddress.setText(address);
                txtCustomerEmail.setText(email);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un cliente para cargar sus datos.");
            }
        });
    }

    public void displayAllOrders(ResultSet rs) throws SQLException {
        String[] columnNames = {"ID", "Fecha", "Customer ID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            int id = rs.getInt("order_id");
            Date date = rs.getDate("order_date");
            int customerId = rs.getInt("customer_id");
            Object[] row = {id, sdf.format(date), customerId};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Personalizar la apariencia de la tabla
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un JDialog para que la ventana sea redimensionable
        JDialog dialog = new JDialog();
        dialog.setTitle("Todas las Órdenes");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton btnDelete = new JButton("Eliminar");
        JButton btnSelect = new JButton("Seleccionar");
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelect);

        // Establecer el icono personalizado redimensionado
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/crud/imagenes/cliente.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dialog.setIconImage(scaledIcon.getImage());

        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);

        // Acción de eliminar
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int orderId = (int) model.getValueAt(selectedRow, 0);
                try {
                    this.model.deleteOrder(orderId);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(dialog, "Orden eliminada exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error al eliminar orden: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione una orden para eliminar.");
            }
        });

        // Acción de seleccionar
        btnSelect.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectedRow, 1));
                } catch (ParseException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                int customerId = (int) model.getValueAt(selectedRow, 2);

                txtOrderId.setText(String.valueOf(id));
                datePickerOrderDate.getModel().setDate(date.getYear() + 1900, date.getMonth(), date.getDate());
                datePickerOrderDate.getModel().setSelected(true);
                cbOrderCustomerId.setSelectedItem(customerId);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione una orden para cargar sus datos.");
            }
        });
    }

    public void displayAllShipments(ResultSet rs) throws SQLException {
        String[] columnNames = {"ID", "Fecha", "Order ID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            int id = rs.getInt("shipment_id");
            Date date = rs.getDate("shipment_date");
            int orderId = rs.getInt("order_id");
            Object[] row = {id, sdf.format(date), orderId};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Personalizar la apariencia de la tabla
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un JDialog para que la ventana sea redimensionable
        JDialog dialog = new JDialog();
        dialog.setTitle("Todos los Envíos");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton btnDelete = new JButton("Eliminar");
        JButton btnSelect = new JButton("Seleccionar");
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelect);

        // Establecer el icono personalizado redimensionado
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/crud/imagenes/cliente.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dialog.setIconImage(scaledIcon.getImage());

        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);

        // Acción de eliminar
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int shipmentId = (int) model.getValueAt(selectedRow, 0);
                try {
                    this.model.deleteShipment(shipmentId);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(dialog, "Envío eliminado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error al eliminar envío: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un envío para eliminar.");
            }
        });

        // Acción de seleccionar
        btnSelect.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectedRow, 1));
                } catch (ParseException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                int orderId = (int) model.getValueAt(selectedRow, 2);

                txtShipmentId.setText(String.valueOf(id));
                datePickerShipmentDate.getModel().setDate(date.getYear() + 1900, date.getMonth(), date.getDate());
                datePickerShipmentDate.getModel().setSelected(true);
                cbShipmentOrderId.setSelectedItem(orderId);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Seleccione un envío para cargar sus datos.");
            }
        });
    }

    // Método para mostrar información de una orden en los campos de texto correspondientes
    public void displayOrderInfo(Date orderDate, int customerId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int orderId = getOrderId(); // Obtener el valor real de Order ID
        txtOrderId.setText(String.valueOf(orderId));
        datePickerOrderDate.getModel().setDate(orderDate.getYear() + 1900, orderDate.getMonth(), orderDate.getDate());
        datePickerOrderDate.getModel().setSelected(true);
        cbOrderCustomerId.setSelectedItem(customerId);
    }

    // Método para mostrar información de un envío en los campos de texto correspondientes
    public void displayShipmentInfo(Date shipmentDate, int orderId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int shipmentId = getShipmentId(); // Obtener el valor real de Shipment ID
        txtShipmentId.setText(String.valueOf(shipmentId));
        datePickerShipmentDate.getModel().setDate(shipmentDate.getYear() + 1900, shipmentDate.getMonth(), shipmentDate.getDate());
        datePickerShipmentDate.getModel().setSelected(true);
        cbShipmentOrderId.setSelectedItem(orderId);
    }
}
