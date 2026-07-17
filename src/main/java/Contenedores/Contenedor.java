package Contenedores;

import Datos.TipoContenedorEnum;

import java.util.ArrayList;
import java.util.List;

public abstract class Contenedor {

    private String nombre;
    private int presenciaBase;
    private List<String> especialidades;
    private String tipo;

    public Contenedor(){}

    public Contenedor(String nombre, int presenciaBase, List<String> especialidades, String tipo){
        this.nombre = nombre;
        this.presenciaBase = presenciaBase;
        this.especialidades = especialidades;
        this.tipo = tipo;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPresenciaBase(){
        return presenciaBase;
    }

    public List<String> getEspecialidades(){
        return new ArrayList<>(especialidades);
    }

    public String getTipo(){
        return tipo;
    }
}