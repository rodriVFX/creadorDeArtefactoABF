import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.ModificadorPoder;
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

    public Artefacto(Contenedor contenedor, Material material, Calidad calidad, ConfiguracionArtefacto config){
        this.contenedor = contenedor;
        this.material = material.getNombre();
        this.calidad = calidad.getNombre();

        poderes = new ArrayList<>();
        materiasPrimas = new ArrayList<>();

        calcularPresenciaInicial(config);
    }

    private void calcularPresenciaInicial(ConfiguracionArtefacto config){

        Material mat = config.getMaterial(material);
        Calidad cal = config.getCalidad(calidad);

        presencia = contenedor.getPresenciaBase() + mat.getModificador() + cal.getModificador();
    }

    public void anadirPoder(Poder poder){poderes.add(poder);}

    public void anadirMateriaPrima(MateriaPrima mat){materiasPrimas.add(mat);}

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==========\n");
        sb.append("Artefacto\n");
        sb.append("==========\n\n");

        sb.append("Contenedor = ").append(contenedor.getNombre()).append("\n");
        sb.append("Material = ").append(material).append("\n");
        sb.append("Calidad = ").append(calidad).append("\n");
        sb.append("Presencia = ").append(presencia).append("\n\n");

        sb.append("Materias primas\n");
        sb.append("--------------------\n");
        if(materiasPrimas.isEmpty()){sb.append("Ninguna\n");}
        else{for (MateriaPrima m:materiasPrimas){sb.append("- ").append(m.getNombre()).append("\n");}}

        sb.append("\nPoderes\n");
        sb.append("--------------------\n");

        if (poderes.isEmpty()) {
            sb.append("Ninguno");
        } else {
            for (Poder p : poderes) {
                sb.append("- ").append(p.getNombre());
                for (ModificadorPoder m : p.getModificadores()){
                sb.append(" (").append(m.getNombre()).append(") ");
                }
            sb.append("\n");
            }
        }

        return sb.toString();
    }
}