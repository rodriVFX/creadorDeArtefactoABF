import java.util.ArrayList;
import java.util.List;

public abstract class Contenedor {

    private String nombre;
    private int presenciaBase;
    private List<String> especialidades;

    public Contenedor(){}

    public Contenedor(String nombre, int presenciaBase, List<String> especialidades){
        this.nombre = nombre;
        this.presenciaBase = presenciaBase;
        this.especialidades = especialidades;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPresenciaBase(){
        return presenciaBase;
    }

    public List<String> getEspecialidades(){
        return new ArrayList<>(especialidades);
    }
}