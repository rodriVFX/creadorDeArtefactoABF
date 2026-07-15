package Datos.Repositorios;

import Contenedores.Municion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioMunicion {

    private final Map<String, Municion> municiones;

    public RepositorioMunicion() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/municiones.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró municiones.json");
            }

            List<Municion> lista = mapper.readValue(archivo, new TypeReference<List<Municion>>() {});

            municiones = new HashMap<>();

            for(Municion m : lista){
                municiones.put(m.getNombre(), m);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos de la munición.", e);
        }
    }

    public Municion getMunicion(String nombre){
        return municiones.get(nombre);
    }

    public Map<String, Municion> listar() {
        return Map.copyOf(municiones);
    }
}
