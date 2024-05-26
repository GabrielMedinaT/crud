package crud;

public class Main {
    public static void main(String[] args) {
        Vista view = new Vista();
        Modelo model = new Modelo();
        Controlador controller = new Controlador(view, model);
        view.setVisible(true);
    }
}
