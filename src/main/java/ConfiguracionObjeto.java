import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionObjeto {

    private Map<String, Objeto> objetos;

    public ConfiguracionObjeto() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/objetos.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró objetos.json");
            }

            List<Objeto> lista = mapper.readValue(archivo, new TypeReference<List<Objeto>>() {});

            objetos = new HashMap<>();

            for(Objeto c : lista){
                objetos.put(c.getNombre(), c);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos del objeto.", e);
        }
    }

    public Objeto getObjeto(String nombre){
        return objetos.get(nombre);
    }
}