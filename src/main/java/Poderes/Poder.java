package Poderes;

import java.util.ArrayList;
import java.util.List;

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

    public int getCostePP(){
        int costePP = opcion.getCostePP();
        for(ModificadorPoder mod : modificadores){
                costePP += mod.getModificadorCoste();
        }
        return costePP;
    }
    public int getNivelPP(){
        int nivelPP = opcion.getNivel();
        for(ModificadorPoder mod : modificadores){
                nivelPP += mod.getModificadorNivel();
        }
        return nivelPP;
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