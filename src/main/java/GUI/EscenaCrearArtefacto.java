package GUI;

import Artefactos.Artefacto;
import Artefactos.CreadorArtefacto;
import Datos.RepositorioDatos;
import GUI.ParteVentanaPrincipal.*;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class EscenaCrearArtefacto {

    CreadorArtefacto creadorArtefacto = new CreadorArtefacto();
    SeleccionContenedor contenedor = new SeleccionContenedor();
    SeleccionMateriaPrima matPrima = new SeleccionMateriaPrima();
    SeleccionPoderes poder = new SeleccionPoderes();


    public Scene crear() {

        Label titulo = new Label("Constructor de Artefactos");

        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Nombre del artefacto");

        CheckBox creadoParaArtefacto = new CheckBox("Creado específicamente para el artefacto");

        Button botonCrear = new Button("Crear artefacto");
        botonCrear.setOnAction(event ->{
            String nombre = campoNombre.getText();
            boolean creado = creadoParaArtefacto.isSelected();

            Artefacto artefacto = creadorArtefacto.crearArtefacto(
                    nombre,
                    contenedor.getContenedor(),
                    contenedor.getMaterial(),
                    contenedor.getCalidad(),
                    creado,
                    matPrima.getMateriasSeleccionadas(),
                    poder.getPoderesSeleccionados()
            );

            VentanaExportacion ventanaExportacion = new VentanaExportacion(artefacto);

            ventanaExportacion.mostrar(
                    (Stage) botonCrear.getScene().getWindow()
            );
        });

        VBox root = new VBox(10);

        root.getChildren().addAll(
                titulo,
                campoNombre,
                contenedor.getVista(),
                creadoParaArtefacto,
                matPrima.getVista(),
                poder.getVista(),
                botonCrear
        );

        return new Scene(root, 900, 700);
    }
}