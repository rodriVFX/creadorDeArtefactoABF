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

public class SeleccionPOD implements SelectorInterfaz{

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();
    private final CreadorMateriaPrima creador = new CreadorMateriaPrima();

    public Node getVista(){
        VBox root = new VBox(10);

        Label labelPOD = new Label("Introduce tu atributo POD:");
        Label labelPresencia = new Label("Introduce tu presencia:");

        TextField campoNivel = new TextField();
        campoNivel.setPromptText("0");
        campoNivel.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));


        TextField campoPresencia = new TextField();
        campoPresencia.setPromptText("0");
        campoPresencia.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        Button botonAnadirMat = new Button("Añadir");
        botonAnadirMat.setOnAction(event -> {
            int nivel = Integer.parseInt(campoNivel.getText());
            int presencia = Integer.parseInt(campoPresencia.getText());
            if(nivel < 1 || presencia < 1){
                return;
            }
            materiasSeleccionadas.add(creador.crearSacrificioDePoder(nivel, presencia));
        });

        HBox boxPod = new HBox(5);
        boxPod.getChildren().addAll(labelPOD, campoNivel, labelPresencia, campoPresencia);

        root.getChildren().addAll(
                boxPod,
                botonAnadirMat
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
