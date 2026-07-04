import Datos.Calidad;
import Datos.Material;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracionArtefacto {

    private Map<String, Material> materiales;
    private Map<String, Calidad> calidades;

    public ConfiguracionArtefacto(){

        ObjectMapper mapper = new ObjectMapper();

        try{
            InputStream archivoMateriales = getClass().getResourceAsStream("/materiales.json");
            if (archivoMateriales == null) {
                throw new IllegalStateException("No se encontró materiales.json");
            }

            List<Material> listaMateriales = mapper.readValue(archivoMateriales, new TypeReference<List<Material>>() {});

            materiales = new HashMap<>();
            for(Material material : listaMateriales){
                materiales.put(material.getNombre(), material);
            }

            InputStream archivoCalidades = getClass().getResourceAsStream("/calidades.json");
            if (archivoCalidades == null) {
                throw new IllegalStateException("No se encontró calidades.json");
            }
            List<Calidad> listaCalidades = mapper.readValue(archivoCalidades, new TypeReference<List<Calidad>>() {});

            calidades = new HashMap<>();
            for(Calidad calidad : listaCalidades){
                calidades.put(calidad.getNombre(), calidad);
            }
        }
        catch(Exception e) {
            throw new RuntimeException("Error cargando la configuración de artefactos.", e);
        }
    }

    public Material getMaterial(String nombre){
        return materiales.get(nombre);
    }

    public Calidad getCalidad (String nombre){
        return calidades.get(nombre);
    }
}