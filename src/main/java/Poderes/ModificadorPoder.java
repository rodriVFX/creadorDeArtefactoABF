package Poderes;

public class ModificadorPoder {

    private String nombre;
    private int modificadorCoste;
    private int modificadorNivel;
    private String descripcion;

    public ModificadorPoder(){}

    public ModificadorPoder(String nombre, int modificadorCoste, int modificadorNivel, String descripcion) {
        this.nombre = nombre;
        this.modificadorCoste = modificadorCoste;
        this.modificadorNivel = modificadorNivel;
        this.descripcion = descripcion;
    }

    public String getNombre(){return nombre;}
    public int getModificadorCoste(){return modificadorCoste;}
    public int getModificadorNivel(){return modificadorNivel;}
    public String getDescripcion(){return descripcion;}
}