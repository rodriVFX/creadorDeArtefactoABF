package GUI.ParteVentanaPrincipal;

import Datos.Enums.TipoMatPrimaEnum;
import MateriasPrimas.MateriaPrima;
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

public class SeleccionMateriaPrima {

    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();

    public Node getVista(){
        VBox root = new VBox(10);

        Label labelTipo = new Label("Selecciona el método de obtención de PP:");
        ComboBox<TipoMatPrimaEnum> comboTipo = new ComboBox<>();
        for(TipoMatPrimaEnum tipo : TipoMatPrimaEnum.values()){
            comboTipo.getItems().add(tipo);
        }
        comboTipo.getItems().sort(Comparator.comparing(TipoMatPrimaEnum::toString));
        comboTipo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(TipoMatPrimaEnum tipo, boolean empty) {
                super.updateItem(tipo, empty);

                if (empty || tipo == null) {
                    setText(null);
                } else {
                    setText(nombreTipo(tipo));
                }
            }
        });
        comboTipo.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(TipoMatPrimaEnum tipo, boolean empty) {
                super.updateItem(tipo, empty);

                if (empty || tipo == null) {
                    setText(null);
                } else {
                    setText(nombreTipo(tipo));
                }
            }
        });
        comboTipo.setPromptText("Selecciona el método de obtención de PP");

        Button botonAnadirMat = new Button("Añadir Materia Prima");
        botonAnadirMat.setOnAction(event -> {
            TipoMatPrimaEnum tipoMat = comboTipo.getValue();
            if(tipoMat == null) {
                return;
            }
            VentanaAnadirMateria ventana = new VentanaAnadirMateria(tipoMat);

            ObservableList<MateriaPrima> mat = ventana.mostrar((Stage) botonAnadirMat.getScene().getWindow());
            if(mat != null){
                materiasSeleccionadas.addAll(mat);
            }
        });

        TableView<MateriaPrima> tablaMat = new TableView<>();
        TableColumn<MateriaPrima, String> columnaNombre = new TableColumn<>("Materia");
        columnaNombre.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getNombre()));
        columnaNombre.setPrefWidth(300);
        TableColumn<MateriaPrima, Integer> columnaPP = new TableColumn<>("Cantidad PP");
        columnaPP.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCantidadPP()));
        columnaPP.setPrefWidth(100);
        TableColumn<MateriaPrima, Integer> columnaNivel = new TableColumn<>("Nivel PP");
        columnaNivel.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getNivelPP()));
        TableColumn<MateriaPrima, String> columnaEspecial = new TableColumn<>("Reglas especiales");
        columnaEspecial.setCellValueFactory(dato -> new SimpleStringProperty(String.join(", ", dato.getValue().getReglasEspeciales())));
        columnaEspecial.setPrefWidth(400);
        tablaMat.getColumns().addAll(columnaNombre, columnaEspecial, columnaPP, columnaNivel);
        tablaMat.setItems(materiasSeleccionadas);

        Button botonEliminarMat = new Button("Eliminar Materia Seleccionada");
        botonEliminarMat.setOnAction(event -> {
            ObservableList<MateriaPrima> materiaSeleccionada = tablaMat.getSelectionModel().getSelectedItems();
            if(materiaSeleccionada == null){
                return;
            }
            for(MateriaPrima m : materiaSeleccionada){
                tablaMat.getItems().remove(m);
            }
        });


        HBox boxAnadir = new HBox(5);
        boxAnadir.getChildren().addAll(labelTipo, comboTipo, botonAnadirMat, botonEliminarMat);

        root.getChildren().addAll(
                boxAnadir,
                tablaMat
        );
        return root;
    }

    private String nombreTipo(TipoMatPrimaEnum tipo){
        return switch (tipo){
            case COMPONENTE -> "Componente";
            case OBJETO -> "Sacrificio de objetos sobrenaturales";
            case ZEON -> "Infusión de zeón";
            case PODER -> "Sacrificio de POD del hechicero";
            case VIDAS -> "Sacrificio de vidas";
        };
    };
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}
