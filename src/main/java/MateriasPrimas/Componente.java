package MateriasPrimas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Componente extends MateriaPrima{

    public Componente(){}

    public Componente(String nombre, int nivelPP, int cantidadPP, List<String> reglas){
        super(Map.of(nivelPP, cantidadPP), new ArrayList<>(reglas), nombre);
    }

}