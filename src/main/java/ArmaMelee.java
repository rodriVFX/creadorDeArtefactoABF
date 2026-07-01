import java.util.List;

public class ArmaMelee extends Arma {

    private int dano;
    private int ignoraTA;
    private String criticoPrimario;
    private String criticoSecundario;

    public ArmaMelee(){}

    public ArmaMelee(String nombre, int presenciaBase, List<String> especialidades, int dano, int modificadorHA, int ignoraTA, int velocidad, String fuerzaRequerida, String criticoPrimario, String criticoSecundario, String tipo, int entereza, int rotura, List<String> especializaciones){
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones, modificadorHA, velocidad, fuerzaRequerida);
        this.dano = dano;
        this.ignoraTA = ignoraTA;
        this.criticoPrimario = criticoPrimario;
        this.criticoSecundario = criticoSecundario;
    }


    public int getDano(){
        return dano;
    }
    public int getIgnoraTA() { return ignoraTA; }
    public String getCriticoPrimario(){
        return criticoPrimario;
    }
    public String getCriticoSecundario(){ return criticoSecundario; }
}