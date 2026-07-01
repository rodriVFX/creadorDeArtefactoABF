import java.util.List;

public class ArmaLanzable extends ArmaDistancia{

    int cadenciaFuego;

    public ArmaLanzable(){}

    public ArmaLanzable(String nombre, int presenciaBase, List<String> especialidades,int modificadorHA, int ignoraTA, int velocidad, String fuerzaRequerida, String criticoPrimario, String criticoSecundario, String tipo, int entereza, int rotura, List<String> especializaciones, int alcance, int cadenciaFuego) {
        super(nombre, presenciaBase, especialidades, modificadorHA, ignoraTA, velocidad, fuerzaRequerida, criticoPrimario, criticoSecundario, tipo, entereza, rotura, especializaciones, alcance);
        this.cadenciaFuego = cadenciaFuego;
    }

    public int getCadenciaFuego() { return cadenciaFuego; }
}
