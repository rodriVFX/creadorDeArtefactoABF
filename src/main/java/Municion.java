import java.util.List;

public class Municion extends Equipamiento{

    private int dano;
    private String criticoPrimario;
    private String criticoSecundario;

    public Municion(){}

    public Municion(String nombre, int presenciaBase, List<String> especialidades, String tipo, int entereza, int rotura, List<String> especializaciones, int dano, String criticoPrimario, String criticoSecundario) {
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones);
        this.dano = dano;
        this.criticoPrimario = criticoPrimario;
        this.criticoSecundario = criticoSecundario;
    }

    public int getDano() { return dano; }
    public String getCriticoPrimario() { return criticoPrimario; }
    public String getCriticoSecundario() { return criticoSecundario; }
}
