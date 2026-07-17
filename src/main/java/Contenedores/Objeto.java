package Contenedores;

import Datos.TipoContenedorEnum;

import java.util.ArrayList;
import java.util.List;

public class Objeto extends Contenedor {

    public Objeto(){}

    public Objeto(String nombre, int presenciaBase, List<String> especialidades, String tipo){
        super(nombre, presenciaBase, especialidades, tipo);
    }
}