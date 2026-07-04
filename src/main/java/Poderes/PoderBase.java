package Poderes;

import java.util.ArrayList;
import java.util.List;

public class PoderBase {

    private String nombre;
    private List<OpcionPoder> opciones = new ArrayList<>();
    private List<ModificadorPoder> modificadores = new ArrayList<>();

    public PoderBase(){}

    public String getNombre(){return nombre;}
    public List<OpcionPoder> getOpciones(){return new ArrayList<>(opciones);}
    public List<ModificadorPoder> getModificadores(){return new ArrayList<>(modificadores);}

}