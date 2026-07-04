import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.ArrayList;
import java.util.List;

public class Artefacto {
    private Contenedor contenedor;
    private String material;
    private String calidad;
    private int presencia;
    private List<Poder> poderes;
    private List<MateriaPrima> materiasPrimas;

    public Artefacto(Contenedor contenedor, String material, String calidad, ConfiguracionArtefacto config){
        this.contenedor = contenedor;
        this.material = material;
        this.calidad = calidad;

        poderes = new ArrayList<>();
        materiasPrimas = new ArrayList<>();

        calcularPresenciaInicial(config);
    }

    private void calcularPresenciaInicial(ConfiguracionArtefacto config){

        Material mat = config.getMaterial(material);
        Calidad cal = config.getCalidad(calidad);

        presencia = contenedor.getPresenciaBase() + mat.getModificador() + cal.getModificador();
    }

    public int getPresencia(){
        return presencia;
    }

    public Contenedor getContenedor(){
        return contenedor;
    }

    public List<Poder> getPoderes(){
        return new ArrayList<>(poderes);
    }

    public List<MateriaPrima> getMateriasPrimas(){
        return new ArrayList<>(materiasPrimas);
    }
}