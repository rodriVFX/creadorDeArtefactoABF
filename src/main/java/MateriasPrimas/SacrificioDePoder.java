package MateriasPrimas;

import java.util.ArrayList;
import java.util.HashMap;

public class SacrificioDePoder extends MateriaPrima{

    public static HashMap<Integer, Integer> ppSacrificioPoder(int nivelPOD, int presenciaUsuario){
        int nivelPP;
        if(nivelPOD > 5){
            nivelPP = switch(nivelPOD){
                case 6,7 -> 1;
                case 8,9 -> 2;
                case 10,11,12,13 -> 3;
                case 14,15 -> 4;
                default -> 5;
            };
        }
        else {throw new IllegalArgumentException("Necesitas al menos 6 puntos del atributo POD.");}

        double cantidadPP = (double) presenciaUsuario / (double) nivelPP;
        cantidadPP = (Math.floor(cantidadPP/5)) * 5;
        HashMap<Integer, Integer> ppSacrificio = new HashMap<>();
        ppSacrificio.put(nivelPP, (int) (cantidadPP));
        return ppSacrificio;
    }

    public static ArrayList<String> crearReglas(){
        ArrayList<String> reglas = new ArrayList<>();
        reglas.add("El nivel de PP obtenido del sacrificio de POD depende directamente del POD del usuario, requiriendo como mínimo de 5 puntos para su ejecución.");
        return reglas;
    }

    public SacrificioDePoder(int nivelPOD, int presenciaUsuario){
        super(ppSacrificioPoder(nivelPOD, presenciaUsuario), crearReglas(), "Sacrificio de POD");
    }

    @Override
    public String getNombre(){
        return "Sacrificio de POD";
    }
}