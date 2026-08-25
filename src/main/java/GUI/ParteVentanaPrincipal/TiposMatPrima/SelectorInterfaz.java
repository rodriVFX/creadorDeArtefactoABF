package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.MateriaPrima;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public interface SelectorInterfaz {

    Node getVista();

    ObservableList<MateriaPrima> getMateriasSeleccionadas();
}
