package Artefactos;

import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.ModificadorPoder;
import Poderes.Poder;
import java.util.ArrayList;
import java.util.List;

public class Artefacto {
    private final String nombre;
    private final Contenedor contenedor;
    private final Material material;
    private final Calidad calidad;
    private final boolean creadoParaArtefacto;
    private final List<Poder> poderes;
    private final List<MateriaPrima> materiasPrimas;

    public Artefacto(String nombre, Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaArtefacto, List<MateriaPrima> materiasPrimas, List<Poder> poderes){
        this.nombre = nombre;
        this.contenedor = contenedor;
        this.material = material;
        this.calidad = calidad;
        this.creadoParaArtefacto = creadoParaArtefacto;
        this.poderes = new ArrayList<>(poderes);
        this.materiasPrimas = new ArrayList<>(materiasPrimas);
    }

    public String getNombre(){
        return nombre;
    }
    public Contenedor getContenedor(){
        return contenedor;
    }
    public Material getMaterial(){
        return material;
    }
    public Calidad getCalidad(){
        return calidad;
    }
    public List<MateriaPrima> getMateriasPrimas(){
        return new ArrayList<>(materiasPrimas);
    }
    public List<Poder> getPoderes(){
        return new ArrayList<>(poderes);
    }
    public boolean getCreado(){
        return creadoParaArtefacto;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==========\n");
        sb.append("Artefacto\n");
        sb.append("==========\n\n");

        sb.append("Contenedor = ").append(contenedor.getNombre()).append("\n");
        sb.append("Material = ").append(material.getNombre()).append("\n");
        sb.append("Calidad = ").append(calidad.getNombre()).append("\n\n");

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
                sb.append(": ").append(p.getOpcion().getNombre());
                for (ModificadorPoder m : p.getModificadores()){
                sb.append(" (").append(m.getNombre()).append(") ");
                }
            sb.append("\n");
            }
        }

        return sb.toString();
    }
}