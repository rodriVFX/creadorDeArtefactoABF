package Contenedores;

import Datos.Enums.TipoContenedorEnum;

import java.util.List;

public class ArmaLanzable extends ArmaDistancia {

    private int dano;
    private int cadenciaFuego;
    private int ignoraTA;
    private String criticoPrimario;
    private String criticoSecundario;

    public ArmaLanzable(){}

    public ArmaLanzable(int dano, String nombre, int presenciaBase, List<String> especialidades,int modificadorHA, int ignoraTA, int velocidad, String fuerzaRequerida, String tipo, int entereza, int rotura, List<String> especializaciones, int alcance, int cadenciaFuego, String criticoPrimario, String criticoSecundario) {
        super(nombre, presenciaBase, especialidades, modificadorHA, velocidad, fuerzaRequerida, tipo, entereza, rotura, especializaciones, alcance);
        this.dano = dano;
        this.cadenciaFuego = cadenciaFuego;
        this.ignoraTA = ignoraTA;
        this.criticoPrimario = criticoPrimario;
        this.criticoSecundario = criticoSecundario;
    }

    public int getDano(){
        return dano;
    }
    public int getCadenciaFuego() { return cadenciaFuego; }
    public int getIgnoraTA(){ return ignoraTA; }
    public String getCriticoPrimario(){ return criticoPrimario; }
    public String getCriticoSecundario(){ return criticoSecundario; }
    public TipoContenedorEnum getTipoEnum(){
        return TipoContenedorEnum.ARMALANZABLE;
    }
}
