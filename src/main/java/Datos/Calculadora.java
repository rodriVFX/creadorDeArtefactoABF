package Datos;

import Artefactos.Artefacto;
import Contenedores.Contenedor;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.HashMap;
import java.util.List;
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

    public int calcularPresenciaInicial(Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaEsto) {
        int presenciaInicial = contenedor.getPresenciaBase() + material.getModificador() + calidad.getModificador();
        if (creadoParaEsto) {
            presenciaInicial += 20;
        }
        return presenciaInicial;
    }
    public int calcularPresenciaRequerida(List<Poder> poderes) {
        int presenciaRequerida = 0;
        for (Poder p : poderes) {
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
    public int calcularPresenciaFinal(Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaEsto, List<Poder> poderes){
        return calcularPresenciaInicial(contenedor, material, calidad, creadoParaEsto) + calcularPresenciaRequerida(poderes);
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
