package Contenedores;

import Datos.Enums.TipoContenedorEnum;

import java.util.List;

public class Tatuaje extends Contenedor{

    public Tatuaje(){}

    public Tatuaje(String nombre, int presenciaBase, List<String> especialidades){
        super(nombre, presenciaBase, especialidades, "Tatuaje");
    }

    @Override
    public TipoContenedorEnum getTipoEnum() {
        return TipoContenedorEnum.TATUAJE;
    }
}
