import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionArmaProyectiles {

    private final Map<String, ArmaProyectiles> armasProyectiles;

    public ConfiguracionArmaProyectiles() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/armasProyectiles.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró armasProyectiles.json");
            }

            List<ArmaProyectiles> lista = mapper.readValue(archivo, new TypeReference<List<ArmaProyectiles>>() {});

            armasProyectiles = new HashMap<>();

            for(ArmaProyectiles a : lista){
                armasProyectiles.put(a.getNombre(), a);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos del arma.", e);
        }
    }

    public ArmaProyectiles getArmaProyectiles(String nombre){
        return armasProyectiles.get(nombre);
    }
}
