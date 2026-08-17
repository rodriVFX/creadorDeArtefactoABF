package GUI;

import MateriasPrimas.MateriaPrima;
import Datos.RepositorioDatos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VentanaMateriasPrimas {

    private final RepositorioDatos datos = new RepositorioDatos();
    private List<MateriaPrima> materiasSeleccionadas = new ArrayList<>();

    public List<MateriaPrima> mostrar() {

        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Seleccionar materias primas");

        ComboBox<MateriaPrima> combo = new ComboBox<>();

        combo.getItems().addAll(
                datos.getComponentes().listar().values()
        );

        combo.getItems().sort(
                Comparator.comparing(MateriaPrima::getNombre)
        );

        combo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MateriaPrima materia, boolean empty) {
                super.updateItem(materia, empty);

                if (empty || materia == null) {
                    setText(null);
                } else {
                    setText(materia.getNombre());
                }
            }
        });

        combo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(MateriaPrima materia, boolean empty) {
                super.updateItem(materia, empty);

                if (empty || materia == null) {
                    setText(null);
                } else {
                    setText(materia.getNombre());
                }
            }
        });

        Button aceptar = new Button("Aceptar");

        aceptar.setOnAction(event -> {

            MateriaPrima seleccionada = combo.getValue();

            if (seleccionada == null) {
                return;
            }

            materiasSeleccionadas.add(seleccionada);

            ventana.close();
        });

        VBox root = new VBox(10);

        root.getChildren().addAll(
                new Label("Selecciona una materia prima"),
                combo,
                aceptar
        );

        ventana.setScene(new Scene(root, 400, 250));
        ventana.showAndWait();

        return materiasSeleccionadas;
    }
}