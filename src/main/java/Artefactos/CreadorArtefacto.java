package Artefactos;

import Contenedores.Contenedor;
import Datos.Calculadora;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

import java.util.List;

public class CreadorArtefacto {

    Calculadora calc = new Calculadora();

    public Artefacto crearArtefacto(Contenedor contenedor, Material material, Calidad calidad, boolean creadoParaArtefacto, List<MateriaPrima> materiasPrimas, List<Poder> poderes){
        return new Artefacto(contenedor, material, calidad, creadoParaArtefacto, materiasPrimas, poderes);
    }
}
