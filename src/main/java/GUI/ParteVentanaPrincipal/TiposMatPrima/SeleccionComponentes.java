package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.MateriaPrima;
import Datos.RepositorioDatos;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SeleccionComponentes implements SelectorInterfaz{

    private final RepositorioDatos datos = new RepositorioDatos();
    private final ObservableList<MateriaPrima> materiasSeleccionadas = FXCollections.observableArrayList();

    public Node getVista(){
        VBox root = new VBox(10);

        ComboBox<MateriaPrima> comboMat = new ComboBox<>();
        comboMat.getItems().addAll(datos.getComponentes().listar().values());
        comboMat.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(MateriaPrima mat, boolean empty){
                super.updateItem(mat, empty);
                if(empty || mat == null){
                    setText(null);
                }
                else{
                    setText(mat.getNombre());
                }
            }
        });
        comboMat.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(MateriaPrima mat, boolean empty){
                super.updateItem(mat, empty);
                if(empty || mat == null){
                    setText(null);
                }
                else{
                    setText(mat.getNombre());
                }
            }
        });

        Button botonAnadirMat = new Button("Añadir");
        botonAnadirMat.setOnAction(event -> {
            MateriaPrima mat = comboMat.getValue();

            if(mat != null){
                materiasSeleccionadas.add(mat);
            }
        });

        root.getChildren().addAll(
                comboMat,
                botonAnadirMat
        );
        return root;
    }

    @Override
    public ObservableList<MateriaPrima> getMateriasSeleccionadas(){
        return materiasSeleccionadas;
    }
}