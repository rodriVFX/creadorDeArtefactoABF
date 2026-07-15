package Artefactos;

import MateriasPrimas.MateriaPrima;
import Poderes.Poder;
import java.util.HashMap;
import java.util.Map;

public class ValidadorArtefacto {

    public Map<Integer, Integer> calcularPPDisponibles(Artefacto artefacto) {
        Map<Integer, Integer> puntos = new HashMap<>();
        for (MateriaPrima m : artefacto.getMateriasPrimas()) {
            for (Map.Entry<Integer, Integer> entrada : m.getPuntosPoder().entrySet()) {
                puntos.merge(entrada.getKey(), entrada.getValue(), Integer::sum);
            }
        }
        return puntos;
    }

    public Map<Integer, Integer> calcularPPGastados(Artefacto artefacto){
        Map<Integer, Integer> puntos = new HashMap<>();
        for (Poder p : artefacto.getPoderes()) {
            for (Map.Entry<Integer, Integer> entrada : p.getCostePP().entrySet()) {
                puntos.merge(entrada.getKey(), entrada.getValue(), Integer::sum);
            }
        }
        return puntos;
    }

    public Map<Integer, Integer> calcularPPFinales(Artefacto artefacto){
        Map<Integer, Integer> puntosDisponibles = calcularPPDisponibles(artefacto);
        Map<Integer, Integer> puntosGastados = calcularPPGastados(artefacto);
        Map<Integer, Integer> puntosFinales = new HashMap<>();
        for(Map.Entry<Integer, Integer> entrada : puntosDisponibles.entrySet()){
            puntosFinales.put(entrada.getKey(), puntosDisponibles.get(entrada.getKey()) - puntosGastados.getOrDefault(entrada.getKey(), 0));
        }
        return puntosFinales;
    }
}