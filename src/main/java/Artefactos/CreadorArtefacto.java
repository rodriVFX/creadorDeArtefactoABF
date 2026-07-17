package Artefactos;

import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.List;

public class CreadorArtefacto {

    public Artefacto crearArtefacto(Contenedor contenedor, Material material, Calidad calidad, List<MateriaPrima> materiasPrimas, List<Poder> poderes){
        return new Artefacto(contenedor, material, calidad, calcularPresenciaInicial(contenedor, material, calidad), materiasPrimas, poderes);
    }

    private int calcularPresenciaInicial(Contenedor contenedor, Material material, Calidad calidad){
        return contenedor.getPresenciaBase() + material.getModificador() + calidad.getModificador();
    }
}
