package Contenedores;

import Datos.TipoContenedorEnum;

import java.util.List;

public class ArmaProyectiles extends ArmaDistancia{

    private int recarga;

    public ArmaProyectiles(){}

    public ArmaProyectiles(String nombre, int presenciaBase, List<String> especialidades, int modificadorHA, int velocidad, String fuerzaRequerida, String tipo, int entereza, int rotura, List<String> especializaciones, int alcance, int recarga) {
        super(nombre, presenciaBase, especialidades, modificadorHA, velocidad, fuerzaRequerida, tipo, entereza, rotura, especializaciones, alcance);
        this.recarga = recarga;
    }

    public int getRecarga() { return recarga; }

    @Override
    public TipoContenedorEnum getTipoEnum(){
        return TipoContenedorEnum.ARMAPROYECTILES;
    }
}
