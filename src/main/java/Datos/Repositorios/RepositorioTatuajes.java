package Datos.Repositorios;

import Contenedores.Tatuaje;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioTatuajes {

    private Map<String, Tatuaje> tatuajes;

    public RepositorioTatuajes() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/tatuajes.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró tatuajes.json");
            }

            List<Tatuaje> lista = mapper.readValue(archivo, new TypeReference<List<Tatuaje>>() {});

            tatuajes = new HashMap<>();

            for(Tatuaje t : lista){
                tatuajes.put(t.getNombre(), t);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los datos del objeto.", e);
        }
    }

    public Tatuaje getObjeto(String nombre){
        return tatuajes.get(nombre);
    }
    public Map<String, Tatuaje> listar(){
        return Map.copyOf(tatuajes);
    }
}
