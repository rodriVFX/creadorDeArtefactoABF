import java.util.List;

public abstract class Arma extends Equipamiento{

    private int modificadorHA;
    private int velocidad;
    private String fuerzaRequerida;

    public Arma(){}

    public Arma(String nombre, int presenciaBase, List<String> especialidades, String tipo, int entereza, int rotura, List<String> especializaciones, int modificadorHA, int velocidad, String fuerzaRequerida){
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones);
        this.modificadorHA = modificadorHA;
        this.velocidad = velocidad;
        this.fuerzaRequerida = fuerzaRequerida;
    }

    public int getModificadorHA() { return modificadorHA; }
    public int getVelocidad(){
        return velocidad;
    }
    public String getFuerzaRequerida(){
        return fuerzaRequerida;
    }
}
