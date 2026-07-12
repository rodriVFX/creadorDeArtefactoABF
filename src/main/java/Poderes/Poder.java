package Poderes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poder {

    private PoderBase base;
    private OpcionPoder opcion;
    private List<ModificadorPoder> modificadores;

    public Poder(PoderBase base, OpcionPoder opcion, List<ModificadorPoder> modificadores){
        this.base = base;
        this.opcion = opcion;
        if(!base.getOpciones().contains(opcion)){
            throw new IllegalArgumentException("La opción elegida no pertenece al poder.");
        }
        this.modificadores = new ArrayList<>(modificadores);
    }

    public Map<Integer, Integer> getCostePP(){
        int costePP = opcion.getCostePP();
        int nivelPP = opcion.getNivel();
        Map<Integer, Integer> puntos = new HashMap<>();
        for(ModificadorPoder mod : modificadores){
                costePP += mod.getModificadorCoste();
                nivelPP += mod.getModificadorNivel();
        }
        puntos.put(nivelPP, costePP);
        return puntos;
    }

    public String getNombre(){
    return base.getNombre();
    }
    public OpcionPoder getOpcion(){
        return opcion;
    }
    public List<ModificadorPoder> getModificadores(){
        return new ArrayList<>(modificadores);
    }
}