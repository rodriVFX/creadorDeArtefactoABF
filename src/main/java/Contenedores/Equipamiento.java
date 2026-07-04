package Contenedores;

import java.util.ArrayList;
import java.util.List;

public abstract class Equipamiento extends Contenedor {

    private String tipo;
    private int entereza;
    private int rotura;
    private List<String> especializaciones;

    public Equipamiento(){}

    public Equipamiento(String nombre, int presenciaBase, List<String> especialidades, String tipo, int entereza, int rotura, List<String> especializaciones){
        super(nombre, presenciaBase, especialidades);
        this.tipo = tipo;
        this.entereza = entereza;
        this.rotura = rotura;
        this.especializaciones = especializaciones;

    }

    public String getTipo(){
        return tipo;
    }
    public int getEntereza(){
        return entereza;
    }
    public int getRotura(){
        return rotura;
    }
    public List<String> getEspecializaciones(){ return new ArrayList<>(especializaciones); }
}
