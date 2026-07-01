import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armadura extends Equipamiento{

    private Map<String, Integer> ta;
    private int requerimientoArmadura;
    private int penalizadorNatural;
    private int restriccionMov;
    private String localizacion;

    public Armadura(){}

    public Armadura(String nombre, int presenciaBase, List<String> especialidades, String tipo, int entereza, int rotura, List<String> especializaciones, Map<String, Integer> ta, int requerimientoArmadura, int penalizadorNatural, int restriccionMov, String localizacion) {
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones);
        this.ta = ta;
        this.requerimientoArmadura = requerimientoArmadura;
        this.penalizadorNatural = penalizadorNatural;
        this.restriccionMov = restriccionMov;
        this.localizacion = localizacion;
    }

    public Map<String, Integer> getTa() { return new HashMap<>(ta);}
    public int getRequerimientoArmadura() { return requerimientoArmadura; }
    public int getPenalizadorNatural() { return penalizadorNatural; }
    public int getRestriccionMov() { return restriccionMov; }
    public String getLocalizacion() { return localizacion; }

    public void modificarTA(String tipoDano, int modificador){

        if(ta == null){ ta = new HashMap<>(); }

        ta.put(tipoDano, ta.getOrDefault(tipoDano, 0)+ modificador);
    }
}
