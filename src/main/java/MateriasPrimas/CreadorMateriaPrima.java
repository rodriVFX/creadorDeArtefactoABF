package MateriasPrimas;

import java.util.HashMap;
import java.util.Map;

public class CreadorMateriaPrima {

    public SacrificioDeObjetos crearSacrificioDeObjetos(Map<Integer, Integer> puntos, boolean conservaNivelPP){
        HashMap<Integer, Integer> niveles = new HashMap<>(puntos);
        return new SacrificioDeObjetos(niveles, conservaNivelPP);
    }

    public SacrificioDeVidas crearSacrificioDeVidas(int presencia, int natura, int gnosis, boolean esVoluntario, boolean esSobrenatural, boolean esArtificial){
        return new SacrificioDeVidas(presencia, natura, gnosis, esVoluntario, esSobrenatural, esArtificial);
    }

    public SacrificioDePoder crearSacrificioDePoder(int nivelPoder, int presencia){
        return new SacrificioDePoder(nivelPoder, presencia);
    }

    public InfusionZeon crearInfusionZeon(int zeon){
        return new InfusionZeon(zeon);
    }
}
