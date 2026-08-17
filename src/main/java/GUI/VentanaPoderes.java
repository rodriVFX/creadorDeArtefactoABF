package GUI;

import Datos.RepositorioDatos;
import Poderes.Poder;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VentanaPoderes {

    private final RepositorioDatos datos = new RepositorioDatos();
    private List<Poder> poderesSeleccionados = new ArrayList<>();

    public List<Poder> mostrar() {

        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Seleccionar poder");

        // ComboBox faceta
        // ComboBox poder
        // ComboBox opción
        // ListView modificadores
        // Botón aceptar

        return poderesSeleccionados;
    }
}