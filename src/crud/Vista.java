package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame {
    private JTextField txtCustomerId = new JTextField(10);
    private JTextField txtName = new JTextField(10);
    private JTextField txtAddress = new JTextField(10);
    private JTextField txtEmail = new JTextField(10);
    private JButton btnAdd = new JButton("Add");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnFind = new JButton("Find");

    public Vista() {
        setTitle("Customer Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtCustomerId, gbc);

        // Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtName, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(txtAddress, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(txtEmail, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnUpdate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        add(btnDelete, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        add(btnFind, gbc);
    }

    public int getCustomerId() {
        try {
            return Integer.parseInt(txtCustomerId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getName() {
        return txtName.getText();
    }

    public String getAddress() {
        return txtAddress.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public void addCustomerListener(ActionListener listenForAddButton) {
        btnAdd.addActionListener(listenForAddButton);
    }

    public void updateCustomerListener(ActionListener listenForUpdateButton) {
        btnUpdate.addActionListener(listenForUpdateButton);
    }

    public void deleteCustomerListener(ActionListener listenForDeleteButton) {
        btnDelete.addActionListener(listenForDeleteButton);
    }

    public void findCustomerListener(ActionListener listenForFindButton) {
        btnFind.addActionListener(listenForFindButton);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
