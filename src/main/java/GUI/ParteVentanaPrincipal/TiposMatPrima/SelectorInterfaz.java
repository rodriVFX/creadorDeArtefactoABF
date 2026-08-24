package GUI.ParteVentanaPrincipal.TiposMatPrima;

import MateriasPrimas.MateriaPrima;
import javafx.scene.Node;

import java.util.List;

public interface SelectorInterfaz {

    Node getVista();

    List<MateriaPrima> getMateriasSeleccionadas();
}
