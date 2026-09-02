package Datos;

import Datos.Repositorios.RepositorioPoder;
import Poderes.PoderBase;


public class Prueba {

    public static void main(String[] args) {

        RepositorioPoder rep = new RepositorioPoder();

        PoderBase pod = rep.getPoder("Ataque Especial");

        System.out.println(pod.getNombre());
        System.out.println(pod.getOpciones());
        System.out.println(pod.getModificadores());
        System.out.println(pod.getContenedoresPermitidos());
    }
}
