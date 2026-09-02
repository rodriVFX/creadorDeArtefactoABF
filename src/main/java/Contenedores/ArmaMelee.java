package Contenedores;

import Contenedores.Interfaces.HaceDano;
import Datos.Enums.TipoContenedorEnum;

import java.util.List;

public class ArmaMelee extends Arma implements HaceDano {

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

    @Override
    public TipoContenedorEnum getTipoEnum(){
        return TipoContenedorEnum.ARMAMELEE;
    }
}