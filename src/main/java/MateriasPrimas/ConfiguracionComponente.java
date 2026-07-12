package MateriasPrimas;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class ConfiguracionComponente {

    private HashMap<String, Componente> componentes;

    public ConfiguracionComponente() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/componentes.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró componentes.json");
            }

            List<Componente> lista = mapper.readValue(archivo, new TypeReference<List<Componente>>() {});

            componentes = new HashMap<>();

            for(Componente c : lista){
                componentes.put(c.getNombre(), c);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error al configurar los componentes de creación.", e);
        }
    }

    public Componente getComponente (String nombre){
        return componentes.get(nombre);
    }
}