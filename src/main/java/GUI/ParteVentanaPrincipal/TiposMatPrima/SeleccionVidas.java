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
import java.util.List;

public class SeleccionVidas implements SelectorInterfaz{

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();
    private final CreadorMateriaPrima creador = new CreadorMateriaPrima();

    public Node getVista(){
        VBox root = new VBox(10);

        Label labelPresencia = new Label("Introduce la presencia del sacrificio:");
        Label labelNatura = new Label("Introduce la natura del sacrificio:");
        Label labelGnosis = new Label("Introduce la gnosis del sacrificio:");

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

        HBox boxVida = new HBox(5);
        boxVida.getChildren().addAll(labelPresencia, campoPresencia, labelNatura, campoNatura, labelGnosis, campoGnosis);

        root.getChildren().addAll(
                boxVida,
                checkVoluntario,
                checkSobrenat,
                checkArtificial,
                botonAnadir
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
