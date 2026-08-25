package GUI.ParteVentanaPrincipal.TiposMatPrima;


import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SeleccionZeon implements SelectorInterfaz{

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();

    public Node getVista(){
        VBox root = new VBox(10);

        TextField campoCantidad = new TextField();
        campoCantidad.setPromptText("Introduce una cantidad de Zeón");
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

        root.getChildren().addAll(
                campoCantidad,
                botonAnadirZeon
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}