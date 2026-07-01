import java.util.ArrayList;
import java.util.HashMap;

public class SacrificioDeVidas extends MateriaPrima{

    public static HashMap<Integer, Integer> calcularPP(int presenciaSacrificio, int natura, int gnosis, boolean esVoluntario, boolean esSobrenatural, boolean esArtificial) {
        HashMap<Integer, Integer> puntosPoder = new HashMap<>();

        if(presenciaSacrificio <= 0){
            throw new IllegalArgumentException("La presencia debe ser positiva.");
        }

        int cantidadPP = presenciaSacrificio * 2;
        int nivelPP;
        if (esArtificial) {
            nivelPP = 1;
        } else if (esSobrenatural) {
            nivelPP = Math.max(1, (gnosis - 15) / 5);
        } else {
            nivelPP = Math.max(natura / 5, 1);
        }

        if(esVoluntario && !esArtificial){
            nivelPP++;
        }

        puntosPoder.put(nivelPP, cantidadPP);
        return puntosPoder;
    }

    public static ArrayList<String> crearReglas(boolean esVoluntario, boolean esArtificial){
        ArrayList<String> reglas = new ArrayList<>();
        if(esVoluntario){
            reglas.add("Los sacrificios voluntarios aumentan en 1 el nivel de PP.");
        }
        if(esArtificial){
            reglas.add("Los sacrificios de criaturas cuya alma no sea natural siempre otorgarán PP de nivel 1.");
        }
        return reglas;
    }


    public SacrificioDeVidas(int presenciaSacrificio, int natura, int gnosis, boolean esVoluntario, boolean esSobrenatural, boolean esArtificial) {
        super(calcularPP(presenciaSacrificio, natura, gnosis, esVoluntario, esSobrenatural, esArtificial), crearReglas(esVoluntario, esArtificial), "Sacrificio de criatura");
    }
}