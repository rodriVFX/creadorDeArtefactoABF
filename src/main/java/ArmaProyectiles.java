import java.util.List;

public class ArmaProyectiles extends ArmaDistancia{

    int recarga;

    public ArmaProyectiles(){}

    public ArmaProyectiles(String nombre, int presenciaBase, List<String> especialidades, int modificadorHA, int ignoraTA, int velocidad, String fuerzaRequerida, String criticoPrimario, String criticoSecundario, String tipo, int entereza, int rotura, List<String> especializaciones, int alcance, int recarga) {
        super(nombre, presenciaBase, especialidades, modificadorHA, ignoraTA, velocidad, fuerzaRequerida, criticoPrimario, criticoSecundario, tipo, entereza, rotura, especializaciones, alcance);
        this.recarga = recarga;
    }

    public int getRecarga() { return recarga; }
}
