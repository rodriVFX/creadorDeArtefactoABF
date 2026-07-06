import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;

public class CreadorArtefacto {

    public Artefacto crearArtefacto(Contenedor contenedor, Material material, Calidad calidad, ConfiguracionArtefacto config){
        return new Artefacto(contenedor, material, calidad, config);
    }
}
