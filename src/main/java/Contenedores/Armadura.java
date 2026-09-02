package Contenedores;

import Datos.Enums.TipoContenedorEnum;
import Datos.Enums.TipoDanoEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armadura extends Equipamiento{

    private Map<TipoDanoEnum, Integer> ta;
    private int requerimientoArmadura;
    private int penalizadorNatural;
    private int restriccionMov;
    private String localizacion;

    public Armadura(){}

    public Armadura(String nombre, int presenciaBase, List<String> especialidades, String tipo, int entereza, int rotura, List<String> especializaciones, Map<TipoDanoEnum, Integer> ta, int requerimientoArmadura, int penalizadorNatural, int restriccionMov, String localizacion) {
        super(nombre, presenciaBase, especialidades, tipo, entereza, rotura, especializaciones);
        this.ta = ta;
        this.requerimientoArmadura = requerimientoArmadura;
        this.penalizadorNatural = penalizadorNatural;
        this.restriccionMov = restriccionMov;
        this.localizacion = localizacion;
    }
    public Map<TipoDanoEnum, Integer> getTa() { return new HashMap<>(ta);}

    public int getRequerimientoArmadura() { return requerimientoArmadura; }
    public int getPenalizadorNatural() { return penalizadorNatural; }
    public int getRestriccionMov() { return restriccionMov; }
    public String getLocalizacion() { return localizacion; }

    public void modificarTA(TipoDanoEnum tipoDano, int modificador){

        if(ta == null){ ta = new HashMap<>(); }

        ta.put(tipoDano, ta.getOrDefault(tipoDano, 0) + modificador);
    }

    @Override
    public TipoContenedorEnum getTipoEnum(){
        return TipoContenedorEnum.ARMADURA;
    }
}
