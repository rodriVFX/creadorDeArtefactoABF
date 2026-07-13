package Contenedores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionArmaLanzable {

    private final Map<String, ArmaLanzable> armasLanzables;

    public ConfiguracionArmaLanzable() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/armasLanzables.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró armasLanzables.json");
            }

            List<ArmaLanzable> lista = mapper.readValue(archivo, new TypeReference<List<ArmaLanzable>>() {});

            armasLanzables = new HashMap<>();

            for(ArmaLanzable a : lista){
                armasLanzables.put(a.getNombre(), a);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos del arma.", e);
        }
    }

    public ArmaLanzable getArmaLanzable(String nombre){
        return armasLanzables.get(nombre);
    }

    public Map<String, ArmaLanzable> getArmasLanzables() {
        return Map.copyOf(armasLanzables);
    }
}
