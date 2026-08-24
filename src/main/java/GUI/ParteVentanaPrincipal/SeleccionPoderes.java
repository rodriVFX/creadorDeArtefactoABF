package GUI.ParteVentanaPrincipal;

import Datos.RepositorioDatos;
import Poderes.Poder;
import Poderes.PoderBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SeleccionPoderes {

    private final RepositorioDatos datos = new RepositorioDatos();
    private final ObservableList<Poder> poderesSeleccionados = FXCollections.observableArrayList();

    public Node getVista() {
        VBox root = new VBox(10);
        ComboBox<PoderBase> comboPod = new ComboBox<>();
        comboPod.getItems().addAll(datos.getPoderes().listar().values());
        comboPod.setCellFactory(param -> new ListCell<>(){
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
        comboPod.setButtonCell(new ListCell<>(){
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

        Button botonAnadirPod = new Button("Añadir");
        botonAnadirPod.setOnAction(event -> {
            PoderBase base = comboPod.getValue();

            if(base == null){
                return;
            }
            VentanaAnadirPoder ventana = new VentanaAnadirPoder(base);

            List<Poder> poder = ventana.mostrar((Stage) botonAnadirPod.getScene().getWindow());
            if(poder != null){
                poderesSeleccionados.addAll(poder);
            }
        });

        TableView<Poder> tablaPod = new TableView<>();
        TableColumn<Poder, String> columnaPoder = new TableColumn<>("Poder");
        columnaPoder.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getNombre()));
        TableColumn<Poder, String> columnaOpcion = new TableColumn<>("Variante");
        columnaOpcion.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getOpcion().getNombre()));
        TableColumn<Poder, String> columnaModificadores = new TableColumn<>("Modificadores");
        columnaModificadores.setCellValueFactory(dato -> new SimpleStringProperty(String.join(", ", dato.getValue().listarModificadores())));
        TableColumn<Poder, Integer> poderPP = new TableColumn<>("Coste PP");
        poderPP.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getValue()));
        TableColumn<Poder, Integer> poderNivel = new TableColumn<>("Nivel PP");
        poderNivel.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue().getCostePP().entrySet().iterator().next().getKey()));
        tablaPod.getColumns().addAll(columnaPoder, columnaOpcion, columnaModificadores, poderPP, poderNivel);
        tablaPod.setItems(poderesSeleccionados);

        root.getChildren().addAll(
                comboPod,
                botonAnadirPod,
                tablaPod
        );
        return root;
    }

    public ObservableList<Poder> getPoderesSeleccionados(){
        return poderesSeleccionados;
    }
}