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
        this.view.showAllCustomersListener(new ShowAllCustomersListener());

        // Listeners para Orders
        this.view.addOrderListener(new AddOrderListener());
        this.view.updateOrderListener(new UpdateOrderListener());
        this.view.deleteOrderListener(new DeleteOrderListener());
        this.view.findOrderListener(new FindOrderListener());
        this.view.showAllOrdersListener(new ShowAllOrdersListener());

        // Listeners para Shipments
        this.view.addShipmentListener(new AddShipmentListener());
        this.view.updateShipmentListener(new UpdateShipmentListener());
        this.view.deleteShipmentListener(new DeleteShipmentListener());
        this.view.findShipmentListener(new FindShipmentListener());
        this.view.showAllShipmentsListener(new ShowAllShipmentsListener());
    }

    // Customer Listeners
    class AddCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areCustomerFieldsValid()) {
                    if (!view.isValidEmail(view.getCustomerEmail())) {
                        view.displayErrorMessage("Email no es válido.");
                    } else {
                        view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    }
                    return;
                }
                model.addCustomer(view.getCustomerId(), view.getCustomerName(), view.getCustomerAddress(), view.getCustomerEmail());
                view.displayErrorMessage("¡Cliente añadido exitosamente!");
                view.updateCustomerIDs(); // Actualiza el JComboBox de IDs de clientes
            } catch (SQLException ex) {
                if (ex.getMessage().contains("Duplicate entry")) {
                    view.displayErrorMessage("Id de cliente ya existe, pruebe a poner otro.");
                } else {
                    view.displayErrorMessage("Error: " + ex.getMessage());
                }
            }
        }
    }

    class UpdateCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!view.areCustomerFieldsValid()) {
                    if (!view.isValidEmail(view.getCustomerEmail())) {
                        view.displayErrorMessage("Email no es válido.");
                    } else {
                        view.displayErrorMessage("¡Todos los campos deben estar completos!");
                    }
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

    class ShowAllCustomersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.getAllCustomers();
                view.displayAllCustomers(rs);
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
                int orderId = view.getOrderId(); // Asegúrate de obtener el orderId de la vista
                Date orderDate = view.getOrderDate();
                int customerId = view.getOrderCustomerId();
                model.addOrder(orderId, new java.sql.Date(orderDate.getTime()), customerId);
                view.displayErrorMessage("¡Orden añadida exitosamente!");
                view.updateOrderIDs(); // Actualiza el JComboBox de IDs de órdenes
            } catch (SQLException ex) {
                if (ex.getMessage().contains("FOREIGN KEY")) {
                    view.displayErrorMessage("Error: El ID del cliente no existe.");
                } else {
                    view.displayErrorMessage("Error: " + ex.getMessage());
                }
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
                if (ex.getMessage().contains("FOREIGN KEY")) {
                    view.displayErrorMessage("Error: El ID del cliente no existe.");
                } else {
                    view.displayErrorMessage("Error: " + ex.getMessage());
                }
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

    class ShowAllOrdersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.getAllOrders();
                view.displayAllOrders(rs);
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

                int shipmentId = view.getShipmentId(); // Obtener el ID del envío desde la vista
                Date shipmentDate = new java.sql.Date(view.getShipmentDate().getTime()); // Obtener la fecha del envío
                int orderId = view.getShipmentOrderId(); // Obtener el ID del pedido

                model.addShipment(shipmentId, (java.sql.Date) shipmentDate, orderId); // Llamar al método del modelo con los parámetros correctos
                view.displayErrorMessage("¡Envío añadido exitosamente!");
            } catch (SQLException ex) {
                if (ex.getMessage().contains("FOREIGN KEY")) {
                    view.displayErrorMessage("Error: El ID del pedido no existe.");
                } else {
                    view.displayErrorMessage("Error: " + ex.getMessage());
                }
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
                if (ex.getMessage().contains("FOREIGN KEY")) {
                    view.displayErrorMessage("Error: El ID del pedido no existe.");
                } else {
                    view.displayErrorMessage("Error: " + ex.getMessage());
                }
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

    class ShowAllShipmentsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = model.getAllShipments();
                view.displayAllShipments(rs);
            } catch (SQLException ex) {
                view.displayErrorMessage("Error: " + ex.getMessage());
            }
        }
    }
}
