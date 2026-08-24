package GUI.ParteVentanaPrincipal;

import Poderes.CreadorPoder;
import Poderes.OpcionPoder;
import Poderes.Poder;
import Poderes.PoderBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class VentanaAnadirPoder {

    private final PoderBase base;
    private Poder poderCreado;
    private List<Poder> poderesSeleccionados;

    public VentanaAnadirPoder(PoderBase base){
        this.base = base;
    }

    public List<Poder> mostrar(Stage ventanaPrincipal){
        Stage ventana = new Stage();

        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.initOwner(ventanaPrincipal);

        Label titulo = new Label("Añadir poder:" + base.getNombre());

        ComboBox<OpcionPoder> comboOpcion = new ComboBox<>();
        comboOpcion.getItems().addAll(base.getOpciones());
        comboOpcion.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(OpcionPoder opcion, boolean empty) {
                super.updateItem(opcion, empty);

                if (empty || opcion == null) {
                    setText(null);
                } else {
                    setText(opcion.getNombre());
                }
            }
        });
        comboOpcion.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(OpcionPoder opcion, boolean empty) {
                super.updateItem(opcion, empty);

                if (empty || opcion == null) {
                    setText(null);
                } else {
                    setText(opcion.getNombre());
                }
            }
        });
        comboOpcion.setPromptText("Selecciona una variante");

        Button aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");

        aceptar.setOnAction(event -> {

            OpcionPoder opcion = comboOpcion.getValue();

            if(opcion == null){
                return;
            }
            CreadorPoder creador = new CreadorPoder();

            poderCreado = creador.crearPoder(base, opcion, base.getModificadores());
            poderesSeleccionados.add(poderCreado);
            ventana.close();
        });

        cancelar.setOnAction(event -> {
            ventana.close();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(
                titulo,
                comboOpcion,
                aceptar,
                cancelar
        );

        Scene scene = new Scene(root, 400, 300);
        ventana.setScene(scene);
        ventana.setTitle("Añadir poder");
        ventana.showAndWait();

        return poderesSeleccionados;
    }
}
