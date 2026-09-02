package Poderes;

import Datos.Enums.TipoContenedorEnum;

import java.util.ArrayList;
import java.util.List;

public class PoderBase {

    private String nombre;
    private String faceta;
    private String descripcion;
    private List<TipoContenedorEnum> contenedoresPermitidos;
    private List<OpcionPoder> opciones = new ArrayList<>();
    private List<ModificadorPoder> modificadores = new ArrayList<>();

    public PoderBase(){}

    public String getNombre(){return nombre;}
    public String getFaceta(){return faceta;}
    public String getDescripcion(){return descripcion;}
    public List<TipoContenedorEnum> getContenedoresPermitidos(){
        return contenedoresPermitidos;
    }
    public List<OpcionPoder> getOpciones(){return new ArrayList<>(opciones);}
    public List<ModificadorPoder> getModificadores(){return new ArrayList<>(modificadores);}

}