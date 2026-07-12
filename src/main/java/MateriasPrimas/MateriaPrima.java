package MateriasPrimas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MateriaPrima {
    private Map<Integer, Integer> puntosPoder;
    private List<String> reglasEspeciales;
    private String nombre;

    protected MateriaPrima(){}

    public MateriaPrima(Map<Integer, Integer> puntosPoder, List<String> reglasEspeciales, String nombre){
        this.puntosPoder = new HashMap<>(puntosPoder);
        this.reglasEspeciales = new ArrayList<>(reglasEspeciales);
        this.nombre = nombre;
    }

    public Map<Integer, Integer> getPuntosPoder(){
        return new HashMap<>(puntosPoder);
    }
    public List<String> getReglasEspeciales(){
        return new ArrayList<>(reglasEspeciales);
    }
    public String getNombre(){
        return nombre;
    }
}