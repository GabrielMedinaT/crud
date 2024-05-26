package crud;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializar el modelo y crear la base de datos y tablas
            Modelo model = new Modelo();
            model.createDatabase();
            model.connect();
            model.createTables();

            // Inicializar la vista y el controlador
            Vista view = new Vista();
            Controlador controller = new Controlador(view, model);

            // Mostrar la vista
            view.setVisible(true);
            System.out.println("Aplicación iniciada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
