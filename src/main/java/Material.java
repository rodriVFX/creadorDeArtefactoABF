public class Material {
    private String nombre;
    private int modificador;

    public Material() {}

    public Material(String nombre, int modificador){
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
