package Contenedores;

import java.util.List;

public abstract class ArmaDistancia extends Arma{

    int alcance;

    public ArmaDistancia(){}

    public ArmaDistancia(String nombre, int presenciaBase, List<String> especialidades, int modificadorHA, int ignoraTA, int velocidad, String fuerzaRequerida, String criticoPrimario, String criticoSecundario, String tipo, int entereza, int rotura, List<String> especializaciones, int alcance){
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones, modificadorHA, velocidad, fuerzaRequerida);
        this.alcance = alcance;
    }

    public int getAlcance(){ return alcance; }

}
