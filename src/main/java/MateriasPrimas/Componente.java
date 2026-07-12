package MateriasPrimas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Componente extends MateriaPrima{

    public Componente(){}

    public Componente(String nombre, int nivelPP, int cantidadPP, List<String> reglas){
        super(new HashMap<>(nivelPP, cantidadPP), new ArrayList<>(reglas), nombre);
    }

}