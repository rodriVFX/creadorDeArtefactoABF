package Datos;

import Artefactos.Artefacto;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculadora {
    Scanner obj = new Scanner(System.in);


    public Map<Integer, Integer> calcularPPRestantes(Artefacto artefacto) {
        Map<Integer, Integer> puntosDisponibles = calcularPPDisponibles(artefacto);
        Map<Integer, Integer> puntosGastados = calcularPPGastados(artefacto);
        Map<Integer, Integer> puntosRestantes = new HashMap<>();
        for (Map.Entry<Integer, Integer> entrada : puntosDisponibles.entrySet()) {
            puntosRestantes.put(entrada.getKey(), puntosDisponibles.get(entrada.getKey()) - puntosGastados.getOrDefault(entrada.getKey(), 0));
        }
        return puntosRestantes;
    }
    public boolean creadoParaArtefacto() {
        System.out.println("¿El contenedor ha sido fabricado durante la creación del artefacto?");
        return obj.hasNext();
    }
    public int calcularPresenciaInicial(Artefacto artefacto, boolean creadoParaEsto) {
        int presenciaInicial = artefacto.getPresencia();
        if (creadoParaEsto) {
            presenciaInicial += 20;
        }
        return presenciaInicial;
    }
    public int calcularPresenciaRequerida(Artefacto artefacto) {
        int presenciaRequerida = 0;
        for (Poder p : artefacto.getPoderes()) {
            presenciaRequerida += switch (p.getOpcion().getNivel()) {
                case 1 -> 10;
                case 2 -> 15;
                case 3 -> 25;
                case 4 -> 60;
                case 5 -> 100;
                default -> throw new IllegalArgumentException("Error al calcular la presencia de los poderes.");
            };
        }
        return presenciaRequerida;
    }
    public int calcularPresenciaFinal(Artefacto artefacto){
        return calcularPresenciaInicial(artefacto, creadoParaArtefacto()) + calcularPresenciaRequerida(artefacto);
    }

    private Map<Integer, Integer> calcularPPDisponibles(Artefacto artefacto) {
        Map<Integer, Integer> puntos = new HashMap<>();
        for (MateriaPrima m : artefacto.getMateriasPrimas()) {
            for (Map.Entry<Integer, Integer> entrada : m.getPuntosPoder().entrySet()) {
                puntos.merge(entrada.getKey(), entrada.getValue(), Integer::sum);
            }
        }
        return puntos;
    }
    private Map<Integer, Integer> calcularPPGastados(Artefacto artefacto) {
        Map<Integer, Integer> puntos = new HashMap<>();
        for (Poder p : artefacto.getPoderes()) {
            for (Map.Entry<Integer, Integer> entrada : p.getCostePP().entrySet()) {
                puntos.merge(entrada.getKey(), entrada.getValue(), Integer::sum);
            }
        }
        return puntos;
    }

}
