package GUI.ParteVentanaPrincipal;

import Datos.TipoMatPrimaEnum;
import GUI.ParteVentanaPrincipal.TiposMatPrima.*;
import MateriasPrimas.MateriaPrima;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class VentanaAnadirMateria {

    private final TipoMatPrimaEnum tipoMat;
    private SelectorInterfaz selector;

    public VentanaAnadirMateria(TipoMatPrimaEnum tipoMat) {
        this.tipoMat = tipoMat;
    }

    public List<MateriaPrima> mostrar(Stage ventanaPrincipal) {
        Stage ventana = new Stage();

        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.initOwner(ventanaPrincipal);

        Label titulo = new Label("Añadir materia prima:");

        Node vista = crearVista();

        VBox root = new VBox(10);
        root.getChildren().addAll(
                titulo,
                vista
        );

        Scene scene = new Scene(root, 400, 300);
        ventana.setScene(scene);
        ventana.setTitle("Añadir materia prima");
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
