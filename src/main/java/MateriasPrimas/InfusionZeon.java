package MateriasPrimas;

import java.util.ArrayList;
import java.util.HashMap;

public class InfusionZeon extends MateriaPrima{

    public  static HashMap<Integer, Integer> calcularPP(int zeon){
        HashMap<Integer, Integer> zeonPP = new HashMap<>();
        zeonPP.put(1, zeon/5);
        return zeonPP;
    }
    public static ArrayList<String> crearReglas(){
        ArrayList<String> reglas = new ArrayList<>();
        reglas.add("Los objetos creados a partir de Zeon tienen la regla Extinguible.");
        return reglas;
    }


    public InfusionZeon (int zeon){
        super(calcularPP(zeon), crearReglas(), "Zeón");
    }

    @Override
    public String getNombre(){
        return "Infusión de zeón";
    }
}