package GUI.ParteVentanaPrincipal;

import Datos.TipoMatPrimaEnum;
import GUI.ParteVentanaPrincipal.TiposMatPrima.*;
import MateriasPrimas.MateriaPrima;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class VentanaAnadirMateria {

    private final TipoMatPrimaEnum tipoMat;
    private SelectorInterfaz selector;

    public VentanaAnadirMateria(TipoMatPrimaEnum tipoMat) {
        this.tipoMat = tipoMat;
    }

    public ObservableList<MateriaPrima> mostrar(Stage ventanaPrincipal) {
        Stage ventana = new Stage();

        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.initOwner(ventanaPrincipal);

        Label titulo = new Label("Añadir materia prima:");
        titulo.setAlignment(Pos.CENTER);
        titulo.getStyleClass().add("titulo");

        Node vista = crearVista();

        Button botonAceptar = new Button("Aceptar");
        Button botonCancelar = new Button("Cancelar");
        botonAceptar.setOnAction(event -> {
            ventana.close();
        });
        botonCancelar.setOnAction(event -> {
            selector.getMateriasSeleccionadas().clear();
            ventana.close();
        });

        TableView<MateriaPrima> tablaMat = new TableView<>();
        TableColumn<MateriaPrima, String> columnaNombre = new TableColumn<>("Materia");
        columnaNombre.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getNombre()));
        TableColumn<MateriaPrima, Integer> columnaPP = new TableColumn<>("Cantidad PP");
        columnaPP.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCantidadPP()));
        TableColumn<MateriaPrima, Integer> columnaNivel = new TableColumn<>("Nivel PP");
        columnaNivel.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getNivelPP()));
        tablaMat.getColumns().addAll(columnaNombre, columnaPP, columnaNivel);
        tablaMat.setItems(selector.getMateriasSeleccionadas());

        HBox boxBotones = new HBox(5);
        boxBotones.getChildren().addAll(botonAceptar, botonCancelar);

        VBox root = new VBox(10);
        root.getChildren().addAll(
                titulo,
                vista,
                boxBotones,
                tablaMat
        );

        Scene scene = new Scene(root, 1000, 600);
        ventana.setScene(scene);
        ventana.setTitle("Añadir materia prima");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        ventana.showAndWait();


        if(selector != null){
            return selector.getMateriasSeleccionadas();
        }

        return null;
    }

    private Node crearVista(){
        return switch(tipoMat){
            case COMPONENTE -> {
                SeleccionComponentes selector = new SeleccionComponentes();
                this.selector = selector;
                yield selector.getVista();
            }
            case ZEON -> {
                SeleccionZeon selector = new SeleccionZeon();
                this.selector = selector;
                yield selector.getVista();
            }
            case PODER -> {
                SeleccionPOD selector = new SeleccionPOD();
                this.selector = selector;
                yield selector.getVista();
            }
            case VIDAS -> {
                SeleccionVidas selector = new SeleccionVidas();
                this.selector = selector;
                yield selector.getVista();
            }
            case OBJETO -> {
                SeleccionObjetos selector = new SeleccionObjetos();
                this.selector = selector;
                yield selector.getVista();
            }
        };
    }
}
