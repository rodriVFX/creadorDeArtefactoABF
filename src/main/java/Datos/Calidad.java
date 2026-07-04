package Datos;

public class Calidad {
    private String nombre;
    private int modificador;

    public Calidad() {}

    public Calidad(String nombre, int modificador){
        this.nombre = nombre;
        this.modificador = modificador;
    }

    public String getNombre(){
        return nombre;
    }

    public int getModificador(){
        return modificador;
    }
}
