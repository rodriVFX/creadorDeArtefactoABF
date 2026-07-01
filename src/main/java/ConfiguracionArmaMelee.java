import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionArmaMelee {

    private Map<String, ArmaMelee> armasMelee;

    public ConfiguracionArmaMelee() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/armasMelee.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró armasMelee.json");
            }

            List<ArmaMelee> lista = mapper.readValue(archivo, new TypeReference<List<ArmaMelee>>() {});

            armasMelee = new HashMap<>();

            for(ArmaMelee a : lista){
                armasMelee.put(a.getNombre(), a);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos del arma.", e);
        }
    }

    public ArmaMelee getArmaMelee(String nombre){
        return armasMelee.get(nombre);
    }
}