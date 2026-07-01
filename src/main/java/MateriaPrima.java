import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MateriaPrima {
    private final Map<Integer, Integer> puntosPoder;
    private final List<String> reglasEspeciales;
    private final String origen;

    public MateriaPrima(Map<Integer, Integer> puntosPoder, List<String> reglasEspeciales, String origen){
        this.puntosPoder = new HashMap<>(puntosPoder);
        this.reglasEspeciales = new ArrayList<>(reglasEspeciales);
        this.origen = origen;
    }

    public Map<Integer, Integer> getPuntosPoder(){
        return new HashMap<>(puntosPoder);
    }
    public List<String> getReglasEspeciales(){
        return new ArrayList<>(reglasEspeciales);
    }
    public String getOrigen(){
        return origen;
    }
}