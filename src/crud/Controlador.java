package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Controlador {
    private Vista view;
    private Modelo model;

    public Controlador(Vista view, Modelo model) {
        this.view = view;
        this.model = model;

        // Listeners para Customers
        this.view.addCustomerListener(new AddCustomerListener());
        this.view.updateCustomerListener(new UpdateCustomerListener());
        this.view.deleteCustomerListener(new DeleteCustomerListener());
        this.view.findCustomerListener(new FindCustomerListener());

        // Listeners para Orders
        this.view.addOrderListener(new AddOrderListener());
        this.view.updateOrderListener(new UpdateOrderListener());
        this.view.deleteOrderListener(new DeleteOrderListener());
        this.view.findOrderListener(new FindOrderListener());

        // Listeners para Shipments
        this.view.addShipmentListener(new AddShipmentListener());
        this.view.updateShipmentListener(new UpdateShipmentListener());
        this.view.deleteShipmentListener(new DeleteShipmentListener());
        this.view.findShipmentListener(new FindShipmentListener());
    }

    // Customer Listeners
    class AddCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areCustomerFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.addCustomer(view.getCustomerName(), view.getCustomerAddress(), view.getCustomerEmail());
                view.displayErrorMessage("¡Cliente añadido exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class UpdateCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areCustomerFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.updateCustomer(view.getCustomerId(), view.getCustomerName(), view.getCustomerAddress(), view.getCustomerEmail());
                view.displayErrorMessage("¡Cliente actualizado exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.deleteCustomer(view.getCustomerId());
                view.displayErrorMessage("¡Cliente eliminado exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class FindCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.findCustomer(view.getCustomerId());
                if (rs.next()) {
                    view.displayCustomerInfo(rs.getString("name"), rs.getString("address"), rs.getString("email"));
                } else {
                    view.displayErrorMessage("Cliente no encontrado.");
                }
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    // Order Listeners
    class AddOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areOrderFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.addOrder(new java.sql.Date(view.getOrderDate().getTime()), view.getOrderCustomerId());
                view.displayErrorMessage("¡Orden añadida exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class UpdateOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areOrderFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.updateOrder(view.getOrderId(), new java.sql.Date(view.getOrderDate().getTime()), view.getOrderCustomerId());
                view.displayErrorMessage("¡Orden actualizada exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class DeleteOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.deleteOrder(view.getOrderId());
                view.displayErrorMessage("¡Orden eliminada exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class FindOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.findOrder(view.getOrderId());
                if (rs.next()) {
                    view.displayOrderInfo(rs.getDate("order_date"), rs.getInt("customer_id"));
                } else {
                    view.displayErrorMessage("Orden no encontrada.");
                }
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    // Shipment Listeners
    class AddShipmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areShipmentFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.addShipment(new java.sql.Date(view.getShipmentDate().getTime()), view.getShipmentOrderId());
                view.displayErrorMessage("¡Envío añadido exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class UpdateShipmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areShipmentFieldsValid()) {
                    view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    return;
                }
                model.updateShipment(view.getShipmentId(), new java.sql.Date(view.getShipmentDate().getTime()), view.getShipmentOrderId());
                view.displayErrorMessage("¡Envío actualizado exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class DeleteShipmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.deleteShipment(view.getShipmentId());
                view.displayErrorMessage("¡Envío eliminado exitosamente!");
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }

    class FindShipmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.findShipment(view.getShipmentId());
                if (rs.next()) {
                    view.displayShipmentInfo(rs.getDate("shipment_date"), rs.getInt("order_id"));
                } else {
                    view.displayErrorMessage("Envío no encontrado.");
                }
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }
}
