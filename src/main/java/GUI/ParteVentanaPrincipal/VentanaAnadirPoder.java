package GUI.ParteVentanaPrincipal;

import Datos.ListaFacetasPoder;
import Datos.RepositorioDatos;
import Poderes.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class VentanaAnadirPoder {

    private final RepositorioDatos datos = new RepositorioDatos();
    private final ListaFacetasPoder faceta;
    private final ObservableList<Poder> poderesSeleccionados = FXCollections.observableArrayList();

    public VentanaAnadirPoder(ListaFacetasPoder faceta){
        this.faceta = faceta;
    }

    public ObservableList<Poder> mostrar(Stage ventanaPrincipal){
        Stage ventana = new Stage();

        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.initOwner(ventanaPrincipal);

        Label titulo = new Label("Añadir poder de la faceta " + faceta.toString());

        ComboBox<PoderBase> comboBase = new ComboBox<>();
        comboBase.getItems().addAll(poderesDisponibles());
        comboBase.getItems().sort(Comparator.comparing(PoderBase::getNombre));
        comboBase.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(PoderBase pod, boolean empty){
                super.updateItem(pod, empty);
                if(empty || pod == null){
                    setText(null);
                }
                else{
                    setText(pod.getNombre());
                }
            }
        });
        comboBase.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(PoderBase pod, boolean empty){
                super.updateItem(pod, empty);
                if(empty || pod == null){
                    setText(null);
                }
                else{
                    setText(pod.getNombre());
                }
            }
        });

        ComboBox<OpcionPoder> comboOpcion = new ComboBox<>();
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
        comboBase.setPromptText("Selecciona un poder");
        ListView<ModificadorPoder> listaMod = new ListView<>();
        listaMod.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaMod.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ModificadorPoder mod, boolean empty) {
                super.updateItem(mod, empty);

                if (empty || mod == null) {
                    setText(null);
                } else {
                    setText(mod.getNombre());
                }
            }
        });
        comboBase.setOnAction(event -> {
            comboOpcion.getItems().clear();
            if(comboBase.getValue() != null) {
                comboOpcion.getItems().addAll(comboBase.getValue().getOpciones());
                comboOpcion.getItems().sort(Comparator.comparing(OpcionPoder::getNombre));
            }
            List<ModificadorPoder> mods = comboBase.getValue().getModificadores();
            listaMod.getItems().clear();
            if(mods == null){
                return;
            }
            listaMod.getItems().addAll(mods);
            listaMod.getItems().sort(Comparator.comparing(ModificadorPoder::getNombre));
        });
        comboOpcion.setPromptText("Selecciona una variante");

        Button anadirPod = new Button("Añadir");
        Button aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");

        anadirPod.setOnAction(event -> {
            PoderBase base = comboBase.getValue();
            OpcionPoder opcion = comboOpcion.getValue();
            List<ModificadorPoder> mods = listaMod.getSelectionModel().getSelectedItems();

            if(base == null || opcion == null){
                return;
            }
            CreadorPoder creador = new CreadorPoder();

            Poder poder = creador.crearPoder(base, opcion, mods);
            poderesSeleccionados.add(poder);
        });

        aceptar.setOnAction(event -> {
            ventana.close();
        });
        cancelar.setOnAction(event -> {
            poderesSeleccionados.clear();
            ventana.close();
        });

        TableView<Poder> tablaPod = new TableView<>();
        TableColumn<Poder, String> columnaNombre = new TableColumn<>("Materia");
        columnaNombre.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getNombre()));
        TableColumn<Poder, Integer> columnaPP = new TableColumn<>("Cantidad PP");
        columnaPP.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getValue()));
        TableColumn<Poder, Integer> columnaNivel = new TableColumn<>("Nivel PP");
        columnaNivel.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getKey()));
        TableColumn<Poder, String>columnaFaceta = new TableColumn<>("Faceta");
        columnaFaceta.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getBase().getFaceta()));
        TableColumn<Poder, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getBase().getDescripcion()));
        TableColumn<Poder, String> columnaMods = new TableColumn<>("Modificadores");
        columnaMods.setCellValueFactory(dato -> new SimpleStringProperty(String.join(", ", dato.getValue().listarModificadores())));
        tablaPod.getColumns().addAll(columnaNombre, columnaFaceta, columnaDescripcion, columnaMods, columnaPP, columnaNivel);
        tablaPod.setItems(poderesSeleccionados);

        VBox root = new VBox(10);
        root.getChildren().addAll(
                titulo,
                comboBase,
                comboOpcion,
                listaMod,
                anadirPod,
                tablaPod,
                aceptar,
                cancelar
        );

        Scene scene = new Scene(root, 1200, 800);
        ventana.setScene(scene);
        ventana.setTitle("Añadir poder");
        ventana.showAndWait();

        return poderesSeleccionados;
    }

    private List<PoderBase> poderesDisponibles(){
        List<PoderBase> poderesDisponibles = new ArrayList<>();
        String nombreFaceta = new SeleccionPoderes().nombreFaceta(faceta);
        for(PoderBase p : datos.getPoderes().listar().values()){
            if(p.getFaceta().equals(nombreFaceta)){
                poderesDisponibles.add(p);
            }
        }
        return poderesDisponibles;
    }
}
