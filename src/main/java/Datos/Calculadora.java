package Datos;

import Artefactos.Artefacto;
import Contenedores.Contenedor;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.*;

public class Calculadora {
    Scanner obj = new Scanner(System.in);

    public Map<Integer, Integer> calcularPPRestantes(Artefacto artefacto) {
        Map<Integer, Integer> puntos = calcularPPDisponibles(artefacto);
        for(Poder p : artefacto.getPoderes()){
            for(Map.Entry<Integer, Integer> coste : p.getCostePP().entrySet()){
                int nivel = coste.getKey();
                int cantidad = coste.getValue();

                for(int i = 0; i < cantidad; i++){
                    if(!consumirPP(puntos, nivel)){
                        throw new IllegalArgumentException("No hay suficientes PP de nivel " + nivel);
                    }
                }
            }
        }
        return puntos;
    }

    public int calcularPresenciaInicial(Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaEsto) {
        int presenciaInicial = contenedor.getPresenciaBase() + material.getModificador() + calidad.getModificador();
        if (creadoParaEsto) {
            presenciaInicial += 20;
        }
        return presenciaInicial;
    }
    public int calcularPresenciaDisponible(Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaEsto, List<Poder> poderes) {
        int presencia = calcularPresenciaInicial(contenedor, material, calidad, creadoParaEsto);
        List<Integer> especialidades = new ArrayList<>();
        for (Poder p : poderes) {
            if (contenedor.getEspecialidades().contains(p.getNombre())) {
                especialidades.add(1);
            } else {
                especialidades.add(0);
            }
        }
        if (especialidades.contains(1)) {
            presencia += 20;
        }
        return presencia;
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
    private boolean consumirPP(Map<Integer, Integer> puntos, int nivel) {

        //Tengo PP del nivel que necesito?
        if (puntos.getOrDefault(nivel, 0) > 0) {

            puntos.put(nivel, puntos.get(nivel) - 1);

            if(puntos.get(nivel) == 0){
                puntos.remove(nivel);
            }
            return true;
        }
        //Estoy en el nivel máximo?
        if(nivel == 5){
            return false;
        }
        //Intento obtener del nivel superior
        int nivelSuperior = nivel + 1;
        if (!consumirPP(puntos, nivelSuperior)) {
            return false;
        }

        //El PP de nivel superior ya ha sido consumido
        //Genero los dos puntos del nivel actual
        puntos.merge(nivel, 2, Integer::sum);

        //Y ahora gasto un punto
        puntos.put(nivel, puntos.get(nivel) - 1);
        if(puntos.get(nivel) == 0){
            puntos.remove(nivel);
        }
        return true;
    }
}
