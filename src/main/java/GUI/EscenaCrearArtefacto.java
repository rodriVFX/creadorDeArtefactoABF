package GUI;

import Artefactos.Artefacto;
import Artefactos.CreadorArtefacto;
import GUI.ParteVentanaPrincipal.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class EscenaCrearArtefacto {

    CreadorArtefacto creadorArtefacto = new CreadorArtefacto();
    SeleccionContenedor contenedor = new SeleccionContenedor();
    SeleccionMateriaPrima matPrima = new SeleccionMateriaPrima();
    SeleccionPoderes poder = new SeleccionPoderes();


    public Scene crear() {

        Label titulo = new Label("Constructor de Artefactos");
        titulo.setAlignment(Pos.CENTER);
        titulo.getStyleClass().add("titulo");

        Label labelNombre = new Label("Nombre del artefacto: ");
        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Nombre del artefacto");
        HBox boxNombre = new HBox();
        boxNombre.getChildren().addAll(labelNombre, campoNombre);

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
                boxNombre,
                contenedor.getVista(),
                creadoParaArtefacto,
                matPrima.getVista(),
                poder.getVista(),
                botonCrear
        );

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return scene;

    }
}