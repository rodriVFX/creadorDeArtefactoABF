import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Componente {

    private HashMap<Integer, Integer> puntosPoder;
    private ArrayList<String> reglasEspeciales;
    private String nombre;

    public Componente(){}

    public Componente(String nombre, int nivelPP, int cantidadPP, List<String> reglas){
        this.nombre = nombre;

        this.puntosPoder = new HashMap<>();
        this.puntosPoder.put(nivelPP, cantidadPP);

        this.reglasEspeciales = new ArrayList<>(reglas);
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<Integer, Integer> getPuntosPoder(){
        return puntosPoder;
    }

    public ArrayList<String> getReglasEspeciales(){
        return reglasEspeciales;
    }

}