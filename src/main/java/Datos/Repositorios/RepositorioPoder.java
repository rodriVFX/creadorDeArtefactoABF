package Datos.Repositorios;

import Poderes.PoderBase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioPoder {

    private final Map<String, PoderBase> poderes;

    public RepositorioPoder() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream archivo = getClass().getResourceAsStream("/poderes.json");
            if (archivo == null) {
                throw new IllegalStateException("No se encontró poderes.json");
            }

            List<PoderBase> lista = mapper.readValue(archivo, new TypeReference<List<PoderBase>>() {});

            poderes = new HashMap<>();

            for(PoderBase poder : lista){
                poderes.put(poder.getNombre(), poder);
            }

        } catch(Exception e) {
            throw new RuntimeException("Error cargando los poderes.", e);
        }
    }

    public PoderBase getPoder(String nombre){
        return poderes.get(nombre);
    }

    public Map<String, PoderBase> listar() {return Map.copyOf(poderes);}
}