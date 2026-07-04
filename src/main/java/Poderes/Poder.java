package Poderes;

import java.util.List;

public class Poder {

    private PoderBase base;
    private OpcionPoder opcion;
    private List<ModificadorPoder> modificadores;

    public Poder(PoderBase base, OpcionPoder opcion, List<ModificadorPoder> modificadores){
        this.base = base;
        this.opcion = opcion;
        this.modificadores = modificadores;
    }
}