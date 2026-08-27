package GUI.ParteVentanaPrincipal.TiposMatPrima;


import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SeleccionZeon implements SelectorInterfaz{

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();

    public Node getVista(){
        VBox root = new VBox(10);

        Label labelZeon = new Label("Introduce una cantidad de Zeón");

        TextField campoCantidad = new TextField();
        campoCantidad.setPromptText("0");
        campoCantidad.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        Button botonAnadirZeon = new Button("Añadir Zeón");
        botonAnadirZeon.setOnAction(event -> {
            int zeon = Integer.parseInt(campoCantidad.getText());
            if(zeon < 1){
                return;
            }
            materiasSeleccionadas.add(new CreadorMateriaPrima().crearInfusionZeon(zeon));
        });

        HBox boxZeon = new HBox(5);
        boxZeon.getChildren().addAll(labelZeon, campoCantidad);

        root.getChildren().addAll(
                boxZeon,
                botonAnadirZeon
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}