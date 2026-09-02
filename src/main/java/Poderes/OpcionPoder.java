package Poderes;

public class OpcionPoder {

    private String nombre;
    private int costePP;
    private int nivel;
    private String descripcion;

    public OpcionPoder(){}

    public OpcionPoder(String nombre, int costePP, int nivel, String descripcion){
        this.nombre = nombre;
        this. costePP = costePP;
        this.nivel = nivel;
        this.descripcion = descripcion;
    }

    public String getNombre() {return nombre;}
    public int getCostePP(){return costePP;}
    public int getNivel(){return nivel;}
    public String getDescripcion(){return descripcion;}
}