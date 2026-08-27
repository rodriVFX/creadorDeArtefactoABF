package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeleccionObjetos implements SelectorInterfaz{

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();
    private final CreadorMateriaPrima creador = new CreadorMateriaPrima();

    public Node getVista(){
        VBox root = new VBox(10);

        Label labelCantidad = new Label("Introduce la cantidad de PP:");
        Label labelNivel = new Label("Introduce el nivel de los PP:");

        TextField campoCantidad = new TextField();
        campoCantidad.setPromptText("0");
        campoCantidad.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        TextField campoNivel = new TextField();
        campoNivel.setPromptText("0");
        campoNivel.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));


        CheckBox checkConservaNivel = new CheckBox("¿Deseas conservar el nivel de PP más alto?");

        Button botonAnadir = new Button("Añadir");
        botonAnadir.setOnAction(event -> {
            int cantidad = Integer.parseInt(campoCantidad.getText());
            int nivel = Integer.parseInt(campoNivel.getText());
            boolean conserva = checkConservaNivel.isSelected();
            Map<Integer, Integer> pp = new HashMap<>();
            pp.put(cantidad, nivel);

            if(cantidad < 1 || nivel < 1 || nivel > 5){
                return;
            }

            materiasSeleccionadas.add(creador.crearSacrificioDeObjetos(pp, conserva));
        });

        HBox boxObjetos = new HBox(5);
        boxObjetos.getChildren().addAll(labelCantidad, campoCantidad, labelNivel, campoNivel);

        root.getChildren().addAll(
                boxObjetos,
                checkConservaNivel,
                botonAnadir
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
