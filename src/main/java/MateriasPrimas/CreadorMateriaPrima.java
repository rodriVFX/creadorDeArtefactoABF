package MateriasPrimas;

import java.util.HashMap;

public class CreadorMateriaPrima {

    public SacrificioDeObjetos crearSacrificioDeObjetos(int[] nivelesPP, int[] cantidadesPP, boolean conservaNivelPP){
        HashMap<Integer, Integer> niveles = new HashMap<>();
        for(int i : nivelesPP){
            niveles.put(i, cantidadesPP[i]);
        }
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
