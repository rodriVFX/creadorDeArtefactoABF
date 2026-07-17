package Artefactos;

import Datos.TipoContenedorEnum;
import MateriasPrimas.MateriaPrima;
import Poderes.ModificadorPoder;
import Poderes.Poder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidadorArtefacto {

    public Map<Integer, Integer> calcularPPRestantes(Artefacto artefacto){
        Map<Integer, Integer> puntosDisponibles = calcularPPDisponibles(artefacto);
        Map<Integer, Integer> puntosGastados = calcularPPGastados(artefacto);
        Map<Integer, Integer> puntosRestantes = new HashMap<>();
        for(Map.Entry<Integer, Integer> entrada : puntosDisponibles.entrySet()){
            puntosRestantes.put(entrada.getKey(), puntosDisponibles.get(entrada.getKey()) - puntosGastados.getOrDefault(entrada.getKey(), 0));
        }
        return puntosRestantes;
    }
    public void validadorCompatibilidad(Artefacto artefacto, List<Poder> poderes){
        if(!poderes.isEmpty()) {

            }
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
    private Map<Integer, Integer> calcularPPGastados(Artefacto artefacto){
        Map<Integer, Integer> puntos = new HashMap<>();
        for (Poder p : artefacto.getPoderes()) {
            for (Map.Entry<Integer, Integer> entrada : p.getCostePP().entrySet()) {
                puntos.merge(entrada.getKey(), entrada.getValue(), Integer::sum);
            }
        }
        return puntos;
    }
    private boolean compatibilidadArmas(Artefacto artefacto){
        TipoContenedorEnum tipoContenedor = TipoContenedorEnum.valueOf(artefacto.getContenedor().getTipo());
        List<TipoContenedorEnum> tiposPermitidos = new ArrayList<>();
        List<Integer> permitidos = new ArrayList<>();
        boolean permitido = false;
        for(Poder p : artefacto.getPoderes()) {
            tiposPermitidos.addAll(p.getBase().getContenedoresPermitidos());
            if (tiposPermitidos.contains(tipoContenedor)) {
                permitidos.add(1);
            } else {
                permitidos.add(0);
            }
        }
        if(permitidos.contains(0)){
            permitido = true;
        }
        return permitido;
    }

}