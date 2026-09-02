package GUI.ParteVentanaPrincipal;

import Datos.Enums.ListaFacetasPoder;
import Poderes.Poder;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

public class SeleccionPoderes {

    private final ObservableList<Poder> poderesSeleccionados = FXCollections.observableArrayList();

    public Node getVista() {
        VBox root = new VBox(10);

        Label labelFaceta = new Label("Selecciona la faceta del Poder:");

        ComboBox<ListaFacetasPoder> comboFaceta = new ComboBox<>();
        for(ListaFacetasPoder lista : ListaFacetasPoder.values()){
            comboFaceta.getItems().add(lista);
        }
        comboFaceta.getItems().sort(Comparator.comparing(ListaFacetasPoder::toString));
        comboFaceta.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(ListaFacetasPoder faceta, boolean empty){
                super.updateItem(faceta, empty);

                if(empty || faceta == null) {
                    setText(null);
                } else {
                    setText(nombreFaceta(faceta));
                }

            }
        });
        comboFaceta.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(ListaFacetasPoder faceta, boolean empty){
                super.updateItem(faceta, empty);

                if(empty || faceta == null) {
                    setText(null);
                } else {
                    setText(nombreFaceta(faceta));
                }

            }
        });
        comboFaceta.setPromptText("Selecciona la faceta del Poder");

        Button botonAnadir = new Button("Añadir Poder");
        botonAnadir.setOnAction(event -> {
            ListaFacetasPoder faceta = comboFaceta.getValue();
            if(faceta == null){
                return;
            }
            VentanaAnadirPoder ventana = new VentanaAnadirPoder(faceta);

            ObservableList<Poder> pod = ventana.mostrar((Stage) botonAnadir.getScene().getWindow());
            if(pod != null){
                poderesSeleccionados.addAll(pod);
            }
        });

        TableView<Poder> tablaPod = new TableView<>();
        TableColumn<Poder, String> columnaPoder = new TableColumn<>("Poder");
        columnaPoder.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getNombre()));
        columnaPoder.setPrefWidth(150);
        TableColumn<Poder, String> columnaOpcion = new TableColumn<>("Variante");
        columnaOpcion.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getOpcion().getNombre()));
        columnaOpcion.setPrefWidth(150);
        TableColumn<Poder, String> columnaModificadores = new TableColumn<>("Modificadores");
        columnaModificadores.setCellValueFactory(dato -> new SimpleStringProperty(String.join(", ", dato.getValue().listarModificadores())));
        columnaModificadores.setPrefWidth(150);
        TableColumn<Poder, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getBase().getDescripcion()));
        columnaDescripcion.setPrefWidth(350);
        TableColumn<Poder, Integer> poderPP = new TableColumn<>("Coste PP");
        poderPP.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getValue()));
        TableColumn<Poder, Integer> poderNivel = new TableColumn<>("Nivel PP");
        poderNivel.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getKey()));
        tablaPod.getColumns().addAll(columnaPoder, columnaOpcion, columnaDescripcion, columnaModificadores, poderPP, poderNivel);
        tablaPod.setItems(poderesSeleccionados);

        Button botonEliminarPoder = new Button("Eliminar Poder Seleccionado");
        botonEliminarPoder.setOnAction(event -> {
            ObservableList<Poder> poderSeleccionado = tablaPod.getSelectionModel().getSelectedItems();
            if(poderSeleccionado == null){
                return;
            }
            for(Poder p : poderSeleccionado){
                tablaPod.getItems().remove(p);
            }
        });

        HBox boxAnadir = new HBox(5);
        boxAnadir.getChildren().addAll(labelFaceta, comboFaceta, botonAnadir, botonEliminarPoder);

        root.getChildren().addAll(
                boxAnadir,
                tablaPod
        );
        return root;
    }

    public String nombreFaceta(ListaFacetasPoder faceta){
        return switch(faceta){
            case GENERAL -> "Generales de Calidad";
            case OFENSIVA -> "Ofensiva";
            case DEFENSIVA -> "Defensiva";
            case PROTECCION -> "Protección";
            case MAGICA -> "Potenciación Mágica";
            case PSIQUICA -> "Potenciación Psíquica";
            case CONJURACION -> "Potenciación en la Conjuración";
            case CONJUROS -> "Conjuros Innatos";
            case MEJORAS -> "Mejoras";
            case DOMINE -> "Domine";
            case ESOTERICA -> "Esotérica";
        };
    }

    public ObservableList<Poder> getPoderesSeleccionados(){
        return poderesSeleccionados;
    }
}