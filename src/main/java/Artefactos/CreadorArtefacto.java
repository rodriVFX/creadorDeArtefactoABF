package Artefactos;

import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import Datos.Repositorios.RepositorioArtefacto;

public class CreadorArtefacto {

    public Artefacto crearArtefacto(Contenedor contenedor, Material material, Calidad calidad, RepositorioArtefacto config){
        return new Artefacto(contenedor, material, calidad, config);
    }
}
