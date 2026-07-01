import java.util.List;

public class ArmaMelee extends Contenedor {

    private String tipoArma;
    private int dano;
    private int modificadorHA;
    private int velocidad;
    private int entereza;
    private int rotura;
    private String fuerzaRequerida;
    private String criticoPrimario;
    private String criticoSecundario;
    private String especializaciones;

    public ArmaMelee(){}

    public ArmaMelee(String nombre, int presenciaBase, List<String> especialidades, String tipoArma, int dano, int modificadorHA, int velocidad, int entereza, int rotura, String fuerzaRequerida, String criticoPrimario, String criticoSecundario, String especializaciones){
        super(nombre, presenciaBase, especialidades);
        this. tipoArma = tipoArma;
        this.dano = dano;
        this.modificadorHA = modificadorHA;
        this.velocidad = velocidad;
        this.entereza = entereza;
        this.rotura = rotura;
        this.fuerzaRequerida = fuerzaRequerida;
        this.criticoPrimario = criticoPrimario;
        this.criticoSecundario = criticoSecundario;
        this.especializaciones = especializaciones;
    }

    public String getTipoArma(){
        return tipoArma;
    }
    public int getDano(){
        return dano;
    }
    public int getModificadorHA() { return modificadorHA; }
    public int getVelocidad(){
        return velocidad;
    }
    public int getEntereza(){
        return entereza;
    }
    public int getRotura(){
        return rotura;
    }
    public String getFuerzaRequerida(){
        return fuerzaRequerida;
    }
    public String getCriticoPrimario(){
        return criticoPrimario;
    }
    public String getCriticoSecundario(){ return criticoSecundario; }
    public String getEspecializaciones(){ return especializaciones; }
}