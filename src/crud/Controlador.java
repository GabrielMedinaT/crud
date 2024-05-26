package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador {
    private Vista view;
    private Modelo model;

    public Controlador(Vista view, Modelo model) {
        this.view = view;
        this.model = model;

        this.view.addCustomerListener(new AddCustomerListener());
        this.view.updateCustomerListener(new UpdateCustomerListener());
        this.view.deleteCustomerListener(new DeleteCustomerListener());
        this.view.findCustomerListener(new FindCustomerListener());
    }

    class AddCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.addCustomer(view.getName(), view.getAddress(), view.getEmail());
            } catch (Exception ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class UpdateCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.updateCustomer(view.getCustomerId(), view.getName(), view.getAddress(), view.getEmail());
            } catch (Exception ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.deleteCustomer(view.getCustomerId());
            } catch (Exception ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class FindCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.findCustomer(view.getCustomerId());
                if (rs.next()) {
                    view.displayErrorMessage("Customer Found: " + rs.getString("name"));
                } else {
                    view.displayErrorMessage("Customer not found.");
                }
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }
}
