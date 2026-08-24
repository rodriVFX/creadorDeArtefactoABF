package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SeleccionVidas implements SelectorInterfaz{

    private final List<MateriaPrima> materiasSeleccionadas = new ArrayList<>();
    private final CreadorMateriaPrima creador = new CreadorMateriaPrima();

    public Node getVista(){
        VBox root = new VBox(10);

        TextField campoPresencia = new TextField();
        campoPresencia.setPromptText("Introduce la presencia del sacrificio");
        campoPresencia.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        TextField campoNatura = new TextField("0");
        campoNatura.setPromptText("Introduce la natura del sacrificio");
        campoNatura.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        TextField campoGnosis = new TextField("0");
        campoGnosis.setPromptText("Introduce el gnosis del sacrificio");
        campoGnosis.setTextFormatter(new TextFormatter<Integer>(change -> {
            if(change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        CheckBox checkVoluntario = new CheckBox("¿El sacrificio es voluntario?");
        CheckBox checkSobrenat = new CheckBox("¿La vida sacrificada es sobrenatural?");
        CheckBox checkArtificial = new CheckBox("¿La vida sacrificada es artificial?");

        Button botonAnadir = new Button("Añadir");
        botonAnadir.setOnAction(event -> {
            int presencia = Integer.parseInt(campoPresencia.getText());
            int natura = Integer.parseInt(campoNatura.getText());
            int gnosis = Integer.parseInt(campoGnosis.getText());
            boolean voluntario = checkVoluntario.isSelected();
            boolean sobrenat = checkSobrenat.isSelected();
            boolean artificial = checkArtificial.isSelected();

            materiasSeleccionadas.add(creador.crearSacrificioDeVidas(presencia, natura, gnosis, voluntario, sobrenat, artificial));
        });

        root.getChildren().addAll(
                campoPresencia,
                campoNatura,
                campoGnosis,
                checkVoluntario,
                checkSobrenat,
                checkArtificial,
                botonAnadir
        );
        return root;
    }

    public List<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
