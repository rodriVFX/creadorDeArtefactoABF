package Datos.Repositorios;

import Contenedores.Armadura;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioArmadura {

    private final Map<String, Armadura> armaduras;

    public RepositorioArmadura() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/armaduras.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró armaduras.json");
            }

            List<Armadura> lista = mapper.readValue(archivo, new TypeReference<List<Armadura>>() {});

            armaduras = new HashMap<>();

            for(Armadura a : lista){
                armaduras.put(a.getNombre(), a);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos de la armadura.", e);
        }
    }

    public Armadura getArmadura(String nombre){
        return armaduras.get(nombre);
    }

    public Map<String, Armadura> listar() {
        return Map.copyOf(armaduras);
    }
}
