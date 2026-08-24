package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SeleccionPOD implements SelectorInterfaz{

    private final List<MateriaPrima> materiasSeleccionadas = new ArrayList<>();
    private final CreadorMateriaPrima creador = new CreadorMateriaPrima();

    public Node getVista(){
        VBox root = new VBox(10);

        TextField campoNivel = new TextField();
        campoNivel.setPromptText("Introduce tu atributo POD");
        campoNivel.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));


        TextField campoPresencia = new TextField();
        campoPresencia.setPromptText("Introduce tu presencia");
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


        root.getChildren().addAll(
                campoNivel,
                campoPresencia,
                botonAnadirMat
        );
        return root;
    }

    public List<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
