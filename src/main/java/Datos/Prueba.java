package Datos;

import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;
import MateriasPrimas.SacrificioDeObjetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prueba {

    public static void main(String[] args) {
        CreadorMateriaPrima creador = new CreadorMateriaPrima();
        Map<Integer, Integer> mapa = new HashMap<>();
        mapa.put(1, 100);
        MateriaPrima mat = creador.crearSacrificioDeObjetos(mapa, true);
        System.out.println(mat.getCantidadPP());
    }
}