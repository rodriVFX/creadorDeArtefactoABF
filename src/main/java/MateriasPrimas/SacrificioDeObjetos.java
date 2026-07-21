package MateriasPrimas;

import java.util.ArrayList;
import java.util.HashMap;
public class SacrificioDeObjetos extends MateriaPrima{


    public static HashMap<Integer, Integer> calcularNivelesPP(HashMap<Integer, Integer> objetoPrevio, boolean conservarNivelPP){
        ArrayList<Integer> nivelesViejos = new ArrayList<>(objetoPrevio.keySet());
        ArrayList<Integer> nivelesNuevos = new ArrayList<>();
        HashMap<Integer, Integer> valoresNuevos = new HashMap<>();
        int cantidadNiveles = objetoPrevio.size();
        int cantidadNivelesNuevos = 0;
        if(!conservarNivelPP){
            for (int i = 0; i < cantidadNiveles; i++) {
                nivelesNuevos.add(nivelesViejos.get(i) - 1);
                cantidadNivelesNuevos = nivelesNuevos.size();
            }
            for (int i = 0; i < cantidadNivelesNuevos; i++) {
                int nivel = nivelesNuevos.get(i);
                int cantidad = objetoPrevio.get(nivel+1);
                if(nivel > 0) {
                    valoresNuevos.put(nivel, cantidad);
                }
            }
        }
        else{
            int nivelMax = 0;
            for (int i = 0; i < cantidadNiveles; i++) {
                if(nivelesViejos.get(i) > nivelMax){
                    nivelMax = nivelesViejos.get(i);
                }
            }
            int cantidadNueva = objetoPrevio.get(nivelMax)/2;
            valoresNuevos.put(nivelMax, cantidadNueva);
        }
        return valoresNuevos;
    }

    public static ArrayList<String> crearReglas(){
        ArrayList<String> reglas = new ArrayList<>();
        reglas.add("En el caso de querer usar un objeto con nivel de PP superior a 3, se debe conectar de alguna forma al nuevo artefacto creado. En caso de ser de nivel 3 o inferior, se puede tratar de desmantelar el objeto original en el proceso de creación.");
        return reglas;
    }

    public SacrificioDeObjetos(HashMap<Integer, Integer> objetoPrevio, boolean conservarNivelPP) {
        super(calcularNivelesPP(objetoPrevio, conservarNivelPP), crearReglas(), "Contenedores.Objeto Sacrificado");
    }

    @Override
    public String getNombre(){
        return "Objeto sacrificado";
    }
}