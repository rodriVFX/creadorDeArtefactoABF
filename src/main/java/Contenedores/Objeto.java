package Contenedores;

import Datos.Enums.TipoContenedorEnum;

import java.util.List;

public class Objeto extends Contenedor {

    public Objeto(){}

    public Objeto(String nombre, int presenciaBase, List<String> especialidades){
        super(nombre, presenciaBase, especialidades, "Objeto");
    }

    @Override
    public TipoContenedorEnum getTipoEnum(){
        return TipoContenedorEnum.OBJETO;
    }
}