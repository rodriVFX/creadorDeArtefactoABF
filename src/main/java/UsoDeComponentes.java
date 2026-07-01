import java.util.ArrayList;
import java.util.HashMap;

public class UsoDeComponentes extends MateriaPrima{

    public UsoDeComponentes(Componente componente) {
        super(componente.getPuntosPoder(), componente.getReglasEspeciales(), componente.getNombre());
    }
}
