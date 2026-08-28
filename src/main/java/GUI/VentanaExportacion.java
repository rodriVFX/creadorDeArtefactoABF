package GUI;

import Artefactos.Artefacto;
import Exportacion.ExportadorPDF;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class VentanaExportacion {

    private final Artefacto artefacto;
    private final ExportadorPDF exportador;

    public VentanaExportacion(Artefacto artefacto) {
        this.artefacto = artefacto;
        this.exportador = new ExportadorPDF();
    }

    public void mostrar(Stage ventanaPadre) {

        Stage ventana = new Stage();

        ventana.setTitle("Exportar artefacto");
        ventana.initOwner(ventanaPadre);

        Label titulo = new Label("Exportar artefacto");

        TextField campoNombre = new TextField(artefacto.getNombre());
        campoNombre.setPromptText("Nombre del archivo");

        Button botonRuta = new Button("Elegir ubicación...");

        Label rutaSeleccionada = new Label("No se ha seleccionado ninguna ubicación.");

        TextArea campoDescripcion = new TextArea();
        campoDescripcion.setPromptText("Descripción del objeto");
        campoDescripcion.getStyleClass().add("descripcion");
        campoDescripcion.setWrapText(true);

        Button botonExportar = new Button("Exportar");
        Button botonCancelar = new Button("Cancelar");

        final File[] archivoSeleccionado = new File[1];

        botonRuta.setOnAction(event -> {

            String nombre = campoNombre.getText().trim();

            if (nombre.isEmpty()) {
                mostrarError("Introduce un nombre para el archivo.");
                return;
            }

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Elegir ubicación");

            fileChooser.setInitialFileName(nombre + ".pdf");

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Archivos PDF (*.pdf)",
                            "*.pdf"
                    )
            );

            File archivo = fileChooser.showSaveDialog(ventana);

            if (archivo != null) {
                archivoSeleccionado[0] = archivo;
                rutaSeleccionada.setText(
                        archivo.getAbsolutePath()
                );
            }
        });
        botonExportar.setOnAction(event -> {

            if (archivoSeleccionado[0] == null) {
                mostrarError(
                        "Debes seleccionar una ubicación para guardar el PDF."
                );
                return;
            }

            File archivo = archivoSeleccionado[0];

            try {
                exportador.exportar(
                        artefacto,
                        archivo.getAbsolutePath(),
                        campoDescripcion.getText()
                );

                ventana.close();

                mostrarMensaje(
                        "PDF exportado correctamente."
                );

            } catch (RuntimeException e) {
                mostrarError(
                        "No se pudo exportar el PDF:\n" + e.getMessage()
                );
            }
        });

        botonCancelar.setOnAction(event -> ventana.close());

        HBox botones = new HBox(
                10,
                botonExportar,
                botonCancelar
        );

        VBox root = new VBox(
                10,
                titulo,
                campoNombre,
                campoDescripcion,
                botonRuta,
                rutaSeleccionada,
                botones
        );

        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        ventana.setScene(scene);
        ventana.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exportación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}